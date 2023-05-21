package dk.easv.dal.dao;

// imports
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
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
    private final DatabaseConnector databaseConnector;
    public ProjectDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all projects from the database.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public List<Project> readAllProjects() throws Exception {
        List<Project> allProjects = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM [Project]";
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
        }
        return allProjects;
    }

    /**
     * Gets the list of all projects from the database.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public List<Project> readTechnicianProjects(User user) throws Exception {
        List<Project> technicianProjects = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM [Project] INNER JOIN [TechnicianProject] ON Project.id = [TechnicianProject].project_id " +
                         "INNER JOIN [User] ON [TechnicianProject].technician_id = [User].id WHERE [TechnicianProject].technician_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, user.getId());

            ResultSet resultSet = pst.executeQuery();
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

                technicianProjects.add(project);
            }
        }
        return technicianProjects;
    }

    /**
     * Gets the list of all projects from the database.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public List<Project> readSalesmanProjects() throws Exception {
        List<Project> approvedProjects = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM [Project] WHERE approved = 1";
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

                    approvedProjects.add(project);
                }
            }
        }
        return approvedProjects;
    }

    /**
     * Gets the images associated with a specific project from the database.
     * @param projectId The ID of the project.
     * @return A list of byte arrays, each representing an image.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public List<byte[]> getProjectImages(int projectId) throws Exception {
        List<byte[]> projectImages = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT imageData FROM ProjectPhotos WHERE project_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, projectId);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("imageData");
                projectImages.add(imageData);
            }
        }
        return projectImages;
    }

    /**
     * Creates project in the database.
     * @param project sends object "Project" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public Project createProject(Project project) throws Exception {
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
        }
    }

    /**
     * Inserts images according to specific project.
     * @param project sends object "Project" as a parameter.
     * @param imageData sends image bytes.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public void insertImages(Project project, byte[] imageData) throws Exception {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO ProjectPhotos(project_id, imageData) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, project.getId());
            pst.setBytes(2, imageData);
            pst.executeUpdate();
        }
    }

    /**
     * Assigns technician to the project.
     * @param user sends object "User" as a parameter.
     * @param project sends object "Project" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public void technicianProject(User user, Project project) throws Exception {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO TechnicianProject(technician_id, project_id) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, user.getId());
            pst.setInt(2, project.getId());
            pst.executeUpdate();
            con.commit();
        }
    }

    /**
     * Deletes the project
     * @param project sends object "Project" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public void deleteProject(Project project) throws Exception {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pstDeleteProject = con.prepareStatement("DELETE FROM [Project] WHERE id = ?");
            pstDeleteProject.setInt(1, project.getId());
            pstDeleteProject.executeUpdate();

            PreparedStatement pstDeleteTechnicianProject = con.prepareStatement("DELETE FROM [TechnicianProject] WHERE project_id = ?");
            pstDeleteTechnicianProject.setInt(1, project.getId());
            pstDeleteTechnicianProject.executeUpdate();

            con.commit();
        }
    }

    /**
     * Updates a project in the database.
     * @param project sends object "Project" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public void updateProject(Project project) throws Exception {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("UPDATE Project SET name = ?, businessType = ?, location = ?, date = ?, drawing = ?, description = ? WHERE id = ?");
            pst.setString(1, project.getName());
            pst.setString(2, project.getBusinessType());
            pst.setString(3, project.getLocation());
            pst.setDate(4, Date.valueOf(project.getDate()));
            pst.setBytes(5, project.getDrawing());
            pst.setString(6, project.getDescription());
            pst.setInt(7, project.getId());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Project not found");
            }
        }
    }

    /**
     * Updates project approval status.
     * @param project sends object "Project" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public void updateApprovalStatus(Project project) throws Exception {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("UPDATE Project SET approved = ? WHERE id = ?");
            pst.setBoolean(1, project.isApproved());
            pst.setInt(2, project.getId());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Project not found");
            }
        }
    }

    public void createLogEntry(Log log) throws SQLException {
        String sql = "INSERT INTO Log (logAction, actionTime, user_id) VALUES (?, ?, ?)";

        try (Connection con = databaseConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, log.getLogAction());
            pstmt.setTimestamp(2, Timestamp.valueOf(log.getActionTime()));
            pstmt.setInt(3, log.getUser_id());

            pstmt.executeUpdate();
        }
    }
}
