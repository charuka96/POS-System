package controller;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.impl.CustomerServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private TableColumn colCusAddress;

    @FXML
    private TableColumn colCusName;

    @FXML
    private TableColumn colCussalary;

    @FXML
    private TableColumn colcusId;

    @FXML
    private TableView<Customer> tblAddCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;
    List<Customer> customerList = new ArrayList<>();
    //CustomerService service = new CustomerServiceImpl();
    CustomerService service = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colcusId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCussalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        loadTable();
        tblAddCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
        if (newValue!=null){
            addValueText(newValue);}
         });

    }

    private void loadTable(){
        List<Customer> all = null;
        try {
            all = service.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblAddCustomer.setItems(FXCollections.observableArrayList(all));
    }
    private void addValueText(Customer newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtSalary.setText(""+newValue.getSalary());
    }

    @FXML
    void btnAddCusOnAction(ActionEvent event) throws SQLException {
        Customer customer  =  new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText()));
        if(service.addCustomer(customer)){
            new Alert(Alert.AlertType.CONFIRMATION,"Add Customer Success").show();
        }

        loadTable();
    }

    @FXML
    void btnDeletCusOnAction(ActionEvent event)  {
       String id =txtId.getText();

       if (service.deleteCustomer(id)){
           new Alert(Alert.AlertType.INFORMATION,"Customer Deleted").show();
       }
       loadTable();
    }
    @FXML
    void btnReloadCustblOnAction(ActionEvent event) {
        loadTable();
    }
    @FXML
    void btnSearchCusOnAction(ActionEvent event) {
      Customer customer = service.getCustomer(txtId.getText());
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(""+customer.getSalary());
        loadTable();

    }

    @FXML
    void btnUpdateCusOnAction(ActionEvent event) {
        Customer customer  =  new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText()));
        customerList.add(customer);
       service.updateCustomer(customer);
        loadTable();
    }

    @FXML
    void btnClearCustblOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
        loadTable();
    }

}
