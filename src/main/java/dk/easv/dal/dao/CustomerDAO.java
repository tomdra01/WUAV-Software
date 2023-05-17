package dk.easv.dal.dao;

// imports
import dk.easv.dal.database.DatabaseConnector;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CustomerDAO {
    private DatabaseConnector databaseConnector;
    public CustomerDAO() {
        databaseConnector = new DatabaseConnector();
    }
}
