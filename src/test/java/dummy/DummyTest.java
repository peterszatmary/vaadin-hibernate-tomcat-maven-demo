package dummy;

import core.db.HibernateUtil;
import core.db.entity.Office;
import org.hibernate.Session;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * Created by nue on 7.3.2016.
 */
public class DummyTest {

    private static Logger logger = Logger.getLogger("Test");

    @Test
    public void test() {
        logger.info("Open session");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Office office = new Office();
        office.setName("Kosice2");
        session.save(office);
        logger.info("Here we are before close()");
        session.close();
        logger.info("Here we are after close()");
    }

}
