package service.custom;
import model.Item;

import java.sql.SQLException;
import java.util.List;
public interface ItemService {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItem(String id);
    List<Item> getAllItem() throws SQLException;
    List<String> getItemIds() throws SQLException;
    Item searchItemById(String id);
    String ItemCount() throws SQLException;
}
