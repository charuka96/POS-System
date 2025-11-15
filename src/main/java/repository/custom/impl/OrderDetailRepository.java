package repository.custom.impl;

import model.OrderDetail;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailRepository {
    private static OrderDetailRepository instance;

    private OrderDetailRepository() {
    }
    public boolean saveOrderDetail(List<OrderDetail> orderDetailList) throws SQLException {
        for (OrderDetail orderDetail : orderDetailList) {
            boolean isSave = saveOrderDetail(orderDetail);
            if (!isSave){
                return false;
            }
        }
        return true;
    }

    public boolean saveOrderDetail(OrderDetail orderDetail) throws SQLException {

        return CrudUtil.execute("INSERT INTO orderdetail VALUES(?,?,?,?)",
                orderDetail.getOrderId(),
                orderDetail.getItemCode(),
                orderDetail.getQty(),
                orderDetail.getUnitPrice()
        );
    }
    public static OrderDetailRepository getInstance() {
        return instance == null ? instance = new OrderDetailRepository() : instance;
    }
}
