package service.custom;

import javafx.collections.ObservableList;
import model.Customer;

import java.util.List;

public interface CustomerService {

    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
    Customer getCustomer(String id);
    List<Customer> getAll();
    List<String>getCustomerIds();
    Customer searchCustomerById(String customerId);
    String  customersCount();
}
