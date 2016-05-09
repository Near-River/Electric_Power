package electric.vo;

import java.util.Date;

/**
 * User 视图展示层的VO对象
 * @author near on 2016/3/17.
 */
public class UserVo {

    /*主键 ID*/
    private String userID;
    /*所属单位code*/
    private String jctID;
    /*用户姓名*/
    private String userName;
    /*登录名*/
    private String loginName;
    /*性别*/
    private String sexID;
    /*联系电话*/
    private String contactTel;
    /*是否在职*/
    private String isDuty;
    /*职位（主要用于工作流审核）*/
    private String postID;
    /*入职时间*/
    private Date onDutyDate;

    public UserVo() {
    }

    public UserVo(String userID, String userName, String loginName, String contactTel, Date onDutyDate) {
        this.userID = userID;
        this.userName = userName;
        this.loginName = loginName;
        this.contactTel = contactTel;
        this.onDutyDate = onDutyDate;
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

    public String getSexID() {
        return sexID;
    }

    public void setSexID(String sexID) {
        this.sexID = sexID;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
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
}
