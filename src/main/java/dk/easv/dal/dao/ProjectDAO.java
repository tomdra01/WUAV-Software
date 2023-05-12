package dk.easv.dal.dao;

// imports
import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.database.DatabaseConnector;
import dk.easv.dal.dao.interfaces.IProjectDAO;

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
     * @throws DatabaseException to handles SQLException.
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
                            resultSet.getString("description"),
                            resultSet.getBoolean("approved")
                    );

                    allProjects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get all projects", e);
        }
        return allProjects;
    }

    /**
     * Creates project in the database.
     * @param project sends object "Project" as a parameter.
     * @throws DatabaseException to handles SQLException.
     */
    public Project createProject(Project project) throws DatabaseException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO Project (name, businessType, location, date, drawing, description, approved) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, project.getName());
            pst.setString(2, project.getBusinessType());
            pst.setString(3, project.getLocation());
            pst.setDate(4, Date.valueOf(project.getDate()));
            pst.setBytes(5, project.getDrawing());
            pst.setString(6, project.getDescription());
            pst.setBoolean(7, project.isApproved());
            pst.execute();

            if (pst.getGeneratedKeys().next()) {
                int id = pst.getGeneratedKeys().getInt(1);
                project.setId(id);
                return project;
            }
            throw new RuntimeException("Id not set");

        } catch (SQLException e) {
            throw new DatabaseException("Failed to create the project", e);
        }

    }

    /**
     * Deletes the project
     * @param project sends object "Project" as a parameter.
     * @throws DatabaseException to handles SQLException.
     */
    public void deleteProject(Project project) throws DatabaseException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pstEventCustomer = con.prepareStatement("DELETE FROM Project WHERE id = ?");
            pstEventCustomer.setInt(1, project.getId());
            pstEventCustomer.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete the project", e);
        }
    }

    /**
     * Inserts images according to specific project.
     * @param project sends object "Project" as a parameter.
     * @param imageData sends image bytes.
     * @throws DatabaseException to handles SQLException.
     */
    public void insertImages(Project project, byte[] imageData) throws DatabaseException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO ProjectPhotos(project_id, imageData) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, project.getId());
            pst.setBytes(2, imageData);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to insert images", e);
        }
    }

    /**
     * Assigns technician to the project.
     * @param technician sends object "Technician" as a parameter.
     * @param project sends object "Project" as a parameter.
     * @throws DatabaseException to handles SQLException.

    public void technicianProject(Technician technician, Project project) throws DatabaseException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO TechnicianProject(technician_id, project_id) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, technician.getId());
            pst.setInt(2, project.getId());
            pst.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to assign technician to the project", e);
        }
    }
    */
}
