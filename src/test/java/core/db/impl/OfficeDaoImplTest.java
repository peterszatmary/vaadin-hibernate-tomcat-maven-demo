package core.db.impl;


import core.TestHibernateUtil;
import core.db.entity.Office;
import core.db.entity.User;
import core.db.ints.OfficeDao;
import core.db.types.UserType;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

public class OfficeDaoImplTest {

	private static final Logger logger = Logger.getLogger(OfficeDaoImplTest.class.getCanonicalName());
	private static SessionFactory sessionFactory;
	private OfficeDao officeDao;

	@Before
	public void before() {
		sessionFactory = TestHibernateUtil.getSessionFactory();
		officeDao = new OfficeDaoImpl(sessionFactory);
	}

	@AfterClass
	public static void close() {
		logger.info("Closing started");
		sessionFactory.close();
	}

	@After
	public void clean() {
		logger.info("Cleaning started");
		officeDao.deleteAll();
	}


	@Test
	public void testCreate() {
		Long countBefore = officeDao.countAll();
		officeDao.create(getOffice(1));
		Long countAfter = officeDao.countAll();
		Assert.assertThat(countAfter, IsEqual.equalTo(countBefore + 1));
	}

	@Test
	public void testGetById() {

		officeDao.create(getOffice(1));
		officeDao.create(getOffice(2));
		officeDao.create(getOffice(3));
		officeDao.create(getOffice(4));
		officeDao.create(getOffice(5));

		Office office = officeDao.getById(3L);

		Assert.assertThat(office, IsNull.notNullValue());
		Assert.assertThat(office.getName(), IsEqual.equalTo("name-3"));
		Assert.assertThat(office.getUsers().size(), IsEqual.equalTo(2));
		Assert.assertThat(office.getUsers().iterator().next().getName(), IsEqual.equalTo("name-30"));
	}


	private Office getOffice(Integer num) {
		Office entity = new Office();
		entity.setName("name-" + num);

		User user;
		Set<User> users = new LinkedHashSet<>();
		for (int i = 0; i < 2; i++) {
			user = new User();
			user.setName("name-" + num + i);
			user.setEmail("email-" + num + i);
			user.setPassword("password-" + num + i);
			user.setStatus(num + i);
			user.setContractEnd(new Date(System.currentTimeMillis()));
			user.setContractStart(new Date(System.currentTimeMillis() - num + i));
			user.setUserType(UserType.STUDENT);
			user.setOffice(entity); // dont forget
			users.add(user);
		}
		entity.setUsers(users);
		return entity;
	}
}
