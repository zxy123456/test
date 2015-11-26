package com.csai.db;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    public static final ThreadLocal session = new ThreadLocal();
    static {
        try {
            // 根据配置文件hibernate.cfg.xml创建SessionFactory
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("初始化SessionFactory成功。");
        } catch (Throwable ex) {
        	System.err.println("初始化SessionFactory失败。" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() {
    	Session s = (Session) session.get();
        // 获得一个新的Session
        if (s == null) {
          s = sessionFactory.openSession();
          session.set(s);
        }
        return s;
    }
}
