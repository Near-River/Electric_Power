package electric.base.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@SuppressWarnings("unchecked")
@Transactional
public class DaoSupportImpl<T> implements DaoSupport<T> {

    private Class clazz = null;

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public DaoSupportImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T entity) {
        getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(Serializable id) {
        T entity = findById(id);
        if (id != null) {
            getSession().delete(entity);
        }
    }

    public void delete(Collection<T> collection) {
        if (collection != null && collection.size() > 0) {
            Session session = getSession();
            for (T entity : collection) {
                session.delete(entity);
            }
        }
    }

    public T findById(Serializable id) {
        return (T) getSession().get(clazz, id);
    }

    public List<T> findAll() {
        return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
    }

    public List<T> findByIds(Serializable[] ids) {
        if (ids == null || ids.length <= 0) {
            return Collections.EMPTY_LIST;
        }
        return getSession().createQuery("FROM " + clazz.getSimpleName() + " u WHERE u.id in (:ids)")
                .setParameterList("ids", ids).list();
    }

    /**
     * 为Query对象注入参数
     *
     * @param query      Query对象
     * @param parameters 注参列表
     * @return 注参后的Query对象
     */
    protected Query setParampters(Query query, List<Object> parameters) {
        if (parameters != null && parameters.size() > 0) {
            for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
            }
        }
        return query;
    }

}
