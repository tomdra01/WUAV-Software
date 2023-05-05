package dk.easv.dal;

// imports
import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.database.DatabaseConnector;
import dk.easv.dal.interfaces.IProjectDAO;

// java imports
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectDAO implements IProjectDAO {
    private DatabaseConnector databaseConnector;
    public ProjectDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all projects from the database.
     */
    public List<Project> readAllProjects() throws DatabaseException {
        List<Project> allProjects = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Project;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {

                    Project project = new Project(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("businessType"),
                            resultSet.getString("location"),
                            resultSet.getDate("date").toLocalDate(),
                            resultSet.getBytes("drawing"),
                            resultSet.getString("description")
                    );

                    allProjects.add(project);
                }
            }
        }
        catch(SQLException e){
            throw new DatabaseException("Couldn't get all projects... Check the database connection!", e);
        }
        return allProjects;
    }

    public Project createProject(Project project) throws SQLException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO Project (name, businessType, location, date, drawing, description) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, project.getName());
            pst.setString(2, project.getBusinessType());
            pst.setString(3, project.getLocation());
            pst.setDate(4, Date.valueOf(project.getDate()));
            pst.setBytes(5, project.getDrawing());
            pst.setString(6, project.getDescription());
            pst.execute();

            if (pst.getGeneratedKeys().next()) {
                int id = pst.getGeneratedKeys().getInt(1);
                project.setId(id);
                return project;
            }
        }
        throw new RuntimeException("Id not set");
    }
}
