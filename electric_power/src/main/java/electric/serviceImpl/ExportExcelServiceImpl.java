package electric.serviceImpl;

import electric.base.dao.DaoSupportImpl;
import electric.entity.ExportFields;
import electric.service.ExportExcelService;
import electric.util.StringToListUtils;
import electric.util.ValueStackUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author near on 2016/3/15.
 */
@Service
@Transactional
public class ExportExcelServiceImpl extends DaoSupportImpl<ExportFields> implements ExportExcelService {

    public void getExcelFields(ExportFields exportFields) {
        List<String> expFieldName = StringToListUtils.listify(exportFields.getExpFieldName());
        List<String> expNameList = StringToListUtils.listify(exportFields.getExpNameList());
        List<String> noExpFieldName = StringToListUtils.listify(exportFields.getNoExpFieldName());
        List<String> noExpNameList = StringToListUtils.listify(exportFields.getNoExpNameList());

        Map<String, String> includeMap = new LinkedHashMap<String, String>();
        Map<String, String> excludeMap = new LinkedHashMap<String, String>();

        if (expFieldName != null && expFieldName.size() > 0) {
            for (int i = 0; i < expFieldName.size(); i++) {
                includeMap.put(expFieldName.get(i), expNameList.get(i));
            }
        }
        if (noExpFieldName != null && noExpFieldName.size() > 0) {
            for (int i = 0; i < noExpFieldName.size(); i++) {
                excludeMap.put(noExpFieldName.get(i), noExpNameList.get(i));
            }
        }
        ValueStackUtils.putStack("includeMap", includeMap);
        ValueStackUtils.putStack("excludeMap", excludeMap);
    }

}
