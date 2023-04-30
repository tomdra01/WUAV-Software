package dk.easv.dal;

import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesmanDAO {
    private DatabaseConnector databaseConnector;
    public SalesmanDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all technicians from the database.
     */
    public List<Salesman> readAllSalesmen() throws DatabaseException {
        List<Salesman> allSalesmen = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Salesman;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {

                    Salesman salesman = new Salesman(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    );

                    allSalesmen.add(salesman);
                }
            }
        } catch(SQLException e){
            throw new DatabaseException("Couldn't get all admins... Check database connection!", e);
        }
        return allSalesmen;
    }

    public Salesman createSalesman(Salesman salesman) throws SQLException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO Salesman (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, salesman.getUsername());
            pst.setString(2, salesman.getPassword());
            pst.execute();

            if (pst.getGeneratedKeys().next()) {
                int id = pst.getGeneratedKeys().getInt(1);
                salesman.setId(id);
                return salesman;
            }
        }
        throw new RuntimeException("Id not set");
    }
}
