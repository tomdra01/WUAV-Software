package dk.easv.bll.logic;

// imports
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.DAOFactory;
import dk.easv.dal.DataAccessObjects;
import dk.easv.dal.dao.interfaces.IProjectDAO;

// java imports
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectLogic {
    IProjectDAO projectDAO = (IProjectDAO) DAOFactory.getDAO(DataAccessObjects.PROJECT_DAO);

    public List<Project> readAllProjects() throws DatabaseException {
        try {
            return projectDAO.readAllProjects();
        } catch (Exception e) {
            throw new DatabaseException("Failed to read all of the projects", e);
        }
    }

    public List<Project> readTechnicianProjects(User user) throws DatabaseException {
        try {
            return projectDAO.readTechnicianProjects(user);
        } catch (Exception e) {
            throw new DatabaseException("Failed to read all of the technician projects", e);
        }
    }

    public List<Project> readSalesmanProjects() throws DatabaseException {
        try {
            return projectDAO.readSalesmanProjects();
        } catch (Exception e) {
            throw new DatabaseException("Failed to read all of the salesman projects", e);
        }
    }

    public List<byte[]> getProjectImages(int projectId) throws DatabaseException {
        try {
            return projectDAO.getProjectImages(projectId);
        } catch (Exception e) {
            throw new DatabaseException("Failed to get project images", e);
        }
    }

    public Project createProject(Project project) throws DatabaseException {
        try {
            return projectDAO.createProject(project);
        } catch (Exception e) {
            throw new DatabaseException("Failed to create the project", e);
        }
    }

    public void insertImages(Project project, byte[] imageData) throws DatabaseException {
        try {
            projectDAO.insertImages(project, imageData);
        } catch (Exception e) {
            throw new DatabaseException("Failed to insert images", e);
        }
    }

    public void technicianProject(User user, Project project) throws DatabaseException {
        try {
            projectDAO.technicianProject(user, project);
        } catch (Exception e) {
            throw new DatabaseException("Failed to assign project to the technician", e);
        }
    }

    public void deleteProject(Project project) throws DatabaseException {
        try {
            projectDAO.deleteProject(project);
        } catch (Exception e) {
            throw new DatabaseException("Failed to delete the project", e);
        }
    }

    public void updateProject(Project project) throws DatabaseException {
        try {
            projectDAO.updateProject(project);
        } catch (Exception e) {
            throw new DatabaseException("Failed to update the project", e);
        }
    }

    public void updateApprovalStatus(Project project) throws DatabaseException {
        try {
            projectDAO.updateApprovalStatus(project);
        } catch (Exception e) {
            throw new DatabaseException("Failed to update approval status", e);
        }
    }

    public void createLogEntry(Log log) throws DatabaseException {
        try {
            projectDAO.createLogEntry(log);
        } catch (Exception e) {
            throw new DatabaseException("Failed to create a log entry", e);
        }
    }

    public List<Log> getAllLogs() throws DatabaseException {
        try {
            return projectDAO.getAllLogs();
        } catch (Exception e) {
            throw new DatabaseException("Failed to get all logs from the database", e);
        }
    }
}
