package dk.easv.gui.model;

// imports
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.ProjectLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectModel {
    ProjectLogic projectLogic = new ProjectLogic();
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final ObservableList<Project> technicianProjects = FXCollections.observableArrayList();
    private final ObservableList<Project> salesmenProjects = FXCollections.observableArrayList();

    public ObservableList<Project> getProjects() {
        return projects;
    }
    
    public ObservableList<Project> getTechnicianProjects() {
        return technicianProjects;
    }

    public ObservableList<Project> getSalesmenProjects() {
        return salesmenProjects;
    }

    public void loadProjects() throws DatabaseException {
        projects.clear();
        projects.addAll(projectLogic.readAllProjects());
    }

    public void loadTechnicianProjects(User user) throws DatabaseException {
        technicianProjects.clear();
        technicianProjects.addAll(projectLogic.readTechnicianProjects(user));
    }

    public void loadSalesmenProjects() throws DatabaseException {
        salesmenProjects.clear();
        salesmenProjects.addAll(projectLogic.readSalesmanProjects());
    }

    public void createProject(Project project) throws DatabaseException {
        Project p = projectLogic.createProject(project);
        projects.add(p);
    }

    public void insertImages(Project project, byte[] imageData) throws DatabaseException {
        projectLogic.insertImages(project, imageData);
    }

    public void technicianProject(User user, Project project) throws DatabaseException {
        projectLogic.technicianProject(user, project);
    }

    public void deleteProject(Project project) throws DatabaseException {
        projectLogic.deleteProject(project);
    }

    public void updateProject(Project project) throws DatabaseException {
        projectLogic.updateProject(project);
    }

    public void updateApprovalStatus(Project project) throws DatabaseException {
        projectLogic.updateApprovalStatus(project);
    }

    public List<byte[]> getProjectImages(int projectId) throws DatabaseException {
        return projectLogic.getProjectImages(projectId);
    }

    public void createLogEntry(Log log) throws DatabaseException {
        projectLogic.createLogEntry(log);
    }

    public List<Log> getAllLogs() throws DatabaseException {
        return projectLogic.getAllLogs();
    }
}
