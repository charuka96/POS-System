package repository.custom.impl;
import db.DBConection;
import model.Orders;
import repository.RepositoryFactory;
import repository.custom.ItemRepository;
import repository.custom.OrderRepository;
import util.CrudUtil;
import util.RepositoryType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl  implements OrderRepository {
    ItemRepository itemRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ITEM);
    @Override
    public boolean save(Orders orders) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            PreparedStatement psTM = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
            psTM.setObject(1,orders.getId());
            psTM.setObject(2,orders.getDate());
            psTM.setObject(3,orders.getCustomerId());
            if(psTM.executeUpdate()>0){
                boolean isOrderDetailsAdd = OrderDetailRepository.getInstance().saveOrderDetail(orders.getOrderDetails());
                if (isOrderDetailsAdd){
                    boolean isUpdateStock = itemRepository.updateStock(orders.getOrderDetails());
                    if (isUpdateStock){
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }

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
        while (resultSet.next()){Orders orders = new Orders(
                   resultSet.getNString("id"),
                   resultSet.getDate("date"),
                   resultSet.getString("customerId")
        );
            ordersList.add(orders);
        }
        return ordersList;
    }
}
