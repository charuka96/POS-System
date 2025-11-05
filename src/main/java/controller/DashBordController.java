package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.OrederService;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;
import service.custom.impl.OrderServiceImp;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashBordController implements Initializable {

    @FXML
    private AreaChart chrtOrder;

    @FXML
    private Label txtCoustCount;

    @FXML
    private Label txtItemCount;

    @FXML
    private Label txtOrderCount;
    CustomerService customerService = new CustomerServiceImpl();
    ItemService itemService = new ItemServiceImpl();
    OrederService orederService = new OrderServiceImp();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series seriesJuly = new XYChart.Series();
        seriesJuly.setName("July");
        seriesJuly.getData().add(new XYChart.Data(1,4));
        seriesJuly.getData().add(new XYChart.Data(3,10));
        seriesJuly.getData().add(new XYChart.Data(6,15));
        seriesJuly.getData().add(new XYChart.Data(9,8));
        seriesJuly.getData().add(new XYChart.Data(12,5));
        seriesJuly.getData().add(new XYChart.Data(15,18));
        seriesJuly.getData().add(new XYChart.Data(18,15));
        seriesJuly.getData().add(new XYChart.Data(21,13));
        seriesJuly.getData().add(new XYChart.Data(24,19));
        seriesJuly.getData().add(new XYChart.Data(27,21));
        seriesJuly.getData().add(new XYChart.Data(30,21));


        XYChart.Series seriesaAug = new XYChart.Series();
        seriesaAug.setName("Aug");
        seriesaAug.getData().add(new XYChart.Data(1,20));
        seriesaAug.getData().add(new XYChart.Data(3,50));
        seriesaAug.getData().add(new XYChart.Data(6,8));
        seriesaAug.getData().add(new XYChart.Data(10,10));
        seriesaAug.getData().add(new XYChart.Data(21,40));
        seriesaAug.getData().add(new XYChart.Data(15,12));
        seriesaAug.getData().add(new XYChart.Data(18,18));
        seriesaAug.getData().add(new XYChart.Data(30,14));
        seriesaAug.getData().add(new XYChart.Data(25,14));
        seriesaAug.getData().add(new XYChart.Data(26,8));
        chrtOrder.getData().addAll(seriesJuly,seriesaAug);

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



}
