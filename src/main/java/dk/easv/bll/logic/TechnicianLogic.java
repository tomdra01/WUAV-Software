package dk.easv.bll.logic;

// imports
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.TechnicianDAO;

// java imports
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class TechnicianLogic {
    TechnicianDAO technicianDAO = new TechnicianDAO();

    public List<Technician> readAllTechnicians() throws DatabaseException {
        return technicianDAO.readAllTechnicians();
    }

    public Technician createTechnician(Technician technician) throws SQLException {
        return technicianDAO.createTechnician(technician);
    }
}
