package pro_test;

import electric.entity.SystemDD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.annotation.Resource;

public class TestCache extends BaseSpringTest {

    @Resource
    private SessionFactory factory;

    @Test
    public void test() {
        Transaction tx = null;
        Session session = null;
        try {
            System.out.println();
            session = factory.openSession();
            tx = session.beginTransaction();

            SystemDD systemDD = (SystemDD) session.get(SystemDD.class, 1);
            System.out.println(systemDD.getClass().hashCode());

            tx.commit();
            session.close();

            System.out.println("===========================");

            session = factory.openSession();
            tx = session.beginTransaction();

            SystemDD systemDD2 = (SystemDD) session.get(SystemDD.class, 1);
            System.out.println(systemDD2.getClass().hashCode());

            tx.commit();
            session.close();
        } catch (Exception e) {
            assert tx != null;
            tx.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Transaction tx = null;
        Session session = null;
        try {
            System.out.println();
            session = factory.openSession();
            tx = session.beginTransaction();
            session.createQuery("from SystemDD").setCacheable(true).list();
            tx.commit();
            session.close();

            System.out.println("===========================");

            session = factory.openSession();
            tx = session.beginTransaction();
            session.createQuery("from SystemDD").setCacheable(true).list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            assert tx != null;
            tx.rollback();
            e.printStackTrace();
        }
    }
}