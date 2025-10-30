package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashRootController {

    @FXML
    private AnchorPane root;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../view/Add-customer-form.fxml"));
        assert  root!=null;
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../view/item-form.fxml"));
        assert  root!=null;
        root.getChildren().clear();
        root.getChildren().add(load);

    }

    @FXML
    void btnAboutButtonOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/About-form.fxml"));
        assert  root!=null;
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnOrderButtonOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/Add-Order-Form.fxml"));
        assert  root!=null;
        root.getChildren().clear();
        root.getChildren().add(load);
    }


    @FXML
    void btnDashBordOnAction(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../view/dash-bord.fxml"));
        assert  root!=null;
        root.getChildren().clear();
        root.getChildren().add(load);
    }

}



