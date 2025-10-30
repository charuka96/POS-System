package controller;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import service.custom.ItemService;
import service.custom.impl.ItemServiceImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colItemDescription;

    @FXML
    private TableColumn colItemQty;

    @FXML
    private TableColumn colItemUnitPrice;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemDiscription;

    @FXML
    private JFXTextField txtItemQty;

    @FXML
    private JFXTextField txtItemUnitprice;
    ItemService service = new ItemServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        loadaaTable();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newvalue)->{
            if(newvalue!=null){
                addValueText(newvalue);
            }
        } );
    }

    private  void addValueText(Item newvalue){
        txtItemCode.setText(newvalue.getCode());
        txtItemDiscription.setText(newvalue.getDescription());
        txtItemUnitprice.setText(newvalue.getUnitPrice().toString());
        txtItemQty.setText(newvalue.getQtyOnHand().toString());

    }

    private  void loadaaTable(){
        List<Item> allItem = service.getAllItem();
        tblItem.setItems(FXCollections.observableArrayList(allItem));
    }
    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Item item =new Item(
                txtItemCode.getText(),
                txtItemDiscription.getText(),
                Double.parseDouble(txtItemUnitprice.getText()),
                Integer.parseInt(txtItemQty.getText()));
        service.addItem(item);
        loadaaTable();
    }

    @FXML
    void btnDeletItemOnAction(ActionEvent event) {
        service.deleteItem(txtItemCode.getText());
        loadaaTable();
    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        Item item = service.searchItemById(txtItemCode.getText());
        txtItemCode.setText(item.getCode());
        txtItemDiscription.setText(item.getDescription());
        txtItemUnitprice.setText(item.getUnitPrice().toString());
        txtItemQty.setText(item.getQtyOnHand().toString());
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtItemDiscription.getText(),
                Double.parseDouble(txtItemUnitprice.getText()),
                Integer.parseInt(txtItemQty.getText()));
        service.updateItem(item);

    }

}
