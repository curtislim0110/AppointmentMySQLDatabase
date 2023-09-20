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
        Parent root = FXMLLoader.load(getClass().getResource("../view/Reports.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


//write  login 15m alert here, or as called helper function
        // call all appointments and make two lists like weekly/monthly view. store all appointments within 15m in the 15m list using localdatetime and for-each looping
        // lastly, display a custom message with appointment details


    }
}
