package dk.easv.gui.model;

// imports
import dk.easv.be.roles.Admin;
import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.UserLogic;
import dk.easv.bll.util.PasswordSecurity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// java imports
import java.sql.SQLException;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserModel {
    UserLogic userLogic = new UserLogic();
    private ObservableList<Admin> admins = FXCollections.observableArrayList();
    private ObservableList<Technician> technicians = FXCollections.observableArrayList();
    private ObservableList<ProjectManager> projectManagers = FXCollections.observableArrayList();
    private ObservableList<Salesman> salesmen = FXCollections.observableArrayList();

    public UserModel() {
        try {
            admins.addAll(userLogic.readAllAdmins());
            technicians.addAll(userLogic.readAllTechnicians());
            projectManagers.addAll(userLogic.readAllProjectManagers());
            salesmen.addAll(userLogic.readAllSalesmen());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValidAdmin(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordSecurity.hashPassword(inputPassword);

        return admins.stream()
                .anyMatch(admin -> admin.getUsername().equals(inputUsername) && admin.getPassword().equals(inputPasswordHash));
    }

    public void loadTechnicians() throws DatabaseException {
        technicians.clear();
        technicians.addAll(userLogic.readAllTechnicians());
    }

    public ObservableList<Technician> getTechnicians() {
        return technicians;
    }

    public Technician createTechnician(Technician technician) throws SQLException {
        Technician t = userLogic.createTechnician(technician);
        technicians.add(t);
        return t;
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValidTechnician(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordSecurity.hashPassword(inputPassword);

        return technicians.stream()
                .anyMatch(technician -> technician.getUsername().equals(inputUsername) && technician.getPassword().equals(inputPasswordHash));
    }

    public void loadProjectManagers() throws DatabaseException {
        projectManagers.clear();
        projectManagers.addAll(userLogic.readAllProjectManagers());
    }

    public ProjectManager createProjectManager(ProjectManager projectManager) throws SQLException {
        ProjectManager pm = userLogic.createProjectManager(projectManager);
        projectManagers.add(pm);
        return pm;
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValidProjectManager(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordSecurity.hashPassword(inputPassword);

        return projectManagers.stream()
                .anyMatch(technician -> technician.getUsername().equals(inputUsername) && technician.getPassword().equals(inputPasswordHash));
    }

    public void loadSalesmen() throws DatabaseException {
        salesmen.clear();
        salesmen.addAll(userLogic.readAllSalesmen());
    }

    public Salesman createSalesman(Salesman salesman) throws SQLException {
        Salesman s = userLogic.createSalesman(salesman);
        salesmen.add(s);
        return s;
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValidSalesman(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordSecurity.hashPassword(inputPassword);

        return salesmen.stream()
                .anyMatch(technician -> technician.getUsername().equals(inputUsername) && technician.getPassword().equals(inputPasswordHash));
    }
}
