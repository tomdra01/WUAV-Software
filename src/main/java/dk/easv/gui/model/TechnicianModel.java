package dk.easv.gui.model;

import dk.easv.be.roles.Technician;
import dk.easv.bll.TechnicianLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class TechnicianModel {

    TechnicianLogic technicianLogic = new TechnicianLogic();

    private ObservableList<Technician> technicians = FXCollections.observableArrayList();
    /**
     * Constructor,
     * fetches all the technician logins from the database
     */
    public TechnicianModel() {
        try {
            technicians.addAll(technicianLogic.getAllTechnicians());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValidAdmin(String inputUsername, String inputPassword) {
        return technicians.stream()
                .anyMatch(technician -> technician.getUsername().equals(inputUsername) && technician.getPassword().equals(inputPassword));
    }
}
