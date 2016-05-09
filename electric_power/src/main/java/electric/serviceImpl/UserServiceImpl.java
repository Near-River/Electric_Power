package electric.serviceImpl;

import electric.base.dao.DaoSupportImpl;
import electric.entity.ExportFields;
import electric.entity.User;
import electric.service.ExportExcelService;
import electric.service.SystemDDService;
import electric.service.UserService;
import electric.util.QueryHelper;
import electric.util.StringToListUtils;
import electric.util.UUIDUtils;
import electric.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author near on 2016/3/17.
 */
@Service
@Transactional
@SuppressWarnings("all")
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

    @Autowired
    private SystemDDService systemDDService;

    @Autowired
    private ExportExcelService exportExcelService;

    /**
     * 查询条件：姓名、入职时间 ~ 离职时间、所属单位
     */
    @Deprecated
    public List<UserVo> searchAllUsers(User model) {
        QueryHelper queryHelper = new QueryHelper(User.class, "u");
        queryHelper.addCondition(StringUtils.isNotBlank(model.getUserName()), "u.userName LIKE ?", "%" + model.getUserName() + "%")
                .addCondition(StringUtils.isNotBlank(model.getJctID()), "u.postID=?", model.getJctID())
                .addCondition(model.getOnDutyDate() != null, "u.onDutyDate>=?", model.getOnDutyDate())
                .addCondition(model.getOffDutyDate() != null, "u.onDutyDate<=?", model.getOffDutyDate());
        Query query = getSession().createQuery(queryHelper.getListQueryHql());
        query = this.setParampters(query, queryHelper.getParameters());

        List<User> userList = query.list();
        return this.setVoToPo(userList);
    }

    /**
     * 使用关联查询做优化：
     * SELECT
     * dd.ddValue sexID,
     * dd2.ddValue jctID,
     * dd3.ddValue postID,
     * dd4.ddValue isDuty
     * FROM elec_user u INNER JOIN elec_systemdd dd ON u.sexID = dd.ddCode AND dd.keyword = "性别"
     * INNER JOIN elec_systemdd dd2 ON u.jctID = dd2.ddCode AND dd2.keyword = "所属单位"
     * INNER JOIN elec_systemdd dd3 ON u.postID = dd3.ddCode AND dd3.keyword = "职位"
     * INNER JOIN elec_systemdd dd4 ON u.isDuty = dd4.ddCode AND dd4.keyword = "是否在职"
     * <p>
     * 查询条件：姓名、入职时间 ~ 离职时间、所属单位
     */
    public List<User> searchAllUsersPlus(User model) {
        List<User> userList = new ArrayList<User>();
        String sql = "SELECT u.userID, u.userName, u.loginName, u.contactTel, u.onDutyDate,\n" +
                "   dd.ddValue sexID, dd2.ddValue postID FROM elec_user AS u" +
                "  INNER JOIN elec_systemdd dd ON u.sexID = dd.ddCode AND dd.keyword = ?\n" +
                "  INNER JOIN elec_systemdd dd2 ON u.postID = dd2.ddCode AND dd2.keyword = ?";

        QueryHelper queryHelper = new QueryHelper();
        queryHelper.addParameters(new String[]{"性别", "职位"});
        queryHelper.addCondition(StringUtils.isNotBlank(model.getUserName()), "u.userName LIKE ?", "%" + model.getUserName() + "%")
                .addCondition(StringUtils.isNotBlank(model.getJctID()), "u.postID=?", model.getJctID())
                .addCondition(model.getOnDutyDate() != null, "u.onDutyDate>=?", model.getOnDutyDate())
                .addCondition(model.getOffDutyDate() != null, "u.onDutyDate<=?", model.getOffDutyDate())
                .addCondition("u.isDelete=?", 0);
        Query query = getSession().createSQLQuery(sql + queryHelper.getListQueryHql());
        query = this.setParampters(query, queryHelper.getParameters());

        List<Object[]> objects = query.list();
        if (objects != null && objects.size() > 0) {
            for (Object[] objs : objects) {
                User user = new User(String.valueOf(objs[0]), String.valueOf(objs[1]), String.valueOf(objs[2]),
                        String.valueOf(objs[3]), (Date) objs[4], String.valueOf(objs[5]), String.valueOf(objs[6]));
                userList.add(user);
            }
        }
        return userList;
    }

    public User findByLoginName(String loginName) {
        return (User) getSession().createQuery("FROM User u WHERE u.loginName=:loginName AND u.isDelete=FALSE")
                .setParameter("loginName", loginName).uniqueResult();
    }

    /**
     * 查询所有在职且未被删除的用户
     *
     * @return
     */
    public List<User> findAllOnDuty() {
        return getSession().createQuery("FROM User u WHERE u.isDuty='1' AND u.isDelete=0").list();
    }

    /**
     * 根据用户名和密码验证用户
     *
     * @param loginName 登录名
     * @param password  密码
     * @return
     */
    public User checkUser(String loginName, String password) {
        User user = (User) getSession().createQuery("FROM User u WHERE u.loginName=:loginName AND u.password=:password")
                .setParameter("loginName", loginName).setParameter("password", password).uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getRoles());
            user.setLoginState(user.getLoginState() + 1);
            return user;
        }
        return null;
    }

    /**
     * 根据用户ID关联查询用户所拥有的所有权限mid
     * <p>
     * SELECT rp.mid
     * FROM elec_role_user ru
     * INNER JOIN elec_role_popedom rp ON ru.roleID = rp.roleID
     * WHERE ru.userID = '1'
     * ORDER BY rp.mid;
     *
     * @param userID 用户ID
     * @return 权限mid组成的字符串
     */
    public String getUserPopedoms(String userID) {
        String sql = "SELECT rp.mid FROM elec_role_user ru INNER JOIN elec_role_popedom rp ON ru.roleID = rp.roleID " +
                "WHERE ru.userID = ? ORDER BY rp.mid ASC";
        List<String> popedomStrs = getSession().createSQLQuery(sql).setParameter(0, userID).list();
        if (popedomStrs != null && popedomStrs.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (String str : popedomStrs) {
                buffer.append(str + "@");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            return buffer.toString();
        }
        return null;
    }

    /**
     * 查询所有User对象 将角色所关联的User对象的标识置为true
     *
     * @param roleID
     * @return
     */
    public List<User> findByRoleID(Long roleID) {
        List<User> userList = this.findAllOnDuty();
        if (userList == null || userList.size() <= 0) {
            return null;
        }
        /*查询角色所拥有用户的 id 集合*/
        List<String> userIDs = getSession().createSQLQuery("SELECT ru.userID FROM elec_role_user ru WHERE ru.roleID=:roleID AND u.isDelete=FALSE")
                .setParameter("roleID", roleID).list();
        if (userIDs != null && userIDs.size() > 0) {
            for (User user : userList) {
                if (userIDs.contains(user.getUserID())) {
                    user.setBelongTo(true);
                }
            }
        }
        return userList;
    }

    @Override
    public void delete(Collection<User> collection) {
        if (collection != null && collection.size() > 0) {
            Session session = getSession();
            for (User user : collection) {
                user.setDelete(true);
                session.update(user);
            }
        }
    }

    @Deprecated
    private List<UserVo> setVoToPo(List<User> userList) {
        /*组装 VO 对象*/
        List<UserVo> userVoList = new ArrayList<UserVo>();
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                UserVo userVo = new UserVo(user.getUserID(), user.getUserName(), user.getLoginName(), user.getContactTel(), user.getOnDutyDate());
                userVo.setSexID(StringUtils.isNotBlank(user.getSexID()) ? systemDDService.findByKeywordAndDdCode("性别", user.getSexID()) : "");
                userVo.setPostID(StringUtils.isNotBlank(user.getPostID()) ? systemDDService.findByKeywordAndDdCode("职位", user.getPostID()) : "");
                // userVo.setJctID(StringUtils.isNotBlank(user.getJctID()) ? systemDDService.findByKeywordAndDdCode("所属单位", user.getJctID()) : "");
                // userVo.setIsDuty(StringUtils.isNotBlank(user.getIsDuty()) ? systemDDService.findByKeywordAndDdCode("是否在职", user.getIsDuty()) : "");
                userVoList.add(userVo);
            }
        }
        return userVoList;
    }

    /**
     * 获取导出字段表中的中文字段列表
     *
     * @param belongTo 用户信息导出字段标识
     * @return
     */
    public ArrayList<String> getExportExcelName(String belongTo) {
        ArrayList<String> exportExcelName = new ArrayList<String>();
        ExportFields exportFields = exportExcelService.findById(belongTo);

        String expNameList = exportFields.getExpNameList();
        ArrayList<String> nameList = (ArrayList<String>) StringToListUtils.listify(expNameList);

        return nameList;
    }

    /**
     * 获取导出字段表中的用户信息列表
     *
     * @param belongTo 用户信息导出字段标识
     * @param user
     * @return
     */
    public ArrayList<ArrayList<String>> getExportExcelData(String belongTo, User user) {
        ArrayList<ArrayList<String>> exportExcelData = new ArrayList<ArrayList<String>>();
        ExportFields exportFields = exportExcelService.findById(belongTo);

        String expNameList = exportFields.getExpNameList();
        String expFieldName = exportFields.getExpFieldName();

        String sql = "SELECT " + expFieldName.replace("#", ",") + " FROM User";
        QueryHelper queryHelper = new QueryHelper();
        queryHelper.addCondition(StringUtils.isNotBlank(user.getUserName()), "userName LIKE ?", "%" + user.getUserName() + "%")
                .addCondition(StringUtils.isNotBlank(user.getJctID()), "postID=?", user.getJctID())
                .addCondition(user.getOnDutyDate() != null, "onDutyDate>=?", user.getOnDutyDate())
                .addCondition(user.getOffDutyDate() != null, "onDutyDate<=?", user.getOffDutyDate())
                .addCondition("isDelete=?", 0);
        Query query = getSession().createQuery(sql + queryHelper.getListQueryHql());
        query = this.setParampters(query, queryHelper.getParameters());

        List<Object[]> userList = query.list();
        if (userList != null && userList.size() > 0) {
            for (Object[] userInfo : userList) {
                ArrayList<String> userData = new ArrayList<String>();
                if (userInfo != null && userInfo.length > 0) {
                    if (expFieldName.contains("#")) { //导出字段多于一条
                        String[] nameList = expNameList.split("#");
                        for (int i = 0; i < userInfo.length; i++) {
                            Object value = userInfo[i];
                            String field = "";
                            if (value != null) {
                                if ("性别".equals(nameList[i]) || "所属单位".equals(nameList[i]) || "职位".equals(nameList[i]) || "是否在职".equals(nameList[i])) {
                                    field = StringUtils.isNotBlank(value.toString()) ? systemDDService.findByKeywordAndDdCode(nameList[i], value.toString()) : "";
                                } else {
                                    field = value.toString();
                                }
                            }
                            userData.add(field);
                        }
                    } else { //导出字段仅一条
                        Object value = userInfo[0];
                        String field = "";
                        if (value != null) {
                            if ("性别".equals(expFieldName) || "所属单位".equals(expFieldName) || "职位".equals(expFieldName) || "是否在职".equals(expFieldName)) {
                                field = StringUtils.isNotBlank(value.toString()) ? systemDDService.findByKeywordAndDdCode(expNameList, value.toString()) : "";
                            } else {
                                field = value.toString();
                            }
                        }
                        userData.add(field);
                    }
                }
                exportExcelData.add(userData);
            }
        }

        return exportExcelData;
    }

    /**
     * 保存从Excel表格中导出的User对象
     *
     * @param list
     */
    public void saveUserFromExcel(List<User> list) {
        if (list != null && list.size() > 0) {
            for (User user : list) {
                user.setUserID(UUIDUtils.getUUID());
                this.save(user);
            }
        }
    }

    /**
     * 联合查询图形所需的统计数据
     * <p>
     * SELECT
     * count(u.userID),
     * dd.keyword,
     * dd.ddValue
     * FROM elec_user u INNER JOIN elec_systemdd dd
     * WHERE u.jctID = dd.ddCode AND dd.keyword = '所属单位' AND u.isDelete = FALSE
     * GROUP BY dd.ddCode;
     *
     * @param keyword 数据字典的keyword字段名称
     * @param column  关联数据字典ddCode的列字段名称
     * @return
     */
    public List<Object[]> loadChartInfo(String keyword, String column) {
        String sql = "SELECT count(u.userID), dd.keyword, dd.ddValue FROM elec_user u INNER JOIN elec_systemdd dd " +
                " ON u." + column + " = dd.ddCode AND dd.keyword = \'" + keyword + "\' AND u.isDelete = FALSE " +
                " GROUP BY dd.ddCode";
        return getSession().createSQLQuery(sql).list();
    }
}
