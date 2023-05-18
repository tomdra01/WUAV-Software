package dal;

// imports
import dk.easv.dal.DAOFactory;
import dk.easv.dal.DataAccessObjects;
import dk.easv.dal.IDataAccess;
import dk.easv.dal.dao.ProjectDAO;
import dk.easv.dal.dao.UserDAO;

// JUnit imports
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DAOFactoryTest {

    @DisplayName("Test get UserDAOTest")
    @Test
    void testGetUserDAO() {
        IDataAccess dataAccess = DAOFactory.getDAO(DataAccessObjects.USER_DAO);
        Assertions.assertNotNull(dataAccess);
        Assertions.assertEquals(dataAccess.getClass(), UserDAO.class);
    }

    @DisplayName("Test get ProjectDAO")
    @Test
    void testGetProjectDAO() {
        IDataAccess dataAccess = DAOFactory.getDAO(DataAccessObjects.PROJECT_DAO);
        Assertions.assertNotNull(dataAccess);
        Assertions.assertEquals(dataAccess.getClass(), ProjectDAO.class);
    }
}
