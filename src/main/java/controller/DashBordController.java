package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.OrederService;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;
import service.custom.impl.OrderServiceImp;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBordController implements Initializable {

    @FXML
    private Label txtCoustCount;

    @FXML
    private Label txtItemCount;

    @FXML
    private Label txtOrderCount;
    CustomerService customerService = new CustomerServiceImpl();
    ItemService itemService = new ItemServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCoustCount.setText(customerService.customersCount());
        txtItemCount.setText(itemService.ItemCount());
        txtOrderCount.setText("80");}
}
