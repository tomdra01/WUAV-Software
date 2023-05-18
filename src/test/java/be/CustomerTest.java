package be;

// imports
import dk.easv.be.Customer;

// JUnit imports
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CustomerTest {
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer(1, "testCustomer", "testcustomer@example.com");
    }

    @DisplayName("Test customer getter")
    @Test
    public void testCustomerGetter() {
        Assertions.assertEquals(1, customer.getId());
        Assertions.assertEquals("testCustomer", customer.getName());
        Assertions.assertEquals("testcustomer@example.com", customer.getEmail());
    }

    @DisplayName("Test customer setter")
    @Test
    public void testCustomerSetter() {
        customer.setId(2);
        customer.setName("newCustomer");
        customer.setEmail("newcustomer@example.com");

        Assertions.assertEquals(2, customer.getId());
        Assertions.assertEquals("newCustomer", customer.getName());
        Assertions.assertEquals("newcustomer@example.com", customer.getEmail());
    }

    @DisplayName("Test customer with null values")
    @Test
    public void testCustomerWithNullValues() {
        customer.setId(0);
        customer.setName(null);
        customer.setEmail(null);

        Assertions.assertEquals(0, customer.getId());
        Assertions.assertNull(customer.getName());
        Assertions.assertNull(customer.getEmail());
    }

    @DisplayName("Test customer to string")
    @Test
    public void testCustomerToString() {
        Customer customer = new Customer(1, "testCustomer", "testcustomer@example.com");
        String expectedToString = "Customer{id=1, name='testCustomer', email='testcustomer@example.com'}";
        Assertions.assertEquals(expectedToString, customer.toString());
    }
}