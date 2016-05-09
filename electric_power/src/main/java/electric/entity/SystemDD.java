package electric.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统数据字典表（动态维护系统的基本数据项）
 * 存放系统所有的元数据
 * --> 维护系统表，在涉及到数据字典字段的地方，存放的是对应数据项编号
 * 提高安全性、方便系统做统计工作
 *
 * @author near on 2016/3/16.
 */
@Entity
@Table(name = "Elec_SystemDD")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemDD implements Serializable {

    /**
     * 主键ID(自增长)
     */
    private Integer id;

    /**
     * 数据类型：
     * 例如：性别
     */
    private String keyword;

    /**
     * 数据项编号
     */
    private Integer ddCode;

    /**
     * 数据项的值
     */
    private String ddValue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getDdCode() {
        return ddCode;
    }

    public void setDdCode(Integer ddCode) {
        this.ddCode = ddCode;
    }

    public String getDdValue() {
        return ddValue;
    }

    public void setDdValue(String ddValue) {
        this.ddValue = ddValue;
    }

}
