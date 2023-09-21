package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls the main menu which the user starts after a successful login.
 */
public class MainMenuController implements Initializable {

    /**
     * Switches to the Appointments screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAppointments(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the Customers screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCustomers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the report screen for a sorted list of appointments by month and appointment type
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReportSortedCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportSorted.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the report screen for appointments filtered by contact
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReportContactSchedule(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportContact.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the custom report screen for appointments filtered by user
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReportCustom(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportUserID.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
