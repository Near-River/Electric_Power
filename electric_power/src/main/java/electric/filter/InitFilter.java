package electric.filter;

import electric.entity.User;
import electric.serviceImpl.UserServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 粗粒度的权限控制
 *
 * @author near on 2016/3/19.
 */
public class InitFilter implements Filter {

    // 存放系统无需访问控制的 url
    private List<String> list = new ArrayList<String>();

    public void init(FilterConfig config) throws ServletException {
        list.add("/index.jsp");
        list.add("/image.jsp");
        list.add("/error.jsp");
        list.add("/system/menuManage_home.action");
        list.add("/system/menuManage_logout.action");
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();

        this.rememberMe(request, path);

        // 判断当前用户是否已登录
        User user = (User) request.getSession().getAttribute("globalUser");
        if (user != null) {
            /*处理用户异地登录问题*/
            // 从数据库中查询获取登录状态
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
            UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
            User userDB = userService.findById(user.getUserID());
            Long loginStateDB = userDB.getLoginState();
            // 从Session中查询登录状态
            if (!loginStateDB.equals(user.getLoginState())) {
                request.setAttribute("errorMsg", "您的账号已经在异地登录，换句话说，您被踢了！");
                request.getSession().invalidate();
                request.getRequestDispatcher("/errorMsg.jsp").forward(request, response);
            }
            chain.doFilter(req, resp);
        } else {
            if (list.contains(path)) {
                chain.doFilter(req, resp);
            } else {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
    }

    private void rememberMe(HttpServletRequest request, String path) throws UnsupportedEncodingException {
        if (path.equals("/index.jsp")) {
            String loginName = "";
            String password = "";
            String rememberMe = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if ("elec_loginName".equals(cookie.getName())) {
                        loginName = cookie.getValue();
                        rememberMe = "checked";
                    }
                    if ("elec_password".equals(cookie.getName())) {
                        password = cookie.getValue();
                    }
                }
            }
            request.setAttribute("loginName", URLEncoder.encode(loginName, "UTF-8"));
            request.setAttribute("password", password);
            request.setAttribute("rememberMe", rememberMe);
        }
    }

}
