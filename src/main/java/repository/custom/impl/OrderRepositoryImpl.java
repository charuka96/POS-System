package repository.custom.impl;
import model.Orders;
import repository.custom.OrderRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl  implements OrderRepository {

    @Override
    public boolean save(Orders orders) {
        return false;
    }

    @Override
    public boolean update(Orders orders) {
        return false;
    }
    @Override
    public boolean delete(String s) {
        return false;
    }
    @Override
    public Orders searchById(String s) {
        return null;
    }
    @Override
    public List<Orders> getAll() throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT *FROM orders");
        while (resultSet.next()){
            Orders orders = new Orders(
                    resultSet.getNString("id"),
                    resultSet.getDate("date"),
                    resultSet.getString("customerId")
            );
            ordersList.add(orders);
        }
        return ordersList;
    }
}
