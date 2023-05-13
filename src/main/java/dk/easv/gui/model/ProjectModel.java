package dk.easv.gui.model;

// imports
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.ProjectLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectModel {
    ProjectLogic projectLogic = new ProjectLogic();
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    public ObservableList<Project> getProjects() {
        return projects;
    }

    public ProjectModel() {
        try {
            projects.addAll(projectLogic.readAllProjects());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void loadProjects() throws Exception {
        projects.clear();
        projects.addAll(projectLogic.readAllProjects());
    }

    public Project createProject(Project project) throws DatabaseException {
        Project p = projectLogic.createProject(project);
        projects.add(p);
        return p;
    }

    public void deleteProject(Project project) throws Exception {
        projectLogic.deleteProject(project);
    }

    public void insertImages(Project project, byte[] imageData) throws Exception {
        projectLogic.insertImages(project, imageData);
    }

    public void technicianProject(User user, Project project) throws Exception {
        projectLogic.technicianProject(user, project);
    }
}
