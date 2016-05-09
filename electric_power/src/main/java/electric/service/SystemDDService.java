package electric.service;

import electric.base.dao.DaoSupport;
import electric.entity.SystemDD;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@Service
@Transactional
public interface SystemDDService extends DaoSupport<SystemDD> {

    List<Object> findAllKeyword();

    List<SystemDD> findByKeyword(String keyword);

    void save(SystemDD entity, String[] itemNames);

    void update(SystemDD entity, String[] itemNames);

    String findByKeywordAndDdCode(String keyword, String ddCode);

    String findByKeywordAndDdValue(String keyword, String ddValue);

}
