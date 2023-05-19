package dk.easv.dal;

//imports
import dk.easv.dal.dao.CustomerDAO;
import dk.easv.dal.dao.ProjectDAO;
import dk.easv.dal.dao.UserDAO;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DAOFactory {
    public static IDataAccess getDAO(DataAccessObjects dataAccessObjects) {
        return switch (dataAccessObjects) {
            case USER_DAO -> new UserDAO();
            case PROJECT_DAO -> new ProjectDAO();
            case CUSTOMER_DAO -> new CustomerDAO();
        };
    }
}
