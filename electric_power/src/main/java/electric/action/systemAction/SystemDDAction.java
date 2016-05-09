package electric.action.systemAction;

import electric.base.action.BaseAction;
import electric.entity.SystemDD;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author near on 2016/3/16.
 */
@Controller
@Scope("prototype")
public class SystemDDAction extends BaseAction<SystemDD> {
    /*
        keywordname：数据类型名称
        typeflag：用来判断执行的动作类型（new/save)
        itemname：存放数据项的数组
    */
    private String keywordname;
    private String typeflag;
    private String[] itemname;

    public String index() {
        List<Object> keywordList = systemDDService.findAllKeyword();
        request.setAttribute("keywordList", keywordList);

        return "index";
    }

    public String edit(){
        List<SystemDD> systemDDList = systemDDService.findByKeyword(model.getKeyword());
        request.setAttribute("systemDDList", systemDDList);

        return "edit";
    }

    public String save(){
        model.setKeyword(keywordname);
        if ("new".equals(typeflag)){
            systemDDService.save(model, itemname);
        } else if ("update".equals(typeflag)){
            systemDDService.update(model, itemname);
        }

        return "save";
    }

    public void setKeywordname(String keywordname) {
        this.keywordname = keywordname;
    }

    public void setTypeflag(String typeflag) {
        this.typeflag = typeflag;
    }

    public void setItemname(String[] itemname) {
        this.itemname = itemname;
    }

}
