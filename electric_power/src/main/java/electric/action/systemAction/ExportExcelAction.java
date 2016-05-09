package electric.action.systemAction;

import electric.base.action.BaseAction;
import electric.entity.ExportFields;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author near on 2016/3/16.
 */
@Controller
@Scope("prototype")
public class ExportExcelAction extends BaseAction<ExportFields> {

    public String setExcelFields() {
        String belongTo = model.getBelongTo();
        if (belongTo != null) {
            model = exportExcelService.findById(belongTo);
            exportExcelService.getExcelFields(model);
        }
        return "setExcelFields";
    }

    public String saveExcelFields() {
        if (model != null) {
            exportExcelService.update(model);
        }
        return "close";
    }

}
