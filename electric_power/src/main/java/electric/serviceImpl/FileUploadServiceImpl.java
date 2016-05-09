package electric.serviceImpl;

import electric.base.dao.DaoSupportImpl;
import electric.entity.FileUpload;
import electric.lucene.LuceneUtils;
import electric.service.FileUploadService;
import electric.util.FileUploadUtils;
import electric.util.QueryHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@Service
@Transactional
@SuppressWarnings("all")
public class FileUploadServiceImpl extends DaoSupportImpl<FileUpload> implements FileUploadService {

    /**
     * 使用关联查询做优化：查询符合条件的资料图纸
     * <p>
     * SELECT
     * dd.ddValue  proId,
     * dd2.ddValue paperType
     * FROM elec_fileupload f INNER JOIN elec_systemdd dd ON f.proId = dd.ddCode AND dd.keyword = '所属单位'
     * INNER JOIN elec_systemdd dd2 ON f.belongTo = dd2.ddCode AND dd2.keyword = '图纸类别'
     * WHERE f.proId = '2' AND f.belongTo = '2'
     * <p>
     * 查询条件：所属单位、图纸类别
     */
    public List<FileUpload> searchByCondition(FileUpload model) {
        List<FileUpload> fileUploads = new ArrayList<FileUpload>();

        String sql = "SELECT f.seqId, dd.ddValue proId, dd2.ddValue belongTo, f.fileName, filePath, f.comment" +
                " FROM elec_fileupload f INNER JOIN elec_systemdd dd ON f.proId = dd.ddCode AND dd.keyword = ? " +
                "  INNER JOIN elec_systemdd dd2 ON f.belongTo = dd2.ddCode AND dd2.keyword = ? ";

        QueryHelper queryHelper = new QueryHelper();
        queryHelper.addParameters(new String[]{"所属单位", "图纸类别"});
        queryHelper.addCondition(StringUtils.isNotBlank(model.getProId()), "f.proId=?", model.getProId())
                .addCondition(StringUtils.isNotBlank(model.getBelongTo()), "f.belongTo=?", model.getBelongTo());
        Query query = getSession().createSQLQuery(sql + queryHelper.getListQueryHql());
        query = this.setParampters(query, queryHelper.getParameters());

        List<Object[]> objects = query.list();
        if (objects != null && objects.size() > 0) {
            for (Object[] objs : objects) {
                // (Integer seqId, String proId, String belongTo, String fileName, String filePath,  String comment)
                FileUpload fileUpload = new FileUpload(Integer.parseInt(String.valueOf(objs[0])), String.valueOf(objs[1]), String.valueOf(objs[2]),
                        String.valueOf(objs[3]), String.valueOf(objs[4]), String.valueOf(objs[5]));
                fileUploads.add(fileUpload);
            }
        }
        return fileUploads;
    }

    /**
     * 保存上传的资料图纸文件
     *
     * @param fileInfo 封装了上传文件的信息
     */
    public void saveUploadFile(List<Object[]> fileInfo, FileUpload model) {
        /*获取上传的文件*/
        File[] uploads = (File[]) fileInfo.get(0);
        /*获取上传的文件名称*/
        String[] uploadsFileName = (String[]) fileInfo.get(1);
        /*获取上传的文件简介*/
        String[] comments = (String[]) fileInfo.get(2);

        if (uploads != null && uploads.length > 0) {
            for (int i = 0; i < uploads.length; i++) {
                //保存上传的资料图纸信息
                File file = uploads[i];
                FileUpload fileUpload = new FileUpload();
                fileUpload.setProId(model.getProId());
                fileUpload.setBelongTo(model.getBelongTo());
                fileUpload.setFileName(uploadsFileName[i]);
                fileUpload.setFilePath(FileUploadUtils.fileReturnPath(file, uploadsFileName[i]));
                fileUpload.setComment(comments[i]);
                fileUpload.setUploadTime(new Date());
                this.save(fileUpload);

                //向索引库中添加数据
                LuceneUtils.saveFileUpload(fileUpload);
            }
        }
    }

    /**
     * 根据全文检索词条 查询索引库中数据
     *
     * @param queryString 搜索词条
     * @param model
     */
    @Transactional(readOnly = true)
    public List<FileUpload> searchByQueryString(String queryString, FileUpload model) {
        List<FileUpload> fileUploads = new ArrayList<FileUpload>();
        String proId = model.getProId();
        String belongTo = model.getBelongTo();

        List<FileUpload> list = LuceneUtils.searchFileUploadByCondition(queryString, proId, belongTo);

        if (list != null && list.size() > 0) {
            for (FileUpload fileUpload : list) {
                FileUpload upload = this.findById(fileUpload.getSeqId());
                upload.setFileName(fileUpload.getFileName());
                upload.setComment(fileUpload.getComment());

                fileUploads.add(upload);
            }
        }

        return fileUploads;
    }

    @Override
    public void delete(Serializable id) {
        FileUpload fileUpload = this.findById(id);
        //根据图纸存放路径 删除其对应的上传的文件
        try {
            FileUtils.forceDelete(new File(fileUpload.getFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //删除索引库中存放的数据
        LuceneUtils.deleteFileUploadByID(fileUpload.getSeqId());
        //删除数据库中的记录
        getSession().delete(fileUpload);
    }
}
