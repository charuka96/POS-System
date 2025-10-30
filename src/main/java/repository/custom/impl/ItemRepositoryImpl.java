package repository.custom.impl;
import model.Item;
import repository.custom.ItemRepository;
import util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public boolean save(Item item) {
        try {
        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?)",
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean update(Item item) {
        try {
          return   CrudUtil.execute("UPDATE item SET  code=?,description=?,  unitPrice=?, qtyOnHand=? WHERE id=?",
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean delete(String id) {
        try {
            return CrudUtil.execute("DELETE FROM item WHERE code= '"+id+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Item searchById(String id) {
        Item item = new Item();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM item WHERE code='"+id+"'");
            while (resultSet.next()){
               Item item1 =new Item (
                       resultSet.getString(1),
                       resultSet.getString(2),
                       resultSet.getDouble(3),
                       resultSet.getInt(4)
                        );
               item=item1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }
    @Override
    public List<Item> getAll() {
        List<Item>itemList =new ArrayList<>();
        try {
            ResultSet resultSet =CrudUtil.execute("SELECT *FROM item");
            while (resultSet.next()){
                Item item =new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                );
                itemList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}
