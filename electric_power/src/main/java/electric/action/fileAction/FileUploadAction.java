package electric.action.fileAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import electric.entity.FileUpload;
import electric.entity.SystemDD;
import electric.service.FileUploadService;
import electric.service.SystemDDService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 资料图纸管理的 Action
 *
 * @author near on 2016/3/23.
 */
@Controller
@Scope("prototype")
public class FileUploadAction extends ActionSupport implements ModelDriven<FileUpload> {

    private FileUpload model = new FileUpload();

    private HttpServletRequest request = ServletActionContext.getRequest();

    /*获取上传的文件*/
    private File[] uploads;

    /*获取上传的文件名称*/
    private String[] uploadsFileName;

    /*全文检索的查询词条*/
    private String queryString;

    /*获取上传的文件简介*/
    private String[] comments;

    @Autowired
    private SystemDDService systemDDService;

    @Autowired
    private FileUploadService fileUploadService;

    public String index() {
        this.initSystemDD();
        return "index";
    }

    public String luceneIndex() {
        List<FileUpload> uploadList = fileUploadService.searchByQueryString(queryString, model);
        request.setAttribute("uploadList", uploadList);
        this.initSystemDD();

        return "index";
    }

    public String delete() {
        fileUploadService.delete(model.getSeqId());

        return "reIndex";
    }

    public String add() {
        this.initSystemDD();
        return "add";
    }

    public String addList() {
        List<FileUpload> uploadList = fileUploadService.searchByCondition(model);
        request.setAttribute("uploadList", uploadList);

        return "addList";
    }

    public String save() {
        List<Object[]> fileInfo = new ArrayList<Object[]>();
        fileInfo.add(uploads);
        fileInfo.add(uploadsFileName);
        fileInfo.add(comments);
        fileUploadService.saveUploadFile(fileInfo, model);

        return "close";
    }

    public String download() {
        model = fileUploadService.findById(model.getSeqId());
        return "download";
    }

    private void initSystemDD() {
        List<SystemDD> jctList = systemDDService.findByKeyword("所属单位");
        List<SystemDD> dataChart = systemDDService.findByKeyword("图纸类别");
        request.setAttribute("jctList", jctList);
        request.setAttribute("dataChart", dataChart);
    }

    public FileUpload getModel() {
        return model;
    }

    public void setUploads(File[] uploads) {
        this.uploads = uploads;
    }

    public void setUploadsFileName(String[] uploadsFileName) {
        this.uploadsFileName = uploadsFileName;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public InputStream getInputStream() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(model.getFilePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public String getDownloadFileName() {
        String fileName = null;
        try {
            fileName = new String(model.getFileName().getBytes("gbk"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
