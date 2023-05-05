package dk.easv.bll.logic;

// imports
import dk.easv.be.roles.Admin;
import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.UserDAO;
import dk.easv.dal.interfaces.IUserDao;

// java imports
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserLogic {

    IUserDao userDao = new UserDAO();

    // ADMIN
    public List<Admin> readAllAdmins() throws DatabaseException {
        return userDao.readAllAdmins();
    }

    // TECHNICIAN
    public List<Technician> readAllTechnicians() throws DatabaseException {
        return userDao.readAllTechnicians();
    }

    public Technician createTechnician(Technician technician) throws SQLException {
        return userDao.createTechnician(technician);
    }

    // PROJECT MANAGER
    public List<ProjectManager> readAllProjectManagers() throws DatabaseException {
        return userDao.readAllProjectManagers();
    }

    public ProjectManager createProjectManager(ProjectManager projectManager) throws SQLException {
        return userDao.createProjectManager(projectManager);
    }

    // SALESMAN
    public List<Salesman> readAllSalesmen() throws DatabaseException {
        return userDao.readAllSalesmen();
    }

    public Salesman createSalesman(Salesman salesman) throws SQLException {
        return userDao.createSalesman(salesman);
    }
}
