package dk.easv.dal.dao.interfaces;

// imports
import dk.easv.be.Customer;
import dk.easv.dal.IDataAccess;

import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public interface ICustomerDAO extends IDataAccess {
    List<Customer> readCustomers() throws Exception;
    Customer createCustomer(Customer customer) throws Exception;
}
