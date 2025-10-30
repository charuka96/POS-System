package service.custom.impl;
import model.Item;
import repository.custom.ItemRepository;
import repository.custom.impl.ItemRepositoryImpl;
import service.custom.ItemService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    ItemRepository repository = new ItemRepositoryImpl();
    @Override
    public boolean addItem(Item item) {
       return repository.save(item);
    }
    @Override
    public boolean updateItem(Item item) {
       return repository.update(item);
    }
    @Override
    public boolean deleteItem(String id) {
       return repository.delete(id);
    }
    @Override
    public List<Item> getAllItem() throws SQLException {
         return repository.getAll();
    }
    @Override
    public List<String> getItemIds() throws SQLException {
        List<Item>all = getAllItem();
        ArrayList<String> itemList = new ArrayList<>();
        all.forEach(items -> itemList.add(items.getCode()));
        return itemList;
    }
    @Override
    public Item searchItemById(String id) {
        Item item = repository.searchById(id);
        return item;
    }

    @Override
    public String ItemCount() throws SQLException {
       List itemList = repository.getAll();
       Integer itemCount = itemList.size();
        return itemCount.toString();
    }
}
