package service.custom;

import model.Orders;
import repository.custom.OrderRepository;
import service.SuperService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrederService  extends SuperService {
   List<Orders>getAll() throws SQLException;
  boolean placeOrder( Orders order) throws SQLException;
  List<Date> getDate();
  public int getOrder(Date d);


}
