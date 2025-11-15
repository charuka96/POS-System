package service.custom.impl;

import lombok.SneakyThrows;
import model.Orders;
import repository.RepositoryFactory;
import repository.custom.OrderRepository;
import repository.custom.impl.OrderRepositoryImpl;
import service.custom.OrederService;
import util.RepositoryType;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderServiceImp implements OrederService {

    List<Date>dates = new ArrayList<>();
    List<Orders>allOrders = new ArrayList<>();
    OrderRepository orderRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ORDER);
    @Override
    public List<Orders> getAll(){
        try {
            return orderRepository.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean placeOrder(Orders order) throws SQLException {
        return orderRepository.save(order);
    }

    @Override
   public List<Date> getDate() {
        for (Orders orders : getAll()) {
            dates.add( orders.getDate());
        }
        return dates;
   }

    @SneakyThrows
    @Override
    public int getOrder(Date d) {
      allOrders = orderRepository.getAll();
            long count = allOrders.stream()
                    .filter(odr -> d.equals(odr.getDate()))
                    .count();
            if (count == 0) {
                System.out.println("There are no orders for this date");
            }
            return (int) count;
        }
    }
