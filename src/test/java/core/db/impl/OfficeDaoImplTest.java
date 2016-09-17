package core.db.impl;


import core.TestHibernateUtil;
import core.db.entity.Office;
import core.db.ints.OfficeDao;
import org.hamcrest.core.IsEqual;
import org.hibernate.SessionFactory;
import org.junit.*;

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


	private Office getOffice(Integer num) {
		Office entity = new Office();
		entity.setName("name-" + num);
		return entity;
	}
}
