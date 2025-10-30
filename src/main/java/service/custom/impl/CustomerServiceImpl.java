package service.custom.impl;
import model.Customer;
import repository.RepositoryFactory;
import repository.custom.CustomerRepository;
import repository.custom.impl.CustomerRepositoryImpl;
import service.custom.CustomerService;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl  implements CustomerService {
    CustomerRepository repository = new CustomerRepositoryImpl();
    //CustomerRepository repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.CUSTOMER);
    @Override
    public boolean addCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        repository.update(customer);
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return repository.delete(id);
    }

    @Override
    public Customer getCustomer(String id) {
        return repository.searchById(id);
    }

    @Override
    public List<Customer> getAll() {
        return repository.getAll();
    }

    @Override
    public List<String> getCustomerIds() {
        List<Customer>all = getAll();
        ArrayList<String>customerIdlist = new ArrayList<>();
        all.forEach(customer -> customerIdlist.add(customer.getId()));
        return customerIdlist;

    }
    @Override
    public Customer searchCustomerById(String customerId) {
        Customer customer = repository.searchById(customerId);
        return customer;
    }

    @Override
    public String customersCount() {
        List<Customer> all = repository.getAll();
        Integer size = all.size();
        return size.toString();
    }
}
