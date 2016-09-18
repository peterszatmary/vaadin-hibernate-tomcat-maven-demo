package core.db.impl;


import core.TestHibernateUtil;
import core.db.entity.Office;
import core.db.entity.Project;
import core.db.entity.User;
import core.db.ints.UserDao;
import core.db.types.UserType;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoImplTest {

	private static final Logger logger = Logger.getLogger(UserDaoImplTest.class.getCanonicalName());
	private static SessionFactory sessionFactory;
	private UserDao userDao;

	@After
	public void after() {
		logger.info("Cleaning started");
		userDao.deleteAll();
	}

	@Before
	public void before() {
		sessionFactory = TestHibernateUtil.getSessionFactory();
		userDao = new UserDaoImpl(sessionFactory);
	}

	@AfterClass
	public static void close() {
		logger.info("Closing started");
		sessionFactory.close();
	}



	@Test
	public void testUpdate() {
		Long countBefore = userDao.countAll();
		User user = getUser(1);
		userDao.create(user);

		user.setEmail("newmail");

		userDao.update(user);

		User afterUpdate = userDao.getById(user.getId());
		Long countAfter = userDao.countAll();

		Assert.assertThat(afterUpdate.getEmail(), IsEqual.equalTo("newmail"));
		Assert.assertThat(countAfter, IsEqual.equalTo(countBefore + 1));
	}

	@Test
	public void testGetAll() {

		userDao.create(getUser(1));
		userDao.create(getUser(2));
		userDao.create(getUser(3));

		List<User> users = userDao.getAll();
		Assert.assertThat(users.size(), IsEqual.equalTo(3));
	}

	@Test
	public void testCreate() {
		Long countBefore = userDao.countAll();
		userDao.create(getUser(1));
		Long countAfter = userDao.countAll();
		Assert.assertThat(countAfter, IsEqual.equalTo(countBefore + 1));
	}

	@Test
	public void testDelete() {

		User user = getUser(1);
		userDao.create(user);
		userDao.create(getUser(2));
		userDao.create(getUser(3));

		Long count = userDao.countAll();
		Assert.assertThat(count, IsEqual.equalTo(3L));

		userDao.delete(user);
		count = userDao.countAll();
		List<User> users = userDao.getAll();

		Assert.assertThat(users.get(0).getEmail(), IsEqual.equalTo("email-2"));
		Assert.assertThat(users.get(1).getEmail(), IsEqual.equalTo("email-3"));
		Assert.assertThat(count, IsEqual.equalTo(2L));
	}

	@Test
	public void testSelectPage() {

		userDao.create(getUser(1));
		userDao.create(getUser(2));
		userDao.create(getUser(3));
		userDao.create(getUser(4));
		userDao.create(getUser(5));

		List<User> users = userDao.selectPage(2, 4);

		Long count = userDao.countAll();
		Assert.assertThat(count, IsEqual.equalTo(5L));
		Assert.assertThat(users.size(), IsEqual.equalTo(3));
		Assert.assertThat(users.get(0).getEmail(), IsEqual.equalTo("email-3"));
		Assert.assertThat(users.get(1).getEmail(), IsEqual.equalTo("email-4"));
		Assert.assertThat(users.get(2).getEmail(), IsEqual.equalTo("email-5"));
	}


	@Test
	public void testGetByEmailAndPassword() {

		userDao.create(getUser(1));
		userDao.create(getUser(2));
		userDao.create(getUser(3));
		userDao.create(getUser(4));
		userDao.create(getUser(5));

		User user = userDao.getByEmailAndPassword("email-1", "password-1");

		Long count = userDao.countAll();
		Assert.assertThat(count, IsEqual.equalTo(5L));
		Assert.assertThat(user, IsNull.notNullValue());
		Assert.assertThat(user.getEmail(), IsEqual.equalTo("email-1"));
		Assert.assertThat(user.getName(), IsEqual.equalTo("name-1"));
	}

	@Test
	public void testGetById() {

		userDao.create(getUser(1));
		userDao.create(getUser(2));
		userDao.create(getUser(3));
		userDao.create(getUser(4));
		userDao.create(getUser(5));

		User user = userDao.getById(3L);

		Assert.assertThat(user, IsNull.notNullValue());
		Assert.assertThat(user.getEmail(), IsEqual.equalTo("email-3"));
		Assert.assertThat(user.getName(), IsEqual.equalTo("name-3"));

		Assert.assertThat(user.getProjects().size(), IsEqual.equalTo(1));
		Assert.assertThat(user.getProjects().iterator().next().getName(), IsEqual.equalTo("name-3"));
	}


	private User getUser(Integer num) {

		User entity = new User();
		entity.setName("name-" + num);
		entity.setEmail("email-" + num);
		entity.setPassword("password-" + num);
		entity.setStatus(num);
		entity.setContractEnd(new Date(System.currentTimeMillis()));
		entity.setContractStart(new Date(System.currentTimeMillis() - num));
		entity.setUserType(UserType.WORKER);

		Office office = new Office();
		office.setName("office-name-" + num);

		entity.setOffice(office);

		Project project = new Project();
		project.setName("name-" + num);
		project.setDescription("description-" + num);
		project.setSuccessful(Boolean.FALSE);

		entity.setProjects(new LinkedHashSet<>(Arrays.asList(project)));
		return entity;
	}
}