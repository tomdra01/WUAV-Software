package dk.easv.dal.dao.interfaces;

// imports
import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.IDataAccess;

// java imports
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public interface IProjectDAO extends IDataAccess {
    List<Project> readAllProjects() throws DatabaseException;
    Project createProject(Project project) throws SQLException;
}
