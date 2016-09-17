package core.db.impl;

import core.db.HibernateUtil;
import core.db.entity.User;
import core.db.ints.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

	private final Logger logger = Logger.getLogger(getClass().getName());
	private SessionFactory sessionFactory;

	// for dev
	public UserDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	// for tests
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// persist
	// -  will not execute an INSERT statement if it is called outside of transaction boundaries
	// - it doesn't guarantee that the identifier value will be assigned to the persistent instance immediately
	@Override
	public void create(User user) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(user);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			logger.info("Create error: " + ex.getLocalizedMessage());
		}
	}

	// delete
	@Override
	public void delete(User user) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.info("Delete error: " + ex.getLocalizedMessage());
		}
	}

	// HQL query
	@Override
	public List<User> getAll() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			return session.
					createQuery("from User").
					list();
		} catch (HibernateException ex) {
			logger.info("Select all error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	// get
	@Override
	public User getById(Long id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			User user = session.get(User.class, id);
			return user;
		} catch (HibernateException ex) {
			logger.info("Get by id error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	// update
	@Override
	public void update(User user) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.info("Update error: " + ex.getLocalizedMessage());
		}
	}


	// named query
	@Override
	public User getByEmailAndPassword(String email, String password) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<User> list = session.
					getNamedQuery("user.byEmailAndPassword").
					setParameter("email", email).
					setParameter("password", password).
					list();
			return list.size() > 1 ? null : list.get(0);
		} catch (HibernateException ex) {
			logger.info("Select user by nick and password: " + ex.getLocalizedMessage());
			return null;
		}
	}

	// query for pagination
	@Override
	public List<User> selectPage(int start, int count) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			return session.
					createQuery("from User").
					setFirstResult(start).
					setMaxResults(count).
					list();
		} catch (Exception ex) {
			logger.info("Select page error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Long countAll() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			return (Long) session.
					createQuery("select count(*) from User").
					uniqueResult();
		} catch (HibernateException ex) {
			logger.info("countAll error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Integer deleteAll() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			return session.
					createQuery("delete from User").
					executeUpdate();
		} catch (HibernateException ex) {
			logger.info("deleteAll error: " + ex.getLocalizedMessage());
			return null;
		}
	}
}
