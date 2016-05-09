package electric.serviceImpl;

import electric.base.dao.DaoSupportImpl;
import electric.entity.SystemDD;
import electric.service.SystemDDService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author near on 2016/3/16.
 */
@Service
@Transactional
@SuppressWarnings("all")
public class SystemDDServiceImpl extends DaoSupportImpl<SystemDD> implements SystemDDService {

    public List<Object> findAllKeyword() {
        return getSession().createQuery("SELECT DISTINCT dd.keyword FROM SystemDD dd").setCacheable(true).list();
    }

    public List<SystemDD> findByKeyword(String keyword) {
        return getSession().createQuery("FROM SystemDD dd WHERE dd.keyword=:keyword ORDER BY dd.ddCode")
                .setCacheable(true).setParameter("keyword", keyword).list();
    }

    public void save(SystemDD entity, String[] itemNames) {
        String keyword = entity.getKeyword();
        if (itemNames != null && itemNames.length > 0) {
            for (int i = 0; i < itemNames.length; i++) {
                SystemDD systemDD = new SystemDD();
                systemDD.setKeyword(keyword);
                systemDD.setDdCode(i + 1);
                systemDD.setDdValue(itemNames[i]);
                this.save(systemDD);
            }
        }
    }

    public void update(SystemDD entity, String[] itemNames) {
        String keyword = entity.getKeyword();
        List<SystemDD> systemDDList = this.findByKeyword(keyword);
        // 将已存在的 数据类型为keyword 的字典数据全部清除
        this.delete(systemDDList);
        this.save(entity, itemNames);
    }

    public String findByKeywordAndDdCode(String keyword, String ddCode) {
        return (String) getSession().createQuery("SELECT dd.ddValue FROM SystemDD dd WHERE dd.keyword=:keyword AND dd.ddCode=:ddCode")
                .setCacheable(true).setParameter("keyword", keyword).setParameter("ddCode", Integer.parseInt(ddCode)).uniqueResult();
    }

    public String findByKeywordAndDdValue(String keyword, String ddValue) {
        Integer ddCode = (Integer) getSession().createQuery("SELECT dd.ddCode FROM SystemDD dd WHERE dd.keyword=:keyword AND dd.ddValue=:ddValue")
                .setCacheable(true).setParameter("keyword", keyword).setParameter("ddValue", ddValue).uniqueResult();
        return ddCode.toString();
    }

}
