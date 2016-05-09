package electric.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Excel （未）导出字段信息表
 *
 * @author near on 2016/3/16.
 */
@Entity
@Table(name = "Elec_ExportFields")
public class ExportFields implements Serializable {

    /**
     * 指定所属模块的ID
     */
    private String belongTo;

    /**
     * 导出的中文字段
     */
    private String expNameList;

    /**
     * 导出的英文字段
     */
    private String expFieldName;

    /**
     * 未导出的中文字段
     */
    private String noExpNameList;

    /**
     * 未导出的英文字段
     */
    private String noExpFieldName;

    @Id
    @GeneratedValue(generator = "assigned")
    @GenericGenerator(name = "assigned", strategy = "assigned")
    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getExpNameList() {
        return expNameList;
    }

    public void setExpNameList(String expNameList) {
        this.expNameList = expNameList;
    }

    public String getExpFieldName() {
        return expFieldName;
    }

    public void setExpFieldName(String expFieldName) {
        this.expFieldName = expFieldName;
    }

    public String getNoExpNameList() {
        return noExpNameList;
    }

    public void setNoExpNameList(String noExpNameList) {
        this.noExpNameList = noExpNameList;
    }

    public String getNoExpFieldName() {
        return noExpFieldName;
    }

    public void setNoExpFieldName(String noExpFieldName) {
        this.noExpFieldName = noExpFieldName;
    }

}
