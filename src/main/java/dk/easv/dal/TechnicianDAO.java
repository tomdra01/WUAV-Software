package dk.easv.dal;

import dk.easv.be.roles.Technician;
import dk.easv.dal.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO {
    private DatabaseConnector databaseConnector;

    public TechnicianDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all technicians from the SQL database
     */
    public List<Technician> getAllTechnicians() throws SQLException {
        List<Technician> allTechnicians = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Technician;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");

                    Technician technician = new Technician(id, username, password);
                    allTechnicians.add(technician);
                }
            }
        }
        return allTechnicians;
    }
}
