package dal.DAO;

// imports
import dk.easv.be.Customer;
import dk.easv.dal.dao.CustomerDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.util.List;

// static imports
import static org.junit.Assert.*;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CustomerDAOTest {
    private CustomerDAO customerDAO;

    @Before
    public void setUp() {
        customerDAO = new CustomerDAO();
    }

    @DisplayName("Test read customers")
    @Test
    public void testReadCustomers() {
        try {
            List<Customer> customers = customerDAO.readCustomers();
            assertNotNull(customers);
            assertFalse(customers.isEmpty());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test create customer")
    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(0, "John Doe", "johndoe@example.com");

        try {
            Customer createdCustomer = customerDAO.createCustomer(customer);
            assertNotNull(createdCustomer);
            assertNotEquals(0, createdCustomer.getId());
            assertEquals(customer.getName(), createdCustomer.getName());
            assertEquals(customer.getEmail(), createdCustomer.getEmail());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}