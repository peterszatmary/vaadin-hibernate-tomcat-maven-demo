package core.db.impl;

import core.db.HibernateUtil;
import core.db.entity.Office;
import core.db.ints.OfficeDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Logger;


public class OfficeDaoImpl implements OfficeDao {

	private final Logger logger = Logger.getLogger(getClass().getName());
	private SessionFactory sessionFactory;

	// for dev
	public OfficeDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	// for tests
	public OfficeDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	// save
	// - will execute an INSERT statement called outside or inside of transaction boundaries
	// - identifier value will be assigned to the persistent instance immediately
	@Override
	public void create(Office office) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(office);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			logger.info("Create error: " + ex.getLocalizedMessage());
		}
	}

	@Override
	public void delete(Office office) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Office> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Office getById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Office office) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Office> selectPage(int start, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long countAll() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("select count(*) from Office");
			return (Long)query.uniqueResult();
		} catch (HibernateException ex) {
			logger.info("getCount error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Integer deleteAll() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("delete from Office");
			return query.executeUpdate();
		} catch (HibernateException ex) {
			logger.info("deleteAll error: " + ex.getLocalizedMessage());
			return null;
		}
	}
}
