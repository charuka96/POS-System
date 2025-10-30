package service.custom;

import model.Orders;
import repository.custom.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public interface OrederService {
   List<Orders>getAll() throws SQLException;
}
