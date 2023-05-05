package dk.easv.gui.model;

import dk.easv.be.Project;
import dk.easv.be.roles.Admin;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.ProjectLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ProjectModel {
    ProjectLogic projectLogic = new ProjectLogic();

    private ObservableList<Project> projects = FXCollections.observableArrayList();
    public ObservableList<Project> getProjects() {
        return projects;
    }

    public ProjectModel() {
        try {
            projects.addAll(projectLogic.readAllProjects());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadProjects() throws DatabaseException {
        projects.clear();
        projects.addAll(projectLogic.readAllProjects());
    }

    public Project createProject(Project project) throws Exception {
        Project p = projectLogic.createProject(project);
        projects.add(p);
        return p;
    }

}
