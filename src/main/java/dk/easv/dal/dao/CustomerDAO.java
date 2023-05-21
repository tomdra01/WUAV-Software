package dk.easv.dal.dao;

// imports
import dk.easv.be.Customer;
import dk.easv.be.User;
import dk.easv.dal.dao.interfaces.ICustomerDAO;
import dk.easv.dal.database.DatabaseConnector;

// java imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CustomerDAO implements ICustomerDAO {
    private DatabaseConnector databaseConnector;
    public CustomerDAO() {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Gets the list of customers from the database.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public List<Customer> readCustomers() throws Exception {
        List<Customer> allCustomers = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM [Customer]";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {

                    Customer customer = new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email")
                    );

                    allCustomers.add(customer);
                }
            }
        }
        return allCustomers;
    }

    /**
     * Creates customer in the database.
     * @param customer sends object "Customer" as a parameter.
     * @throws Exception pushes any kind of Exception upwards to the BLL.
     */
    public Customer createCustomer(Customer customer) throws Exception {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO [Customer] (name, email) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getEmail());
            pst.execute();

            if (pst.getGeneratedKeys().next()) {
                int id = pst.getGeneratedKeys().getInt(1);
                customer.setId(id);
                return customer;
            }
            throw new RuntimeException("Id not set");
        }
    }
}
