package dk.easv.gui.model;

import dk.easv.be.roles.Admin;
import dk.easv.bll.logic.AdminLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AdminModel {
     AdminLogic adminLogic = new AdminLogic();

    private ObservableList<Admin> admins = FXCollections.observableArrayList();
    /**
     * Constructor,
     * fetches all the admin logins from the database
     */
    public AdminModel() {
        try {
            admins.addAll(adminLogic.getAllAdmins());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValid(String inputUsername, String inputPassword) {
        return admins.stream()
                .anyMatch(admin -> admin.getUsername().equals(inputUsername) && admin.getPassword().equals(inputPassword));
    }
}
