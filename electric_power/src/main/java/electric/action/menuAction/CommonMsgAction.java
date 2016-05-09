package electric.action.menuAction;

import com.opensymphony.xwork2.ActionContext;
import electric.base.action.BaseAction;
import electric.entity.CommonMsg;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@Controller
@Scope("prototype")
public class CommonMsgAction extends BaseAction<CommonMsg> {

    public String home() {
        List<CommonMsg> commonMsgList = commonMsgService.findAll();
        if (commonMsgList != null && commonMsgList.size() > 0) {
            model = commonMsgList.get(0);
        }
        ActionContext.getContext().getValueStack().push(model);
        return "home";
    }

    public String save() {
        commonMsgService.save(model);

        /* 模拟大数据的保存 */
        /*
        for (int i = 0; i < 1000; i++) {
            commonMsgService.save(model);
            request.getSession().setAttribute("percent", (1.0 * i / 1000) * 100);
        }
        request.getSession().removeAttribute("percent");
        */

        return "toHome";
    }

    public String progressBar() throws IOException {
        Double percent = (Double) request.getSession().getAttribute("percent");
        String res = "";
        if (percent != null) {
            int percentInt = (int) Math.rint(percent);
            res = "<percent>" + percentInt + "</percent>";
        } else {
            res = "<percent>" + 100 + "</percent>";
        }
        //定义ajax 返回结果是XML类型
        PrintWriter out = response.getWriter();
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        //存放结果数据
        out.println("<response>" + res + "</response>");
        out.close();
        return NONE;
    }

}
