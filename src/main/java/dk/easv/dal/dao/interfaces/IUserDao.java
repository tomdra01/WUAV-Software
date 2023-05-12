package dk.easv.dal.dao.interfaces;

// imports
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.IDataAccess;

// java imports
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public interface IUserDao extends IDataAccess {
    List<User> readUsers() throws DatabaseException;
    User createUser(User user) throws DatabaseException;
}
