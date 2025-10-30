package repository.custom.impl;

import db.DBConection;

import javafx.scene.control.Alert;
import model.Customer;
import repository.custom.CustomerRepository;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean save(Customer customer) {
        try {
            return  CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?)",
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public boolean update(Customer customer) {
        String SQL = "UPDATE customer SET  name=?,address=?, salary=? WHERE id=?";
        try {
            Connection connection = DBConection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1,customer.getName());
            pstm.setObject(2,customer.getAddress());
            pstm.setObject(3,customer.getSalary());
            pstm.setObject(4,customer.getId());
            boolean isUpadte = pstm.executeUpdate()>0;
            if (isUpadte){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  false;
    }

    @Override
    public boolean delete(String id) {
        try {
            Connection connection = DBConection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE id = '"+id+"' ");
            boolean execute = pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    @Override
    public Customer searchById(String id) {
        Customer customer = new Customer();
        try {
            Connection connection = DBConection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer WHERE id = '"+id+"'");
            ResultSet resultSet = pstm.executeQuery();

            while(resultSet.next()){
                Customer customers = new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );
                customer =customers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {

        List<Customer>customerList =new ArrayList<>();
        try {
            Connection connection = DBConection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet =pstm.executeQuery();
            while (resultSet.next()){
                Customer customer =new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getDouble("salary")
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
}

