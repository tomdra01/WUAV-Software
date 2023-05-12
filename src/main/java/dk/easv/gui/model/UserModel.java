package dk.easv.gui.model;

// imports
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.UserLogic;
import dk.easv.bll.util.PasswordSecurity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserModel {
    UserLogic userLogic = new UserLogic();
    private final ObservableList<User> users = FXCollections.observableArrayList();

    public UserModel() {
        try {
            users.addAll(userLogic.readUsers());
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }


    public User createUser(User user) throws DatabaseException {
        // Create a new user and add them to the system
        User u = userLogic.createUser(user);
        users.add(u);
        return u; // Return the created user
    }

    public boolean isValidUser(String inputUsername, String inputPassword, String inputRole) {
        // Hash the input password for comparison
        String inputPasswordHash = PasswordSecurity.hashPassword(inputPassword);

        // Check if a user with the given credentials exists
        for (User user : users) {
            if (user.getUsername().equals(inputUsername)
                    && user.getPassword().equals(inputPasswordHash)
                    && user.getRole().equals(inputRole)) {
                return true; // User with matching credentials found
            }
        }
        return false; // No user with matching credentials found
    }

    public String getUserRole(String inputUsername) {
        // Retrieve the role of a user with the given username
        for (User user : users) {
            if (user.getUsername().equals(inputUsername)) {
                return user.getRole(); // Return the role of the user
            }
        }
        return null; // No user with the given username found
    }
}
