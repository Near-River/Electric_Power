package pro_test;

import electric.entity.CommonMsg;
import electric.service.CommonMsgService;
import electric.util.UUIDUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;

public class TestSsh_01 extends BaseSpringTest {

    @Resource
    private DataSource dataSource;

    @Resource
    private SessionFactory factory;

    @Resource
    private CommonMsgService commonMsgService;

    @Test
    public void test() {
        System.out.println(dataSource.getClass().getName());
        System.out.println(factory.getClass().getName());
    }

    @Test
    public void test2() {
        CommonMsg commonMsg = new CommonMsg();
        commonMsg.setComId(UUIDUtils.getUUID());
        commonMsg.setCreateTime(new Date());
        System.out.println();
        // commonMsgService.save2(commonMsg);
        // System.out.println(DigestUtils.md5DigestAsHex("admin".getBytes()));

        String s = "aa@ab@ac@ad";
        System.out.println("\'" + s.replace("@", "\',\'") + "\'");
    }

    @Test
    @Deprecated
    /**
     * HQL 语句不支持下列测试的查询语句
     */
    public void test3() {
        String innerJoin = " INNER JOIN SystemDD dd ON u.sexID = dd.ddCode AND dd.keyword = ? " +
                " INNER JOIN SystemDD dd2 ON u.jctID = dd2.ddCode AND dd2.keyword = ? " +
                " INNER JOIN SystemDD dd3 ON u.postID = dd3.ddCode AND dd3.keyword = ? " +
                " INNER JOIN SystemDD dd4 ON u.isDuty = dd4.ddCode AND dd4.keyword = ? ";
        String select = "SELECT new electric.entity.User(userID, userName, loginName, contactTel, onDutyDate, " +
                " sexID, jctID,postID, dd4.ddValue AS isDuty) FROM User u ";

        Session session = factory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        session.createQuery(select + innerJoin)
                .setParameter(0, "性别").setParameter(1, "所属单位")
                .setParameter(2, "职位").setParameter(3, "是否在职").list();
        tx.commit();
        session.close();
    }

    @Test
    public void test4() {
        String hql = "SELECT\n" +
                "  u.userName,\n" +
                "  dd.ddValue sexID,\n" +
                "  dd2.ddValue jctID,\n" +
                "  dd3.ddValue postID,\n" +
                "  dd4.ddValue isDuty\n" +
                "FROM elec_user u INNER JOIN elec_systemdd dd ON u.sexID = dd.ddCode AND dd.keyword = \"性别\"\n" +
                "  INNER JOIN elec_systemdd dd2 ON u.jctID = dd2.ddCode AND dd2.keyword = \"所属单位\"\n" +
                "  INNER JOIN elec_systemdd dd3 ON u.postID = dd3.ddCode AND dd3.keyword = \"职位\"\n" +
                "  INNER JOIN elec_systemdd dd4 ON u.isDuty = dd4.ddCode AND dd4.keyword = \"是否在职\";";

        Session session = factory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        session.createSQLQuery(hql).list();
        tx.commit();
        session.close();
    }

}