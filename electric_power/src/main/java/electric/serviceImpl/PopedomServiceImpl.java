package electric.serviceImpl;

import electric.base.dao.DaoSupportImpl;
import electric.entity.Popedom;
import electric.entity.RolePopedom;
import electric.service.PopedomService;
import electric.util.QueryHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author near on 2016/3/18.
 */
@Service
@Transactional
@SuppressWarnings("all")
public class PopedomServiceImpl extends DaoSupportImpl<Popedom> implements PopedomService {

    @Override
    public List<Popedom> findAll() {
        return getSession().createQuery("FROM Popedom").setCacheable(true).list();
    }

    /**
     * 查询所有Popedom对象 将角色所关联的Popedom对象的标识(hasPrivilege)置为true
     *
     * @param roleID
     * @return
     */
    public List<Popedom> findByRoleID(Long roleID) {
        List<Popedom> popedomList = this.findAll();
        if (popedomList == null || popedomList.size() <= 0) {
            return null;
        }
        /*查询角色所拥有权限的 id 集合*/
        List<String> mids = getSession().createQuery("SELECT rp.mid FROM RolePopedom rp WHERE rp.roleID=:roleID")
                .setParameter("roleID", roleID).list();
        if (mids != null && mids.size() > 0) {
            for (Popedom popedom : popedomList) {
                if (mids.contains(popedom.getMid())) {
                    popedom.setHasPrivilege(true);
                }
            }
        }
        return popedomList;
    }

    /**
     * 根据传递的权限mid_pid数组以及角色ID 往角色_权限关联表中添加数据
     *
     * @param mid_pid
     * @param roleID
     */
    public void saveRolePopedomWityRoleID(String[] mid_pid, Long roleID) {
        this.deleteRolePopedomByroleID(roleID);
        this.SaveRolePopedomByMP(mid_pid, roleID);
    }

    /**
     * 加载当前用户所能使用的菜单栏权限
     *
     * @param globalPopedoms
     * @return
     */
    public List<Popedom> loadPopedomMenu(String globalPopedoms) {
        QueryHelper queryHelper = new QueryHelper(Popedom.class, "p");
        queryHelper.addCondition("p.isMenu=TRUE")
                .addCondition("p.mid IN(\'" + globalPopedoms.replace("@", "\',\'") + "\')")
                .addOrderProperty("p.mid", true);
        Query query = getSession().createQuery(queryHelper.getListQueryHql());
        return query.list();
    }

    /**
     * 根据用户ID和权限mid、pid来验证用户是否拥有该权限
     * SELECT COUNT(ru.userID)
     * FROM elec_role_user ru INNER JOIN elec_role_popedom rp
     * WHERE ru.roleID = rp.roleID AND ru.userID = '1' AND rp.mid = 'aa' AND rp.pid = '0';
     *
     * @param userID
     * @param mid
     * @param pid
     * @return
     */
    public boolean checkPopedomByUser(String userID, String mid, String pid) {
        Query query = getSession().createSQLQuery("SELECT COUNT(ru.userID) " +
                "FROM elec_role_user ru INNER JOIN elec_role_popedom rp " +
                "WHERE ru.roleID = rp.roleID AND ru.userID = \'" + userID + "\' AND rp.mid = \'" + mid + "\' AND rp.pid = \'" + pid + "\'");
        Object count = (Object) query.uniqueResult();
        if (Integer.parseInt(count.toString()) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据传递的角色ID删除 RolePopedom 对象
     *
     * @param roleID
     */
    private void deleteRolePopedomByroleID(Long roleID) {
        List<RolePopedom> rolePopedoms = this.findRolePopedomById(roleID);
        if (rolePopedoms != null && rolePopedoms.size() > 0) {
            Session session = getSession();
            for (RolePopedom entity : rolePopedoms) {
                session.delete(entity);
            }
        }
    }

    /**
     * 根据传递的权限mid_pid数组以及roleID保存 RolePopedom 对象
     *
     * @param mid_pid
     * @param roleID
     */
    private void SaveRolePopedomByMP(String[] mid_pid, Long roleID) {
        if (mid_pid != null && mid_pid.length > 0) {
            Session session = getSession();
            for (String mp : mid_pid) {
                String[] split = mp.split("_");
                RolePopedom rolePopedom = new RolePopedom(roleID, split[0], split[1]);
                session.save(rolePopedom);
            }
        }
    }

    private List<RolePopedom> findRolePopedomById(Long roleID) {
        return getSession().createQuery("FROM RolePopedom rp WHERE rp.roleID=:roleID")
                .setParameter("roleID", roleID).list();
    }

}
