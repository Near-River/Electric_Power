package electric.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理的拦截器
 * <p>
 * Created by near on 2016/3/12.
 */
public class ExceptionInteceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
        try {
            return invocation.invoke();
        } catch (Exception e) {
            /*处理异常*/
            String errorMsg = "出现错误信息，请查看日志！";
            //通过instanceof判断到底是什么异常类型
            if (e instanceof RuntimeException) {
                //未知的运行时异常
                RuntimeException re = (RuntimeException) e;
                errorMsg = re.getMessage().trim();
            }
            /*发送错误消息到页面*/
            request.setAttribute("errorMsg", errorMsg);
            /*log4j记录日志*/
            Log log = LogFactory.getLog(invocation.getAction().getClass());
            log.error(errorMsg, e);
            return "errorMsg";
        }
    }
}
