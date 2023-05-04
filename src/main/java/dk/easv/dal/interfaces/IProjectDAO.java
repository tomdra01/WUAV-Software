package dk.easv.dal.interfaces;

import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public interface IProjectDAO {
    List<Project> readAllProjects() throws DatabaseException;
    Project createProject(Project project) throws SQLException;
}
