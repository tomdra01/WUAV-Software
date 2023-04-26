package dk.easv.dal;

// imports
import dk.easv.be.roles.Admin;
import dk.easv.dal.database.DatabaseConnector;

// java imports
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class AdminDAO {
    private DatabaseConnector databaseConnector;

    public AdminDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all admins from the database.
     */
    public List<Admin> getAllAdmins() throws SQLException {
        List<Admin> allAdmins = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Admin;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {

                    Admin admin = new Admin(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    );

                    allAdmins.add(admin);
                }
            }
        }
        return allAdmins;
    }
}
