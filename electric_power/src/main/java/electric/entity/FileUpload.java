package electric.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 上传的资料图纸实体类
 *
 * @author near on 2016/3/16.
 */
@Entity
@Table(name = "Elec_FileUpload")
public class FileUpload implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqId;           //主键ID

    private String proId;            //工程ID/所属单位

    private String belongTo;         //所属模块/图纸类别

    private String fileName;         //文件名

    private String filePath;         //文件上传的路径

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;         //上传时间

    private String comment;          //文件描述

    public FileUpload() {
    }

    public FileUpload(Integer seqId, String proId, String belongTo, String fileName, String filePath, String comment) {
        this.seqId = seqId;
        this.proId = proId;
        this.belongTo = belongTo;
        this.fileName = fileName;
        this.filePath = filePath;
        this.comment = comment;
    }

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
