package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    @FXML
    private Button appointmentsbutton;

    @FXML
    private Button customersbutton;

    @FXML
    private Button reportsbutton;

    @FXML
    void onActionAppointments(ActionEvent event) throws IOException {
        System.out.println("Appointments Button Used");
        Parent root = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCustomers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        System.out.println("Reports Button Used");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main Menu Initialized!");

    }

}
