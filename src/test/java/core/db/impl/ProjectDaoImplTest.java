package core.db.impl;


import core.TestHibernateUtil;
import core.db.entity.ExternalProject;
import core.db.entity.InternalProject;
import core.db.entity.Project;
import core.db.entity.User;
import core.db.ints.ProjectDao;
import core.db.types.UserType;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.LinkedHashSet;
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
		Assert.assertThat(project.getDescription(), IsEqual.equalTo("description-3"));
		Assert.assertThat(project.getName(), IsEqual.equalTo("name-3"));
		Assert.assertThat(project.getUsers().size(), IsEqual.equalTo(1));
		Assert.assertThat(project.getUsers().iterator().next().getName(), IsEqual.equalTo("name-3"));
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

		User user = new User();
		user.setName("name-" + num);
		user.setEmail("email-" + num);
		user.setPassword("password-" + num);
		user.setStatus(num);
		user.setContractEnd(new Date(System.currentTimeMillis()));
		user.setContractStart(new Date(System.currentTimeMillis() - num));
		user.setUserType(UserType.WORKER);
		user.setProjects(new LinkedHashSet<>(Arrays.asList(entity))); // dont forget

		entity.setUsers(new LinkedHashSet<>(Arrays.asList(user)));
		return entity;
	}
}
