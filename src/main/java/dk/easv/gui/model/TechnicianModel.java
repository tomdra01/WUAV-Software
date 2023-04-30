package dk.easv.gui.model;

// imports
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.TechnicianLogic;
import dk.easv.bll.util.PasswordHasher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// java imports
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

public class TechnicianModel {
    TechnicianLogic technicianLogic = new TechnicianLogic();
    private ObservableList<Technician> technicians = FXCollections.observableArrayList();

    /**
     * Constructor,
     * fetches all the technician logins from the database
     */
    public TechnicianModel() {
        try {
            technicians.addAll(technicianLogic.readAllTechnicians());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Technician> getTechnicians() {
        return technicians;
    }

    public void loadTechnicians() throws DatabaseException {
        technicians.clear();
        technicians.addAll(technicianLogic.readAllTechnicians());
    }

    public Technician createTechnician(Technician technician) throws SQLException {
        Technician t = technicianLogic.createTechnician(technician);
        technicians.add(t);
        return t;
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValid(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordHasher.hashPassword(inputPassword);

        return technicians.stream()
                .anyMatch(technician -> technician.getUsername().equals(inputUsername) && technician.getPassword().equals(inputPasswordHash));
    }
}
