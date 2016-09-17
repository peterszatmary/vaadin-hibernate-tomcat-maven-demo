package core.db.impl;

import core.db.HibernateUtil;
import core.db.entity.Project;
import core.db.ints.ProjectDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Logger;


public class ProjectDaoImpl implements ProjectDao {

	private final Logger logger = Logger.getLogger(getClass().getName());
	private SessionFactory sessionFactory;


	// for dev
	public ProjectDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	// for tests
	public ProjectDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void create(Project project) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(project);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			logger.info("Create error: " + ex.getLocalizedMessage());
		}
	}

	@Override
	public void delete(Project project) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Project> getAll() {
		throw new UnsupportedOperationException();
	}

	// load, dont hit db, just cache
	// get hit db
	@Override
	public Project getById(Long id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Project project = session.get(Project.class, id);
			return project;
		} catch (HibernateException ex) {
			logger.info("Get by id error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public void update(Project project) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Project> selectPage(int start, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long countAll() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			return (Long) session.
					createQuery("select count(*) from Project").
					uniqueResult();
		} catch (HibernateException ex) {
			logger.info("getCount error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Integer deleteAll() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			return session.
					createQuery("delete from Project").
					executeUpdate();
		} catch (HibernateException ex) {
			logger.info("deleteAll error: " + ex.getLocalizedMessage());
			return null;
		}
	}

}
