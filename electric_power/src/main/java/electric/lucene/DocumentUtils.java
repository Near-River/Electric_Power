package electric.lucene;

import electric.entity.FileUpload;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.util.NumericUtils;

/**
 * @author near on 2016/3/23.
 */
public class DocumentUtils {

    /*将 FileUpload 对象转换成 Document 对象*/
    public static Document fileUploadToDocument(FileUpload fileUpload) {
        Document document = new Document();
        String seqId = NumericUtils.intToPrefixCoded(fileUpload.getSeqId());
        //主键ID
        document.add(new Field("seqId", seqId, Field.Store.YES, Field.Index.NOT_ANALYZED));
        //文件名
        document.add(new Field("fileName", fileUpload.getFileName(), Field.Store.YES, Field.Index.ANALYZED));
        //文件存放路径
        document.add(new Field("filePath", fileUpload.getFilePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        //文件描述
        document.add(new Field("comment", fileUpload.getComment(), Field.Store.YES, Field.Index.ANALYZED));
        //所属单位
        document.add(new Field("proId", fileUpload.getProId(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        //图纸类别
        document.add(new Field("belongTo", fileUpload.getBelongTo(), Field.Store.YES, Field.Index.NOT_ANALYZED));

        return document;
    }

    /*将 Document 对象转换成 FileUpload 对象*/
    public static FileUpload documentToFileUpload(Document document){
        FileUpload fileUpload = new FileUpload();
        Integer seqId = NumericUtils.prefixCodedToInt(document.get("seqId"));
        //主键ID
        fileUpload.setSeqId(seqId);
        //文件名
        fileUpload.setFileName(document.get("fileName"));
        //文件路径
        fileUpload.setFilePath(document.get("filePath"));
        //文件描述
        fileUpload.setComment(document.get("comment"));
        //所属单位
        fileUpload.setProId(document.get("proId"));
        //图纸类别
        fileUpload.setBelongTo(document.get("belongTo"));

        return fileUpload;
    }
}
