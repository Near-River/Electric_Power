package electric.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author near on 2016/3/15.
 */
public interface DaoSupport<T> {

    void save(T t);

    void update(T t);

    void delete(Serializable id);

    void delete(Collection<T> collection);

    T findById(Serializable id);

    List<T> findAll();

    List<T> findByIds(Serializable[] ids);

}
