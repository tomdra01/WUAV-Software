package dk.easv.dal.interfaces;

import dk.easv.be.roles.Admin;
import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public interface IUserDao {
    List<Admin> readAllAdmins() throws DatabaseException;
    List<Technician> readAllTechnicians() throws DatabaseException;
    Technician createTechnician(Technician technician) throws SQLException;
    List<ProjectManager> readAllProjectManagers() throws DatabaseException;
    ProjectManager createProjectManager(ProjectManager projectManager) throws SQLException;
    List<Salesman> readAllSalesmen() throws DatabaseException;
    Salesman createSalesman(Salesman salesman) throws SQLException;
}
