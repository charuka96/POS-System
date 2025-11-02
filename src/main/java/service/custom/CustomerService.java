package service.custom;

import javafx.collections.ObservableList;
import model.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
    Customer getCustomer(String id);
    List<Customer> getAll() throws SQLException;
    List<String>getCustomerIds() throws SQLException;
    Customer searchCustomerById(String customerId);
    String  customersCount() throws SQLException;
}
