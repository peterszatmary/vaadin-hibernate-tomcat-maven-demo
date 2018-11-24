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
import java.util.Iterator;
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
		Assert.assertThat(office.getName(), IsEqual.equalTo("office-name-3"));
		Assert.assertThat(office.getUsers().size(), IsEqual.equalTo(2));

		LinkedHashSet usrs = new LinkedHashSet(office.getUsers());
		Iterator<User> it = usrs.iterator();
		Assert.assertThat(it.next().getName(), IsEqual.equalTo("user-name-0"));
		Assert.assertThat(it.next().getName(), IsEqual.equalTo("user-name-1"));

	}


	private Office getOffice(Integer num) {
		Office entity = new Office();
		entity.setName("office-name-" + num);

		Set<User> users = new LinkedHashSet<>();
		for (int i = 0; i < 2; i++) {
			users.add(getUser(i, entity));
		}
		entity.setUsers(users);
		return entity;
	}

	private User getUser(Integer num, Office office) {
		User user = new User();
		user.setName("user-name-" + num);
		user.setEmail("user-email-" + num);
		user.setPassword("user-password-" + num);
		user.setStatus(num);
		user.setContractEnd(new Date(System.currentTimeMillis()));
		user.setContractStart(new Date(System.currentTimeMillis() - num));
		user.setUserType(UserType.STUDENT);
		user.setOffice(office); // dont forget
		return user;
	}
}