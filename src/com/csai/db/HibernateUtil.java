package com.csai.db;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    public static final ThreadLocal session = new ThreadLocal();
    static {
        try {
            // ���������ļ�hibernate.cfg.xml����SessionFactory
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("��ʼ��SessionFactory�ɹ���");
        } catch (Throwable ex) {
        	System.err.println("��ʼ��SessionFactoryʧ�ܡ�" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() {
    	Session s = (Session) session.get();
        // ���һ���µ�Session
        if (s == null) {
          s = sessionFactory.openSession();
          session.set(s);
        }
        return s;
    }
}
