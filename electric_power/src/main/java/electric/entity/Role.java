package electric.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author near on 2016/3/18.
 */
@Entity
@Table(name = "Elec_Role")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {

    /*角色ID*/
    @Id
    @GeneratedValue(generator = "assigned")
    @GenericGenerator(name = "assigned", strategy = "assigned")
    private Long roleID;

    /*角色名称*/
    private String roleName;

    /*角色映射的所有用户*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Elec_Role_User",
            joinColumns = {
                    @JoinColumn(name = "roleID", referencedColumnName = "roleID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "userID", referencedColumnName = "userID")
            }
    )
    private List<User> users = new ArrayList<User>();

    /*角色所拥有的权限*/
    @Transient
    private List<Popedom> popedoms = new ArrayList<Popedom>();

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
