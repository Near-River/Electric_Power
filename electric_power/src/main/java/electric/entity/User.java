package electric.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@Entity
@Table(name = "Elec_User")
public class User implements Serializable {

    /*主键 ID*/
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String userID;

    /*所属单位code*/
    private String jctID;

    /*所属单位的单位名称（联动）*/
    private String jctUnitID;

    /*用户姓名*/
    private String userName;

    /*登录名*/
    private String loginName;

    /*密码*/
    private String password;

    /*性别*/
    private String sexID;

    /*出生日期*/
    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    /*联系地址*/
    private String address;

    /*联系电话*/
    private String contactTel;

    /*电子邮箱*/
    private String email;

    /*手机*/
    private String mobile;

    /*是否在职*/
    private String isDuty;

    /*职位（主要用于工作流审核）*/
    private String postID;

    /*入职时间*/
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date onDutyDate;

    /*离职时间*/
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date offDutyDate;

    /*备注*/
    private String remark;

    /*是否删除*/
    private boolean isDelete;

    /*用户所拥有的角色*/
    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<Role>();

    /*判定角色是否关联该用户*/
    @Transient
    private boolean belongTo;

    /*用于记录用户登录状态：处理异地登录问题*/
    @Column(columnDefinition = "bigint default 0")
    private Long loginState;

    // * 做流程操作控制时加入
    //    /*创建人ID*/
    //    private String createEmpID;
    //
    //    /*创建时间*/
    //    private Date createDate;
    //
    //    /*修改人ID*/
    //    private String lastEmpID;
    //
    //    /*修改时间*/
    //    private Date lastModifyDate;

    public User() {
    }

    public User(String userID, String userName, String loginName, String contactTel, Date onDutyDate,
                String sexID, String postID) {
        this.userID = userID;
        this.userName = userName;
        this.loginName = loginName;
        this.contactTel = contactTel;
        this.onDutyDate = onDutyDate;
        this.sexID = sexID;
        this.postID = postID;
    }

    public User(String sexID) {
        this.sexID = sexID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getJctID() {
        return jctID;
    }

    public void setJctID(String jctID) {
        this.jctID = jctID;
    }

    public String getJctUnitID() {
        return jctUnitID;
    }

    public void setJctUnitID(String jctUnitID) {
        this.jctUnitID = jctUnitID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexID() {
        return sexID;
    }

    public void setSexID(String sexID) {
        this.sexID = sexID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIsDuty() {
        return isDuty;
    }

    public void setIsDuty(String isDuty) {
        this.isDuty = isDuty;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public Date getOnDutyDate() {
        return onDutyDate;
    }

    public void setOnDutyDate(Date onDutyDate) {
        this.onDutyDate = onDutyDate;
    }

    public Date getOffDutyDate() {
        return offDutyDate;
    }

    public void setOffDutyDate(Date offDutyDate) {
        this.offDutyDate = offDutyDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isBelongTo() {
        return belongTo;
    }

    public void setBelongTo(boolean belongTo) {
        this.belongTo = belongTo;
    }

    public Long getLoginState() {
        return loginState;
    }

    public void setLoginState(Long loginState) {
        this.loginState = loginState;
    }
}
