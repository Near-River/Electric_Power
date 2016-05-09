package electric.entity.ck;

import java.io.Serializable;

/**
 * Popedom 的联合主键类
 * @author near on 2016/3/18.
 */
public class PopedomCK implements Serializable {

    /*权限ID*/
    private String mid;

    /*上级权限ID*/
    private String pid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
