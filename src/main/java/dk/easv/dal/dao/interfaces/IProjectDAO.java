package dk.easv.dal.dao.interfaces;

// imports
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.dal.IDataAccess;

// java imports
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public interface IProjectDAO extends IDataAccess {
    List<Project> readAllProjects() throws Exception;
    List<Project> readTechnicianProjects(User user) throws Exception;
    List<Project> readSalesmanProjects() throws Exception;
    List<byte[]> getProjectImages(int projectId) throws Exception;
    Project createProject(Project project) throws Exception;
    void insertImages(Project project, byte[] imageData) throws Exception;
    void technicianProject(User user, Project project) throws Exception;
    void deleteProject(Project project) throws Exception;
    void updateProject(Project project) throws Exception;
    void updateApprovalStatus(Project project) throws Exception;
    void createLogEntry(Log log) throws Exception;
    List<Log> getAllLogs() throws Exception;
}
