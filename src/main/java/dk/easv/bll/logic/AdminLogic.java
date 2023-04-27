package dk.easv.bll.logic;

// imports
import dk.easv.be.roles.Admin;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.AdminDAO;

// java imports
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class AdminLogic {
    AdminDAO adminDAO = new AdminDAO();

    public List<Admin> readAllAdmins() throws DatabaseException {
        return adminDAO.readAllAdmins();
    }
}
