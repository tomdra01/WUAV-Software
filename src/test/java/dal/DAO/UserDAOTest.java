package dal.DAO;

// imports
import dk.easv.be.User;
import dk.easv.bll.logic.UserLogic;
import dk.easv.dal.database.DatabaseConnector;

// JUnit imports
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.sql.*;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserDAOTest {
    private static DatabaseConnector databaseConnector;

    @BeforeClass
    public static void setUp() {
        databaseConnector = new DatabaseConnector();
    }

    @DisplayName("Test create user")
    @Test
    public void testCreateUser() throws Exception {
        UserLogic userLogic = new UserLogic();
        User user = new User("testUser", "testPassword", "testRole");
        User createdUser = userLogic.createUser(user);

        Assertions.assertEquals(user.getUsername(), createdUser.getUsername(), "Username should match");
        Assertions.assertEquals(user.getPassword(), createdUser.getPassword(), "Password should match");
        Assertions.assertEquals(user.getRole(), createdUser.getRole(), "Role should match");

        String sql = "SELECT * FROM [User] WHERE id = ?";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement pst = con.prepareStatement(sql))
        {
            pst.setInt(1, createdUser.getId());
            try (ResultSet rs = pst.executeQuery()) {
                Assertions.assertTrue(rs.next(), "User should exist in the database");
                Assertions.assertEquals(user.getUsername(), rs.getString("username"), "Username should match in the database");
                Assertions.assertEquals(user.getPassword(), rs.getString("password"), "Password should match in the database");
                Assertions.assertEquals(user.getRole(), rs.getString("role"), "Role should match in the database");
            }
        }
    }
}
