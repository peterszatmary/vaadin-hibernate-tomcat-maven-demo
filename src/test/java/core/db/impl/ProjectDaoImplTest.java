package core.db.impl;


import core.TestHibernateUtil;
import core.db.entity.ExternalProject;
import core.db.entity.InternalProject;
import core.db.entity.Project;
import core.db.ints.ProjectDao;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.logging.Logger;

public class ProjectDaoImplTest {

	private static final Logger logger = Logger.getLogger(ProjectDaoImplTest.class.getCanonicalName());
	private static SessionFactory sessionFactory;
	private ProjectDao projectDao;


	@Before
	public void before() {
		sessionFactory = TestHibernateUtil.getSessionFactory();
		projectDao = new ProjectDaoImpl(sessionFactory);
	}

	@AfterClass
	public static void close() {
		logger.info("Closing started");
		sessionFactory.close();
	}

	@After
	public void clean() {
		logger.info("Cleaning started");
		projectDao.deleteAll();
	}

	@Test
	public void testGetById() {

		projectDao.create(getProject(1, Project.class));
		projectDao.create(getProject(2, ExternalProject.class));
		projectDao.create(getProject(3, InternalProject.class));
		projectDao.create(getProject(4, ExternalProject.class));
		projectDao.create(getProject(5, Project.class));

		Project project = projectDao.getById(3L);

		Assert.assertThat(project, IsNull.notNullValue());
		Assert.assertThat("description-3", IsEqual.equalTo(project.getDescription()));
		Assert.assertThat("name-3", IsEqual.equalTo(project.getName()));
	}

	private Project getProject(Integer num, Class type) {

		Boolean status = num % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;

		Project entity;
		if(ExternalProject.class.equals(type)) {
			entity = new ExternalProject();
		} else if (InternalProject.class.equals(type)) {
			entity = new InternalProject();
		} else {
			entity = new Project();
		}

		entity.setName("name-" + num);
		entity.setDescription("description-" + num);
		entity.setSuccessful(status);
		entity.setProjectStart(new Date(System.currentTimeMillis()));
		entity.setProjectEnd(new Date(System.currentTimeMillis() - num));
		return entity;
	}
}
