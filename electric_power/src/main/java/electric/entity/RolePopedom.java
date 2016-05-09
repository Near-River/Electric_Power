package electric.entity;

import electric.entity.ck.RolePopedomCK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author near on 2016/3/18.
 */
@Entity
@Table(name = "Elec_Role_Popedom")
@IdClass(value = RolePopedomCK.class)
public class RolePopedom implements Serializable {

    /*角色ID*/
    @Id
    private Long roleID;

    /*权限ID*/
    @Id
    private String mid;

    /*上级权限ID*/
    @Id
    private String pid;

    public RolePopedom() {
    }

    public RolePopedom(Long roleID, String mid, String pid) {
        this.roleID = roleID;
        this.mid = mid;
        this.pid = pid;
    }

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
