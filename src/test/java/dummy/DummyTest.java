package dummy;

import core.TestHibernateUtil;
import core.db.entity.Office;
import org.hibernate.Session;
import org.junit.Test;

import java.util.logging.Logger;


public class DummyTest {

	private static Logger logger = Logger.getLogger("Test");

	@Test
	public void test() {
		logger.info("Open session");
		Session session = TestHibernateUtil.getSessionFactory().openSession();
		Office office = new Office();
		office.setName("New york");
		session.save(office);
		logger.info("Here we are before close()");
		session.close();
		logger.info("Here we are after close()");
	}
}
