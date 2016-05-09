package electric.entity;

import electric.entity.ck.PopedomCK;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author near on 2016/3/18.
 */
@Entity
@Table(name = "Elec_Popedom")
@IdClass(value = PopedomCK.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Popedom implements Serializable {

    /*权限ID*/
    @Id
    private String mid;

    /*上级权限ID*/
    @Id
    private String pid;
    /*权限ID*/

    /*权限名称*/
    private String name;

    /*权限所对应的相对请求路径*/
    private String url;

    /*权限对应菜单的图标名称*/
    private String icon;

    /*权限对应菜单栏 链接执行的Frame区域名称*/
    private String target;

    /*是否是父节点*/
    private boolean isParent;

    /*是否是系统菜单结构*/
    private boolean isMenu;

    /*判定角色是否拥有该权限*/
    @Transient
    private boolean hasPrivilege;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public boolean isHasPrivilege() {
        return hasPrivilege;
    }

    public void setHasPrivilege(boolean hasPrivilege) {
        this.hasPrivilege = hasPrivilege;
    }
}
