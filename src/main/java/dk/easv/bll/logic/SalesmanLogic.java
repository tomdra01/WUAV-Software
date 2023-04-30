package dk.easv.bll.logic;

import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.SalesmanDAO;
import dk.easv.dal.TechnicianDAO;

import java.sql.SQLException;
import java.util.List;

public class SalesmanLogic {
    SalesmanDAO salesmanDAO = new SalesmanDAO();

    public List<Salesman> readAllSalesmen() throws DatabaseException {
        return salesmanDAO.readAllSalesmen();
    }

    public Salesman createSalesman(Salesman salesman) throws SQLException {
        return salesmanDAO.createSalesman(salesman);
    }
}
