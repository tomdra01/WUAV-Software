package dk.easv.bll;

import dk.easv.be.roles.Admin;
import dk.easv.dal.AdminDAO;

import java.sql.SQLException;
import java.util.List;

public class AdminLogic {
    AdminDAO adminDAO = new AdminDAO();

    public List<Admin> getAllAdmins() throws SQLException {
        return adminDAO.getAllAdmins();
    }
}
