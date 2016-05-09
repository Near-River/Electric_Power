package electric.service;

import electric.base.dao.DaoSupport;
import electric.entity.CommonMsg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author near on 2016/3/15.
 */
@Service
@Transactional
public interface CommonMsgService extends DaoSupport<CommonMsg> {

}
