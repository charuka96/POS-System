package service.custom;

import model.Orders;
import repository.custom.OrderRepository;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface OrederService  extends SuperService {
   List<Orders>getAll() throws SQLException;
}
