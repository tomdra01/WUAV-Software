package dk.easv.bll.logic;

// imports
import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.DAOFactory;
import dk.easv.dal.DataAccessObjects;
import dk.easv.dal.dao.interfaces.IProjectDAO;

// java imports
import java.sql.SQLException;
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
            throw new DatabaseException("Failed to get all of the projects", e);
        }
    }

    public Project createProject(Project project) throws DatabaseException {
        try {
            return projectDAO.createProject(project);
        } catch (Exception e) {
            throw new DatabaseException("Failed to create Project", e);
        }
    }
}
