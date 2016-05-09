package electric.entity.ck;

import java.io.Serializable;

/**
 * RolePopedom 的联合主键类
 * @author near on 2016/3/18.
 */
public class RolePopedomCK implements Serializable {

    /*角色ID*/
    private Long roleID;

    /*权限ID*/
    private String mid;

    /*上级权限ID*/
    private String pid;

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
