package dk.easv.dal.dao;

// imports
import dk.easv.be.User;
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
     * Gets the list of users from the database.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public List<User> readUsers() throws Exception {
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
        }
        return allUsers;
    }

    /**
     * Creates project in the database.
     * @param user sends object "User" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public User createUser(User user) throws Exception {
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
            throw new RuntimeException("Id not set");
        }
    }

    /**
     * Updates a user in the database.
     * @param user sends object "User" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public void editUser(User user) throws Exception {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "UPDATE [User] SET username = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getRole());
            pst.setInt(4, user.getId());
            int updatedRows = pst.executeUpdate();

            if (updatedRows <= 0) {
                throw new RuntimeException("User not found with id: " + user.getId());
            }
        }
    }
}
