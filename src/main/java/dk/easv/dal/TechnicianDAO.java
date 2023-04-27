package dk.easv.dal;

// imports
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
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
public class TechnicianDAO {
    private DatabaseConnector databaseConnector;
    public TechnicianDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all technicians from the database.
     */
    public List<Technician> readAllTechnicians() throws DatabaseException {
        List<Technician> allTechnicians = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Technician;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {

                    Technician technician = new Technician(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    );

                    allTechnicians.add(technician);
                }
            }
        } catch(SQLException e){
            throw new DatabaseException("Couldn't get all admins... Check database connection!", e);
        }
        return allTechnicians;
    }
}
