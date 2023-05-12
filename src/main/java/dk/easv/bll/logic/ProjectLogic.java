package dk.easv.bll.logic;

// imports
import dk.easv.be.Project;
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

    public List<Project> readAllProjects() throws Exception {
        return projectDAO.readAllProjects();
    }

    public Project createProject(Project project) throws DatabaseException {
        return projectDAO.createProject(project);
    }

    public void deleteProject(Project project) throws Exception {
        projectDAO.deleteProject(project);
    }

    public void insertImages(Project project, byte[] imageData) throws Exception {
        projectDAO.insertImages(project, imageData);
    }

    /**
     *public void technicianProject(Technician technician, Project project) throws Exception {
     *         projectDAO.technicianProject(technician, project);
     *     }
     * @author tomdra01, mrtng1
     */
}
