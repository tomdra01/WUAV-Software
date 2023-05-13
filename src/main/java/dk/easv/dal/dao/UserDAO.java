package dk.easv.dal.dao;

// imports
import dk.easv.be.User;
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


    public List<User> readUsers() throws DatabaseException {
        List<User> allUsers = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM [User]";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {

                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );

                    allUsers.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("", e);
        }
        return allUsers;
    }

    public User createUser(User user) throws DatabaseException {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO [User] (username, password, role) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getRole());
            pst.execute();

            if (pst.getGeneratedKeys().next()) {
                int id = pst.getGeneratedKeys().getInt(1);
                user.setId(id);
                return user;
            }
            throw new DatabaseException("Id not set");

        } catch (SQLException e) {
            throw new DatabaseException("Failed to create technician", e);
        }
    }
}
