package dk.easv.gui.model;

// imports
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.UserLogic;
import dk.easv.bll.util.PasswordSecurity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

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

    public ObservableList<User> getUsers() {
        return users;
    }

    public ObservableList<User> getTechnicians() {
        List<User> technicians = users.stream()
                .filter(user -> user.getRole().equals("Technician"))
                .toList();

        return FXCollections.observableArrayList(technicians);
    }

    public void createUser(User user) throws DatabaseException {
        // Create a new users and add them to the system
        User u = userLogic.createUser(user);
        users.add(u);
    }

    public User isValidUser(String inputUsername, String inputPassword, String inputRole) {
        for (User user : users) {
            if (user.getUsername().equals(inputUsername) && user.getRole().equals(inputRole)) {
                if (user.getPassword().startsWith("$2a$")) {
                    if (PasswordSecurity.checkPassword(inputPassword, user.getPassword())) {
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public String getUserRole(String inputUsername) {
        // Retrieve the role of a users with the given username
        for (User user : users) {
            if (user.getUsername().equals(inputUsername)) {
                return user.getRole(); // Return the role of the users
            }
        }
        return null; // No users with the given username found
    }

    public void editUser(User user) {
        try {
            userLogic.editUser(user);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
