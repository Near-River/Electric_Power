package electric.lucene;

import electric.entity.FileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.util.NumericUtils;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author near on 2016/3/23.
 */
public class LuceneUtils {

    /*向索引库中新增数据*/
    public static void saveFileUpload(FileUpload fileUpload) {
        Document document = DocumentUtils.fileUploadToDocument(fileUpload);
        try {
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_36, Configuration.getAnalyzer());
            IndexWriter indexWriter = new IndexWriter(Configuration.getDirectory(), indexWriterConfig);
            indexWriter.addDocument(document);
            indexWriter.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /*索引库中删除数据*/
    public static void deleteFileUploadByID(Integer seqId) {
        String id = NumericUtils.intToPrefixCoded(seqId);
        Term term = new Term("seqId", id);
        IndexWriter indexWriter = null;
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_36, Configuration.getAnalyzer());
        try {
            indexWriter = new IndexWriter(Configuration.getDirectory(), indexWriterConfig);
            indexWriter.deleteDocuments(term);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert indexWriter != null;
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 使用搜索条件，从索引库中搜索出对应的结果
     */
    public static List<FileUpload> searchFileUploadByCondition(String queryString, String proId, String belongTo) {
        List<FileUpload> list = new ArrayList<FileUpload>();
        try {
            IndexSearcher indexSearcher = new IndexSearcher(IndexReader.open(Configuration.getDirectory()));
            //指定查询条件在文件名称和文件描述、所属单位、图纸类别的字段上进行搜索
            QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36, new String[]{"fileName", "comment"}, Configuration.getAnalyzer());
            /*使用lucene的多条件查询，即boolean查询，必须满足3个条件*/
            BooleanQuery booleanQuery = new BooleanQuery();
            //【按文件名称和描述搜素】搜素的条件
            if (StringUtils.isNotBlank(queryString)) {
                Query query = queryParser.parse(queryString);
                booleanQuery.add(query, BooleanClause.Occur.MUST);
            }
            //【所属单位】搜素的条件
            if (StringUtils.isNotBlank(proId)) {
                Query query = new TermQuery(new Term("proId", proId));
                booleanQuery.add(query, BooleanClause.Occur.MUST);
            }
            //【图纸类别】搜素的条件
            if (StringUtils.isNotBlank(belongTo)) {
                Query query = new TermQuery(new Term("belongTo", belongTo));
                booleanQuery.add(query, BooleanClause.Occur.MUST);
            }
            //返回前100条数据
            TopDocs topDocs = indexSearcher.search(booleanQuery, 100);
            //返回结果集
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            /*设置高亮效果 begin*/
            Formatter formatter = new SimpleHTMLFormatter("<span style=\"color:red;\">", "</span>");
            Scorer scorer = new QueryScorer(booleanQuery);
            Highlighter highlighter = new Highlighter(formatter, scorer);
            //摘要大小（设置大点，最好比文件名大，因为文件名最好不要截取）
            int fragmentSize = 50;
            Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
            highlighter.setTextFragmenter(fragmenter);
            /*设置高亮效果 end*/

            if (scoreDocs != null && scoreDocs.length > 0) {
                for (ScoreDoc scoreDoc : scoreDocs) {
                    //使用内部惟一编号，获取对应的数据，编号从0开始
                    Document document = indexSearcher.doc(scoreDoc.doc);

                    /*获取高亮效果begin*/
                    /*返回文件名的高亮效果*/
                    String fileNameText = highlighter.getBestFragment(Configuration.getAnalyzer(), "fileName", document.get("fileName"));
                    //没有高亮的效果
                    if (fileNameText == null) {
                        fileNameText = document.get("fileName");
                        if (fileNameText != null && fileNameText.length() > fragmentSize) {
                            fileNameText = fileNameText.substring(0, fragmentSize);
                        }
                    }
                    document.getField("fileName").setValue(fileNameText);
                    /*返回文件描述的高亮效果*/
                    String commentText = highlighter.getBestFragment(Configuration.getAnalyzer(), "comment", document.get("comment"));
                    //没有高亮的效果
                    if (commentText == null) {
                        commentText = document.get("comment");
                        if (commentText != null && commentText.length() > fragmentSize) {
                            commentText = commentText.substring(0, fragmentSize);
                        }
                    }
                    document.getField("comment").setValue(commentText);
                    /*获取高亮效果end*/

                    //将Document转换成ElecFileUpload
                    FileUpload fileUpload = DocumentUtils.documentToFileUpload(document);
                    list.add(fileUpload);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return list;
    }
}
