package electric.action.systemAction;

import electric.base.action.BaseAction;
import electric.entity.Popedom;
import electric.entity.Role;
import electric.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author near on 2016/3/18.
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

    /*接收关联的权限 mid_pid 数组*/
    private String[] selectoper;

    /*接收关联的角色ID 数组*/
    private String[] selectuser;

    public String index() {
        List<Role> roleList = roleService.findAll();
        List<Popedom> popedomList = popedomService.findAll();
        request.setAttribute("roleList", roleList);
        request.setAttribute("popedomList", popedomList);

        return "index";
    }

    public String edit() {
        Long roleID = model.getRoleID();
        if (roleID != null) {
            List<Popedom> popedomList = popedomService.findByRoleID(roleID);
            request.setAttribute("popedomList", popedomList);
            List<User> userList = userService.findByRoleID(roleID);
            request.setAttribute("userList", userList);
        }

        return "edit";
    }

    public String save() {
        Long roleID = model.getRoleID();
        if (roleID != null) {
            Role role = roleService.findById(roleID);
            List<User> userList = userService.findByIds(selectuser);
            // 保存关联用户
            role.setUsers(userList);
            roleService.update(role);

            // 保存关联权限（往中间表中添加数据）
            popedomService.saveRolePopedomWityRoleID(selectoper, roleID);
        }

        return "save";
    }

    public void setSelectoper(String[] selectoper) {
        this.selectoper = selectoper;
    }

    public void setSelectuser(String[] selectuser) {
        this.selectuser = selectuser;
    }
}
