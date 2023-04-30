package dk.easv.gui.model;

import dk.easv.be.roles.Salesman;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.SalesmanLogic;
import dk.easv.bll.util.PasswordSecurity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SalesmanModel {
    SalesmanLogic salesmanLogic = new SalesmanLogic();
    private ObservableList<Salesman> salesmen = FXCollections.observableArrayList();

    /**
     * Constructor,
     * fetches all the technician logins from the database
     */
    public SalesmanModel() {
        try {
            salesmen.addAll(salesmanLogic.readAllSalesmen());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Salesman> getSalesmen() {
        return salesmen;
    }

    public void loadSalesmen() throws DatabaseException {
        salesmen.clear();
        salesmen.addAll(salesmanLogic.readAllSalesmen());
    }

    public Salesman createSalesman(Salesman salesman) throws SQLException {
        Salesman s = salesmanLogic.createSalesman(salesman);
        salesmen.add(s);
        return s;
    }

    /**
     * Checks if the 2 parameters are valid based on the credentials from the database
     */
    public boolean isValid(String inputUsername, String inputPassword) {
        String inputPasswordHash = PasswordSecurity.hashPassword(inputPassword);

        return salesmen.stream()
                .anyMatch(technician -> technician.getUsername().equals(inputUsername) && technician.getPassword().equals(inputPasswordHash));
    }
}
