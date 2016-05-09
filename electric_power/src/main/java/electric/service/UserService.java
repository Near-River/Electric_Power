package electric.service;

import electric.base.dao.DaoSupport;
import electric.entity.User;
import electric.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@Service
@Transactional
public interface UserService extends DaoSupport<User> {

    @Deprecated
    List<UserVo> searchAllUsers(User model);

    List<User> searchAllUsersPlus(User model);

    User findByLoginName(String loginName);

    List<User> findByRoleID(Long roleID);

    List<User> findAllOnDuty();

    User checkUser(String loginName, String password);

    String getUserPopedoms(String userID);

    ArrayList<String> getExportExcelName(String belongTo);

    ArrayList<ArrayList<String>> getExportExcelData(String belongTo, User user);

    void saveUserFromExcel(List<User> list);

    List<Object[]> loadChartInfo(String keyword, String column);

}
