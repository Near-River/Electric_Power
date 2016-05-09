package pro_test;

import electric.entity.FileUpload;
import electric.service.FileUploadService;
import electric.service.PopedomService;
import electric.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class TestSsh_02 extends BaseSpringTest {

    @Resource
    private PopedomService popedomService;

    @Resource
    private UserService userService;

    @Resource
    private FileUploadService fileUploadService;

    @Test
    public void test() {
        System.out.println();
        boolean flag = popedomService.checkPopedomByUser("1", "fa", "0");
        System.out.println(flag);
    }

    @Test
    public void test2() {
        System.out.println();
        List<Object[]> data = userService.loadChartInfo("性别", "sexID");
        System.out.println(data.size());
    }

    @Test
    public void test3() {
        System.out.println();
        FileUpload fileUpload = new FileUpload();
        fileUpload.setProId("2");
        fileUpload.setBelongTo("2");
        List<FileUpload> data = fileUploadService.searchByCondition(fileUpload);
        System.out.println(data.size());
    }
}