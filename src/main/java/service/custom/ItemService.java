package service.custom;
import model.Item;
import java.util.List;
public interface ItemService {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItem(String id);
    List<Item> getAllItem();
    List<String> getItemIds();
    Item searchItemById(String id);
    String ItemCount();
}
