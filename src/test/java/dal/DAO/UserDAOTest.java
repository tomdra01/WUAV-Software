package dal.DAO;

// imports
import dk.easv.be.User;
import dk.easv.dal.dao.UserDAO;

// JUnit imports
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.util.List;

// static imports
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserDAOTest {
    private UserDAO userDAO;

    @Before
    public void setup() {
        userDAO = new UserDAO();
    }

    @DisplayName("Test read users")
    @Test
    public void testReadUsers() {
        try {
            List<User> users = userDAO.readUsers();
            assertNotNull(users);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test create user")
    @Test
    public void testCreateUser() {
        try {
            User user = new User(0, "username", "password", "role");
            User createdUser = userDAO.createUser(user);
            assertNotNull(createdUser);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test edit user")
    @Test
    public void testEditUser() {
        try {
            User user = new User(1, "newUsername", "newPassword", "newRole");
            userDAO.editUser(user);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}