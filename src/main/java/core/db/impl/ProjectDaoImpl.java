package core.db.impl;

import core.db.HibernateUtil;
import core.db.entity.Project;
import core.db.ints.ProjectDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

	@Override
	public Project getById(Long id) {
		throw new UnsupportedOperationException();
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
			Query query = session.createQuery("select count(*) from Project");
			return (Long)query.uniqueResult();
		} catch(HibernateException ex) {
			logger.info("getCount error: " + ex.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Integer deleteAll() {
		throw new UnsupportedOperationException();
	}

}
