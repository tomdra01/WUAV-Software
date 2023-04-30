package dk.easv.gui.model;

import dk.easv.be.roles.ProjectManager;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.ProjectManagerLogic;
import dk.easv.bll.util.PasswordSecurity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ProjectManagerModel {
    ProjectManagerLogic projectManagerLogic = new ProjectManagerLogic();
    private ObservableList<ProjectManager> projectManagers = FXCollections.observableArrayList();

    /**
     * Constructor,
     * fetches all the technician logins from the database
     */
    public ProjectManagerModel() {
        try {
            projectManagers.addAll(projectManagerLogic.readAllProjectManagers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProjectManager> getProjectManagers() {
        return projectManagers;
    }

    public void loadProjectManagers() throws DatabaseException {
        projectManagers.clear();
        projectManagers.addAll(projectManagerLogic.readAllProjectManagers());
    }

    public ProjectManager createProjectManager(ProjectManager projectManager) throws SQLException {
        ProjectManager pm = projectManagerLogic.createProjectManager(projectManager);
        projectManagers.add(pm);
        return pm;
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValid(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordSecurity.hashPassword(inputPassword);

        return projectManagers.stream()
                .anyMatch(technician -> technician.getUsername().equals(inputUsername) && technician.getPassword().equals(inputPasswordHash));
    }
}
