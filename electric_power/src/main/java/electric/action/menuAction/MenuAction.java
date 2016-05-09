package electric.action.menuAction;

import com.opensymphony.xwork2.ActionSupport;
import electric.entity.CommonMsg;
import electric.entity.Popedom;
import electric.entity.Role;
import electric.entity.User;
import electric.service.CommonMsgService;
import electric.service.PopedomService;
import electric.service.UserService;
import electric.util.LoginUtils;
import electric.util.ValueStackUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author near on 2016/3/15.
 */
@Controller
@Scope("prototype")
public class MenuAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String loginName;

    private String password;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;

    private List<Popedom> menuList;

    @Autowired
    private CommonMsgService commonMsgService;

    @Autowired
    private UserService userService;

    @Autowired
    private PopedomService popedomService;

    public String index() throws Exception {
        return "index";
    }

    public String home() throws Exception {
        if (session.getAttribute("globalUser") == null) {
            // 验证用户名和密码是否为空
            if (loginName == null || password == null || "".equals(loginName) || "".equals(password)) {
                this.addFieldError("loginFailure", "用户名或密码不能为空。");
                return "error";
            }
            // 验证用户名和密码是否正确
            User user = userService.checkUser(loginName, DigestUtils.md5DigestAsHex(password.getBytes()));
            if (user == null) {
                this.addFieldError("loginFailure", "用户名或密码不正确。");
                return "error";
            }
            if ("0".equals(user.getIsDuty())) {
                this.addFieldError("loginFailure", "该用户非在职员工。");
                return "error";
            }
            if (user.isDelete()) {
                this.addFieldError("loginFailure", "不存在该用户。");
                return "error";
            }

            // 验证： 验证码数字
            boolean flag = LoginUtils.checkImage(request);
            if (!flag) {
                this.addFieldError("loginFailure", "验证码输入错误。");
                return "error";
            }
            // 记住我：用户信息的 Cookie 处理
            LoginUtils.rememberMe(request, response, loginName, password);

            // 存放用户的登录状态，以防异地登录问题
            session.setAttribute("globalLoginState", user.getLoginState());
            session.setAttribute("globalUser", user);
            // 存放用户所拥有的角色
            List<Role> roleList = user.getRoles();
            Map<Long, String> globalRoles = new HashMap<Long, String>();
            if (roleList != null && roleList.size() > 0) {
                for (Role role : roleList) {
                    globalRoles.put(role.getRoleID(), role.getRoleName());
                }
            } else {
                this.addFieldError("loginFailure", "该用户未被分配角色。");
                return "error";
            }
            session.setAttribute("globalRoles", globalRoles);
            String globalPopedoms = userService.getUserPopedoms(user.getUserID());
            if (StringUtils.isBlank(globalPopedoms)) {
                this.addFieldError("loginFailure", "该用户未被分配权限。");
                return "error";
            }
            session.setAttribute("globalPopedoms", globalPopedoms);
        }

        return "home";
    }

    /**
     * 使用 Ajax 加载左侧菜单栏数据
     *
     * @return
     */
    public String showMenu() {
        menuList = popedomService.loadPopedomMenu((String) session.getAttribute("globalPopedoms"));
        /**
         * 只有系统管理员才能查看所有用户信息
         *      普通用户只能编辑个人信息(未提供实现)
         */
        return "showMenu";
    }

    public String title() throws Exception {
        return "title";
    }

    public String left() throws Exception {
        return "left";
    }

    public String change() throws Exception {
        return "change";
    }

    public String loading() throws Exception {
        return "loading";
    }

    public String alermStation() throws Exception {
        this.loadCommonMsg();
        return "alermStation";
    }

    public String alermDevice() throws Exception {
        this.loadCommonMsg();
        return "alermDevice";
    }

    public String logout() {
        session.invalidate();
        return "logout";
    }

    private void loadCommonMsg() {
        List<CommonMsg> commonMsgList = commonMsgService.findAll();
        CommonMsg commonMsg = new CommonMsg();
        if (commonMsgList != null && commonMsgList.size() > 0) {
            commonMsg = commonMsgList.get(0);
        }
        ValueStackUtils.pushStack(commonMsg);
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Popedom> getMenuList() {
        return menuList;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
        session = request.getSession();
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
