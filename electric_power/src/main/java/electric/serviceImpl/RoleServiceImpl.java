package electric.serviceImpl;

import electric.base.dao.DaoSupportImpl;
import electric.entity.Role;
import electric.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author near on 2016/3/18.
 */
@Service
@Transactional
@SuppressWarnings("all")
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {

    @Override
    public List<Role> findAll() {
        return getSession().createQuery("FROM Role").setCacheable(true).list();
    }

}
