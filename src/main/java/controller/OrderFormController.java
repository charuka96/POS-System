package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController  implements Initializable {

    @FXML
    private JFXComboBox cmbCusId;

    @FXML
    private JFXComboBox cmbItemCod;

    @FXML
    private TableColumn colDescri;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView<PlaceOrder> tblOrder;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private Label txtDate;

    @FXML
    private JFXTextField txtDescrip;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtQutOnHand;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private Label txtTime;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private Label txtnetPrice;

    @FXML
    private JFXTextField txtOrderID;

   //CustomerService customerService = new CustomerServiceImpl();
   ItemService itemService = new ItemServiceImpl();
   List<PlaceOrder> placeOrders = new ArrayList<>();
   Double netValue= 0.0;
   CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        try {
            loadCustomerIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            loadItemIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        cmbCusId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue!=null){
                setTextValueCustomer((String) newValue);
            }

        });
        cmbItemCod.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue!=null){
                setTextValueItem((String) newValue);
            }
        });
    }
    @FXML
    void btnAddtoCardOnAction(ActionEvent event) {
       Item item = itemService.searchItemById(cmbItemCod.getValue().toString());
        PlaceOrder placeOrder =new PlaceOrder(
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                Integer.parseInt(txtQutOnHand.getText()),
                item.getUnitPrice()*Integer.parseInt(txtQutOnHand.getText()),
                item.getUnitPrice()*Integer.parseInt(txtQutOnHand.getText())
        );
        this.netValue += placeOrder.getNetValue();
        txtnetPrice.setText(this.netValue.toString());
        placeOrders.add(placeOrder);
        viewOrderTable();
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
    placeOrders.clear();
        viewOrderTable();
    }
    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId =  txtOrderID.getText();
        Date orderDate = new Date();
        String customerId = cmbCusId.getValue().toString();
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        placeOrders.forEach(obj->{
            orderDetails.add(
                    new OrderDetail(
                            txtOrderID.getText(),
                            obj.getCode(),
                            obj.getQty(),
                            0.0)
            );}
        );
        Orders orders = new Orders(orderId,orderDate,customerId,orderDetails);
        System.out.println(orders);
    }
    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        txtDate.setText(simpleDateFormat.format(date));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,e->{
                    LocalTime now = LocalTime.now();
                    txtTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void loadCustomerIds() throws SQLException {
        cmbCusId.setItems(FXCollections.observableArrayList(customerService.getCustomerIds()));
    }
    private void setTextValueCustomer(String customerId){
        Customer customer = customerService.searchCustomerById(customerId);
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(customer.getSalary().toString());
    }
    private void loadItemIds() throws SQLException {
        cmbItemCod.setItems(FXCollections.observableArrayList(itemService.getItemIds()));
    }

    private void  setTextValueItem(String itemId){
        Item item = itemService.searchItemById(itemId);
        txtDescrip.setText(item.getDescription());
        txtUnitPrice.setText(item.getUnitPrice().toString());
        txtStock.setText(item.getQtyOnHand().toString());
    }
    private void viewOrderTable(){  tblOrder.setItems(FXCollections.observableArrayList(placeOrders));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescri.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new  PropertyValueFactory<>("total"));}
}