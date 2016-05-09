package electric.base.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import electric.service.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;

/**
 * @author near on 2016/3/15.
 */
@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>, ServletRequestAware, ServletResponseAware {

    protected T model;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    // protected HttpServletRequest request = ServletActionContext.getRequest();
    // protected HttpServletResponse response = ServletActionContext.getResponse();

    @Autowired
    protected CommonMsgService commonMsgService;

    @Autowired
    protected ExportExcelService exportExcelService;

    @Autowired
    protected SystemDDService systemDDService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected PopedomService popedomService;

    public BaseAction() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class) pt.getActualTypeArguments()[0];
        try {
            model = (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public T getModel() {
        return model;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
