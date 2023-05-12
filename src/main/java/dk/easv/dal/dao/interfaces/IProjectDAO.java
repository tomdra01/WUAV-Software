package dk.easv.dal.dao.interfaces;

// imports
import dk.easv.be.Project;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.IDataAccess;

// java imports
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public interface IProjectDAO extends IDataAccess {
    List<Project> readAllProjects() throws DatabaseException;
    Project createProject(Project project) throws DatabaseException;
    void insertImages(Project project, byte[] imageData) throws DatabaseException;
    void deleteProject(Project project) throws DatabaseException;
    void technicianProject(Technician technician, Project project) throws DatabaseException;
}
