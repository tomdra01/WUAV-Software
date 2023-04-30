package dk.easv.gui.model;

// imports
import dk.easv.be.roles.Admin;
import dk.easv.bll.logic.AdminLogic;
import dk.easv.bll.util.PasswordHasher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// java imports
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author tomdra01, mrtng1
 */
public class AdminModel {
    AdminLogic adminLogic = new AdminLogic();
    private ObservableList<Admin> admins = FXCollections.observableArrayList();

    /**
     * Constructor,
     * fetches all the admin logins from the database
     */
    public AdminModel() {
        try {
            admins.addAll(adminLogic.readAllAdmins());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValid(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordHasher.hashPassword(inputPassword);

        return admins.stream()
                .anyMatch(admin -> admin.getUsername().equals(inputUsername) && admin.getPassword().equals(inputPasswordHash));
    }
}
