package dk.easv.bll;

import dk.easv.be.roles.Technician;
import dk.easv.dal.TechnicianDAO;

import java.sql.SQLException;
import java.util.List;

public class TechnicianLogic {
    TechnicianDAO technicianDAO = new TechnicianDAO();

    public List<Technician> getAllTechnicians() throws SQLException {
        return technicianDAO.getAllTechnicians();
    }
}
