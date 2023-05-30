package gui.model;

// imports
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.gui.model.UserModel;
import javafx.collections.ObservableList;

// JUnit imports
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// static imports
import static org.junit.jupiter.api.Assertions.*;

// java imports
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserModelTest {

    private UserModel userModel;
    private List<User> userList;

    @BeforeEach
    public void setup(){
        userList = new ArrayList<>();
        userList.add(new User("John", "john123", "Technician"));
        userList.add(new User("Jane", "jane456", "Admin"));

        userModel = new UserModel();
    }

    @DisplayName("Test create user")
    @Test
    public void testCreateUser() throws DatabaseException {
        User newUser = new User("Bob", "bob789", "Technician");
        userModel.createUser(newUser);

        ObservableList<User> users = userModel.getUsers();
        assertTrue(users.contains(newUser));
    }

    @DisplayName("Test isValidUser")

    @Test
    public void testIsValidUser_InvalidCredentials() {
        User invalidUser = userModel.isValidUser("John", "wrongpassword", "Technician");
        assertNull(invalidUser);
    }

    @DisplayName("Test getUserRole")
    @Test
    public void testGetUserRole_NonExistingUser() {
        String role = userModel.getUserRole("NonExistingUser");
        assertNull(role);
    }
}