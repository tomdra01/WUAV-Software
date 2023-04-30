package dk.easv.dal;

// imports
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.database.DatabaseConnector;

// java imports
import java.sql.*;
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

    public Technician createTechnician(Technician technician) throws SQLException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO Technician (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, technician.getUsername());
            pst.setString(2, technician.getPassword());
            pst.execute();

            if (pst.getGeneratedKeys().next()) {
                int id = pst.getGeneratedKeys().getInt(1);
                technician.setId(id);
                return technician;
            }
        }
        throw new RuntimeException("Id not set");
    }
}
