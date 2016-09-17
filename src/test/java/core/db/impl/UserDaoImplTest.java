package core.db.impl;


import core.TestHibernateUtil;
import core.db.entity.User;
import core.db.ints.UserDao;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
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

		Assert.assertThat("newmail", IsEqual.equalTo(afterUpdate.getEmail()));
		Assert.assertThat(countBefore + 1, IsEqual.equalTo(countAfter));
	}

	@Test
	public void testGetAll() {

		userDao.create(getUser(1));
		userDao.create(getUser(2));
		userDao.create(getUser(3));

		List<User> users = userDao.getAll();
		Assert.assertThat(3, IsEqual.equalTo(users.size()));
	}

	@Test
	public void testCreate() {
		Long countBefore = userDao.countAll();
		userDao.create(getUser(1));
		Long countAfter = userDao.countAll();
		Assert.assertThat(countBefore + 1, IsEqual.equalTo(countAfter));
	}

	@Test
	public void testDelete() {

		User user = getUser(1);
		userDao.create(user);
		userDao.create(getUser(2));
		userDao.create(getUser(3));

		Long count = userDao.countAll();
		Assert.assertThat(3L, IsEqual.equalTo(count));

		userDao.delete(user);
		count = userDao.countAll();
		List<User> users = userDao.getAll();

		Assert.assertThat("email-2", IsEqual.equalTo(users.get(0).getEmail()));
		Assert.assertThat("email-3", IsEqual.equalTo(users.get(1).getEmail()));
		Assert.assertThat(2L, IsEqual.equalTo(count));
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
		Assert.assertThat(5L, IsEqual.equalTo(count));
		Assert.assertThat(3, IsEqual.equalTo(users.size()));
		Assert.assertThat("email-3", IsEqual.equalTo(users.get(0).getEmail()));
		Assert.assertThat("email-4", IsEqual.equalTo(users.get(1).getEmail()));
		Assert.assertThat("email-5", IsEqual.equalTo(users.get(2).getEmail()));
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
		Assert.assertThat(5L, IsEqual.equalTo(count));
		Assert.assertThat(user, IsNull.notNullValue());
		Assert.assertThat("email-1", IsEqual.equalTo(user.getEmail()));
		Assert.assertThat("name-1", IsEqual.equalTo(user.getName()));
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
		Assert.assertThat("email-3", IsEqual.equalTo(user.getEmail()));
		Assert.assertThat("name-3", IsEqual.equalTo(user.getName()));
		Assert.assertThat(3, IsEqual.equalTo(user.getProjectId()));
	}


	private User getUser(Integer num) {

		User entity = new User();
		entity.setName("name-" + num);
		entity.setEmail("email-" + num);
		entity.setPassword("password-" + num);
		entity.setProjectId(num);
		entity.setStatus(num);
		entity.setContractEnd(new Date(System.currentTimeMillis()));
		entity.setContractStart(new Date(System.currentTimeMillis() - num));
		return entity;
	}
}