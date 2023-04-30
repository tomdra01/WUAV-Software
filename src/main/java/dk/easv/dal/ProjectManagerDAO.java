package dk.easv.dal;

import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectManagerDAO {
    private DatabaseConnector databaseConnector;
    public ProjectManagerDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all technicians from the database.
     */
    public List<ProjectManager> readAllProjectManagers() throws DatabaseException {
        List<ProjectManager> allProjectManagers = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM ProjectManager;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {

                    ProjectManager projectManager = new ProjectManager(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    );

                    allProjectManagers.add(projectManager);
                }
            }
        } catch(SQLException e){
            throw new DatabaseException("Couldn't get all admins... Check database connection!", e);
        }
        return allProjectManagers;
    }

    public ProjectManager createProjectManager(ProjectManager projectManager) throws SQLException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO ProjectManager (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, projectManager.getUsername());
            pst.setString(2, projectManager.getPassword());
            pst.execute();

            if (pst.getGeneratedKeys().next()) {
                int id = pst.getGeneratedKeys().getInt(1);
                projectManager.setId(id);
                return projectManager;
            }
        }
        throw new RuntimeException("Id not set");
    }
}
