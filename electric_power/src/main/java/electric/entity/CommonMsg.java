package electric.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 设备&站点 运行监控信息
 *
 * @author near on 2016/3/15.
 */
@Entity
@Table(name = "Elec_CommonMsg")
public class CommonMsg implements Serializable {

    /**
     * 主键 UUID
     */
    private String comId;

    /**
     * 站点运行情况
     */
    private String stationRun;

    /**
     * 设备运行情况
     */
    private String devRun;

    /**
     * 创建日期
     */
    private Date createTime;

    @Id
    @Column(length = 100)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    @Column(length = 5000)
    public String getStationRun() {
        return stationRun;
    }

    public void setStationRun(String stationRun) {
        this.stationRun = stationRun;
    }

    @Column(length = 5000)
    public String getDevRun() {
        return devRun;
    }

    public void setDevRun(String devRun) {
        this.devRun = devRun;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
