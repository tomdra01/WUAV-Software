package dk.easv.dal;

import dk.easv.be.roles.Admin;
import dk.easv.be.roles.Technician;
import dk.easv.dal.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private DatabaseConnector databaseConnector;

    public AdminDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all admins from the SQL database
     */
    public List<Admin> getAllAdmins() throws SQLException {
        List<Admin> allAdmins = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Admin;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");

                    Admin admin = new Admin(id, username, password);
                    allAdmins.add(admin);
                }
            }
        }
        return allAdmins;
    }
}
