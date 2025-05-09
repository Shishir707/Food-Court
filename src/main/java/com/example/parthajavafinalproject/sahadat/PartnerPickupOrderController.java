package com.example.parthajavafinalproject.sahadat;

import com.example.parthajavafinalproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PartnerPickupOrderController {

    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private Label label1;

    @javafx.fxml.FXML
    public void handleArrival(ActionEvent actionEvent) {
        label.setText("Restaurant arrived");
    }

    @javafx.fxml.FXML
    public void handlePickup(ActionEvent actionEvent) {
        label1.setText("Order picked up");
    }

    @javafx.fxml.FXML
    public void partnerDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sahadat/partnerDashboard.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Back");
        stage.show();
    }
}
