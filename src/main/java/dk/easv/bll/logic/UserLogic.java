package dk.easv.bll.logic;

// imports
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.DAOFactory;
import dk.easv.dal.DataAccessObjects;
import dk.easv.dal.dao.interfaces.IUserDao;

// java imports
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserLogic {
    IUserDao userDao = (IUserDao) DAOFactory.getDAO(DataAccessObjects.USER_DAO);

    public List<User> readUsers() throws DatabaseException {
        try {
            return userDao.readUsers();
        } catch (Exception e) {
            throw new DatabaseException("Failed to read all of the users", e);
        }
    }

    public User createUser(User user) throws DatabaseException {
        try {
            return userDao.createUser(user);
        } catch (Exception e) {
            throw new DatabaseException("Failed to create the user", e);
        }
    }
}
