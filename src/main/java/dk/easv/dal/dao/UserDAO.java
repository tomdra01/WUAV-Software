package dk.easv.dal.dao;

// imports
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.be.roles.Admin;
import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.database.DatabaseConnector;
import dk.easv.dal.dao.interfaces.IUserDao;

// java imports
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserDAO implements IUserDao {
    private final DatabaseConnector databaseConnector;
    public UserDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of all admins from the database.
     * @throws DatabaseException handles SQLException.
     */
    public List<Admin> readAllAdmins() throws DatabaseException {
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
        catch(SQLException e){
            throw new DatabaseException("Failed to get all admins", e);
        }
        return allAdmins;
    }

    /**
     * Gets the list of all technicians from the database.
     * @throws DatabaseException handles SQLException.
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
            throw new DatabaseException("Failed to get all technicians", e);
        }
        return allTechnicians;
    }

    /**
     * Creates technician in the database.
     * @param technician sends object "Technician" as a parameter.
     * @throws DatabaseException handles SQLException.
     */
    public Technician createTechnician(Technician technician) throws DatabaseException {
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
            throw new DatabaseException("Id not set");

        } catch (SQLServerException e) {
            throw new DatabaseException("Failed to connect to the server", e);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create technician", e);
        }
    }

    /**
     * Gets the list of all project managers from the database.
     * @throws DatabaseException handles SQLException.
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
            throw new DatabaseException("Failed to get all project managers", e);
        }
        return allProjectManagers;
    }

    /**
     * Creates project manager in the database.
     * @param projectManager sends object "ProjectManager" as a parameter.
     * @throws DatabaseException handles SQLException.
     */
    public ProjectManager createProjectManager(ProjectManager projectManager) throws DatabaseException {
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
            throw new DatabaseException("Id not set");

        } catch (SQLServerException e) {
            throw new DatabaseException("Failed to connect to the server", e);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create project manager", e);
        }
    }

    /**
     * Gets the list of all salesmen from the database.
     * @throws DatabaseException handles SQLException.
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
            throw new DatabaseException("Failed to get all technicians", e);
        }
        return allSalesmen;
    }

    /**
     * Creates salesman in the database.
     * @param salesman sends object "Salesman" as a parameter.
     * @throws DatabaseException handles SQLException.
     */
    public Salesman createSalesman(Salesman salesman) throws DatabaseException {
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
            throw new RuntimeException("Id not set");

        } catch (SQLServerException e) {
            throw new DatabaseException("Failed to connect to the server", e);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create salesman", e);
        }
    }
}
