package electric.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author near on 2016/3/19.
 */
public class LoginUtils {

    /**
     * 验证码数字匹配
     *
     * @param request
     * @return
     */
    public static boolean checkImage(HttpServletRequest request) {
        String checkNumber = request.getParameter("checkNumber");
        if (StringUtils.isBlank(checkNumber)) {
            return false;
        }
        // 从Session中获取验证码
        String CHECK_NUMBER_KEY = (String) request.getSession().getAttribute("CHECK_NUMBER_KEY");
        return !StringUtils.isBlank(CHECK_NUMBER_KEY) && checkNumber.equalsIgnoreCase(CHECK_NUMBER_KEY);
    }

    /**
     * 记住我功能，为用户信息添加Cookie
     *
     * @param request
     * @param response
     * @param loginName
     * @param password
     */
    public static void rememberMe(HttpServletRequest request, HttpServletResponse response,
                                  String loginName, String password) {
        try {
            loginName = URLEncoder.encode(loginName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie loginNameCookie = new Cookie("elec_loginName", loginName);
        Cookie passwordCookie = new Cookie("elec_password", password);

        // 设置Cookie存在的有效路径（绝对路径）
        loginNameCookie.setPath(request.getContextPath() + "/");
        passwordCookie.setPath(request.getContextPath() + "/");
        String remeberMe = request.getParameter("rememberMe");
        if (remeberMe != null && "yes".equals(remeberMe)) {
            loginNameCookie.setMaxAge(60 * 60 * 24 * 7);
            passwordCookie.setMaxAge(60 * 60 * 24 * 7);
        } else {
            loginNameCookie.setMaxAge(0);
            passwordCookie.setMaxAge(0);
        }
        // 存放cookie
        response.addCookie(loginNameCookie);
        response.addCookie(passwordCookie);
    }

}
