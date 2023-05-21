package dk.easv.gui.model;

// imports
import dk.easv.be.Customer;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.logic.CustomerLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CustomerModel {
    CustomerLogic customerLogic = new CustomerLogic();
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void loadCustomers() throws DatabaseException{
        customers.clear();
        customers.addAll(customerLogic.readCustomers());
    }

    public void createCustomer(Customer customer) throws DatabaseException {
        Customer c = customerLogic.createCustomer(customer);
        customers.add(c);
    }
}
