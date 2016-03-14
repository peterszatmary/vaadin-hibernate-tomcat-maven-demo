package core.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	//private static final ServiceRegistry serviceRegistry;

	static {
		try {
			sessionFactory = new Configuration()
					.configure("/hbnt/hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
