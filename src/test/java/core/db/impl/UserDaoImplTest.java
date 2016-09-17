package core.db.impl;


import core.TestHibernateUtil;
import core.db.entity.User;
import core.db.ints.UserDao;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoImplTest {

	private static final Logger logger = Logger.getLogger(UserDaoImplTest.class.getCanonicalName());
	private static SessionFactory sessionFactory;
	private UserDao userDao;

	@Before
	public void init() {
		logger.info("Initialization started");
		sessionFactory = TestHibernateUtil.getSessionFactory();
		userDao = new UserDaoImpl(sessionFactory);
	}

	@After
	public void clean() {
		logger.info("Cleaning started");
		userDao.deleteAll();
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

		Assert.assertEquals("newmail", afterUpdate.getEmail());
		Assert.assertTrue(countBefore + 1 == countAfter);
	}

	@Test
	public void testGetAll() {

		userDao.create(getUser(1));
		userDao.create(getUser(2));
		userDao.create(getUser(3));

		List<User> users = userDao.getAll();
		Assert.assertEquals(3, users.size());
	}

	@Test
	public void testCreate() {
		Long countBefore = userDao.countAll();
		userDao.create(getUser(1));
		Long countAfter = userDao.countAll();
		Assert.assertTrue(countBefore + 1 == countAfter);
	}

	@Test
	public void testDelete() {

		User user = getUser(1);
		userDao.create(user);
		userDao.create(getUser(2));
		userDao.create(getUser(3));

		Long count = userDao.countAll();
		Assert.assertTrue(3 == count);

		userDao.delete(user);
		count = userDao.countAll();
		List<User> users = userDao.getAll();

		Assert.assertEquals("email-2", users.get(0).getEmail());
		Assert.assertEquals("email-3", users.get(1).getEmail());
		Assert.assertTrue(2 == count);
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
		Assert.assertTrue(5 == count);
		Assert.assertTrue(3 == users.size());
		Assert.assertEquals("email-3", users.get(0).getEmail());
		Assert.assertEquals("email-4", users.get(1).getEmail());
		Assert.assertEquals("email-5", users.get(2).getEmail());
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
		Assert.assertTrue(5 == count);
		Assert.assertNotNull(user);
		Assert.assertEquals("email-1", user.getEmail());
		Assert.assertEquals("name-1", user.getName());
	}

	@Test
	public void testGetById() {

		userDao.create(getUser(1));
		userDao.create(getUser(2));
		userDao.create(getUser(3));
		userDao.create(getUser(4));
		userDao.create(getUser(5));

		User user = userDao.getById(3L);

		Assert.assertNotNull(user);
		Assert.assertEquals("email-3", user.getEmail());
		Assert.assertEquals("name-3", user.getName());
		Assert.assertEquals(3, user.getProjectId());
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