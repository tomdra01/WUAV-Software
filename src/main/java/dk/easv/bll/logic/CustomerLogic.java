package dk.easv.bll.logic;

// imports
import dk.easv.be.Customer;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.DAOFactory;
import dk.easv.dal.DataAccessObjects;
import dk.easv.dal.dao.interfaces.ICustomerDAO;

import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CustomerLogic {
    ICustomerDAO customerDAO = (ICustomerDAO) DAOFactory.getDAO(DataAccessObjects.CUSTOMER_DAO);

    public List<Customer> readCustomers() throws DatabaseException {
        try {
            return customerDAO.readCustomers();
        } catch (Exception e) {
            throw new DatabaseException("Failed to read all customers", e);
        }
    }

    public Customer createCustomer(Customer customer) throws DatabaseException {
        try {
            return customerDAO.createCustomer(customer);
        } catch (Exception e) {
            throw new DatabaseException("""
                    Failed to create the customer

                        ...Please check the database""", e);
        }
    }
}
