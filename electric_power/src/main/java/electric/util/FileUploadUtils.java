package electric.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传的工具类
 *
 * @author near on 2016/3/23.
 */
public class FileUploadUtils {

    /**
     * 使用传递的文件参数，实现文件上传的功能
     *
     * @param upload   上传的文件
     * @param fileName 上传的文件名称
     * @return 文件保存的系统路径
     */
    public static String fileReturnPath(File upload, String fileName) {
        //获取上传的文件路径
        String bathPath = ServletActionContext.getServletContext().getRealPath("/upload");
        //使用当前时间在upload的文件夹创建日期的文件夹
        String datePath = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        //使用UUID的方式，作为上传文件的文件名
        String perfix = "";
        if (StringUtils.isNotBlank(fileName)) {
            int pos = fileName.lastIndexOf(".");
            perfix = fileName.substring(pos, fileName.length());
        }
        String filename = UUIDUtils.getUUID() + perfix;

        //如果没有日期文件夹，完成对日期文件夹的创建
        File dateFile = new File(bathPath + datePath);
        if (!dateFile.exists()) {
            //创建文件夹
            dateFile.mkdirs();
        }

        //最终的路径 path
        String path = bathPath + datePath + filename;
        File destFile = new File(path);
        //文件上传,不能删除临时文件
        try {
            FileUtils.copyFile(upload, destFile);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        // upload.renameTo(destFile);

        return path;
    }
}
