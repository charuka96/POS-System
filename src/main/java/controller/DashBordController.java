package controller;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Orders;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.OrederService;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;
import service.custom.impl.OrderServiceImp;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class DashBordController implements Initializable {

    @FXML
    private AreaChart chrtOrder;

    @FXML
    private Label txtCoustCount;

    @FXML
    private Label txtItemCount;

    @FXML
    private DatePicker txtgetDate;

    @FXML
    private Label txtOrderCount;
    CustomerService customerService = new CustomerServiceImpl();
    ItemService itemService = new ItemServiceImpl();

    OrederService orederService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtCoustCount.setText(customerService.customersCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            txtItemCount.setText(itemService.ItemCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            txtOrderCount.setText(String.valueOf(orederService.getAll().size()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadOrdersChart();
    }
    public void loadOrdersChart(){
        LocalDate selected = txtgetDate.getValue(); // example: 2025-11-11
        int year = selected.getYear();
        int month = selected.getMonthValue();
        int daysInMonth = selected.lengthOfMonth();

        XYChart.Series series = new XYChart.Series();
        series.setName(selected.getMonth().toString()); // Example: "NOVEMBER"
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            Date sqlDate = Date.valueOf(date);
            System.out.println(sqlDate);
            int count = orederService.getOrder(sqlDate);
            System.out.println(count);
            series.getData().add(new XYChart.Data(day, count));
        }
        chrtOrder.getData().clear();
        chrtOrder.getData().add(series);

    }

}



