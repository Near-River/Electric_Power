package electric.serviceImpl;

import electric.entity.SystemDD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用AXIS（WebService）技术
 *
 * @author near on 2016/3/17.
 */
public class WebSystemDDServiceImpl {

    public String findSystemDDByKeyword(String keyword) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        SystemDDServiceImpl systemDDService = (SystemDDServiceImpl) ac.getBean("systemDDServiceImpl");

        List<SystemDD> list = systemDDService.findByKeyword(keyword);
        StringBuilder webObject = new StringBuilder("");  //axis2支持String类型和XML的类型
        if (list != null && list.size() > 0) {
            for (SystemDD aList : list) {
                webObject.append(aList.getDdValue()).append(",");
            }
            webObject.deleteCharAt(webObject.length() - 1);
        }
        return webObject.toString(); //return "男,女";
    }

}
