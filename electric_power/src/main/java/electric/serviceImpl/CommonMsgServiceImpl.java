package electric.serviceImpl;

import electric.base.dao.DaoSupportImpl;
import electric.entity.CommonMsg;
import electric.service.CommonMsgService;
import electric.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@Service
@Transactional
public class CommonMsgServiceImpl extends DaoSupportImpl<CommonMsg> implements CommonMsgService {

    @Override
    public void save(CommonMsg entity) {
        List<CommonMsg> commonMsgList = this.findAll();
        if (commonMsgList != null && commonMsgList.size() > 0) {
            CommonMsg commonMsg = commonMsgList.get(0);
            commonMsg.setDevRun(entity.getDevRun());
            commonMsg.setStationRun(entity.getStationRun());
            commonMsg.setCreateTime(new Date());
            this.update(commonMsg);
        } else {
            entity.setComId(UUIDUtils.getUUID());
            entity.setCreateTime(new Date());
            getSession().save(entity);
        }
    }

}
