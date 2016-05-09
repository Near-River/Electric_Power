package electric.service;


import electric.base.dao.DaoSupport;
import electric.entity.Popedom;

import java.util.List;

/**
 * @author near on 2016/3/18.
 */
public interface PopedomService extends DaoSupport<Popedom> {

    List<Popedom> findByRoleID(Long roleID);

    void saveRolePopedomWityRoleID(String[] mid_pid, Long roleID);

    List<Popedom> loadPopedomMenu(String globalPopedoms);

    boolean checkPopedomByUser(String userID, String mid, String pid);

}
