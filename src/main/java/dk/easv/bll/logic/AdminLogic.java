package dk.easv.bll.logic;

// imports
import dk.easv.be.roles.Admin;
import dk.easv.dal.AdminDAO;

// java imports
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class AdminLogic {
    AdminDAO adminDAO = new AdminDAO();

    public List<Admin> getAllAdmins() throws SQLException {
        return adminDAO.getAllAdmins();
    }
}
