package electric.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import electric.annotation.PopedomLimit;
import electric.entity.User;
import electric.serviceImpl.PopedomServiceImpl;
import org.apache.struts2.StrutsStatics;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 控制用户访问权限的拦截器
 * <p>
 * Created by near on 2016/3/12.
 */
public class PrivilegeInteceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
        //获取请求的action对象
        Object action = invocation.getAction();
        //获取请求的方法的名称
        String methodName = invocation.getProxy().getMethod();
        //获取action中的方法的封装类(action中的方法没有参数)
        Method method = action.getClass().getMethod(methodName, null);
        if ("MenuAction".equals(action.getClass().getSimpleName())) {
            return invocation.invoke();
        }

        String result; // Action的返回值
        //在完成跳转之前完成细颗粒权限控制，控制Action的每个方法
        //检查注解，是否可以操作权限
        boolean flag = isCheckLimit(request, method);
        flag = true; // 方便开发
        if (flag) {
            result = invocation.invoke();
        } else {
            request.setAttribute("errorMsg", "对不起！您没有权限操作此功能！");
            return "errorMsg";
        }
        return result;
    }

    /**
     * 验证细颗粒权限控制
     */
    public boolean isCheckLimit(HttpServletRequest request, Method method) {
        if (method == null) {
            return false;
        }
        //获取当前的登陆用户
        User user = (User) request.getSession().getAttribute("globalUser");
        if (user == null) {
            return false;
        }

        //处理注解，判断方法上是否存在权限限制的注解
        boolean isAnnotationPresent = method.isAnnotationPresent(PopedomLimit.class);
        //不存在注解（此时不能操作该方法）
        if (!isAnnotationPresent) {
            return false;
        }
        //存在注解（调用注解）
        PopedomLimit limit = method.getAnnotation(PopedomLimit.class);
        //获取注解上的值
        String mid = limit.mid();  //权限子模块名称
        String pid = limit.pid();  //权限父操作名称
        if ("".equals(mid) || "".equals(pid)) {
            return false;
        }

        /*验证用户是否拥有该权限*/
        //拦截器中加载 spring 容器，从而获取相关bean做业务处理
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        PopedomServiceImpl popedomService = (PopedomServiceImpl) wac.getBean("popedomServiceImpl");

        return popedomService.checkPopedomByUser(user.getUserID(), mid, pid);
    }

}
