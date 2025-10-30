package service.custom.impl;

import model.Orders;
import repository.custom.OrderRepository;
import repository.custom.impl.OrderRepositoryImpl;
import service.custom.OrederService;


import java.sql.SQLException;
import java.util.List;

public class OrderServiceImp implements OrederService {
    OrderRepository repository = new OrderRepositoryImpl();
    @Override
    public List<Orders> getAll(){
        try {
            return repository.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
