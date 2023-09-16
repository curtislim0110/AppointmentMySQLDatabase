package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class mainmenucontroller implements Initializable {

    @FXML
    private Button appointmentsbutton;

    @FXML
    private Button customersbutton;

    @FXML
    private Button reportsbutton;

    @FXML
    void onActionAppointments(ActionEvent event) throws IOException {

    }

    @FXML
    void onActionCustomers(ActionEvent event) throws IOException {

    }

    @FXML
    void onActionReports(ActionEvent event) throws IOException {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main Menu Initialized!");

    }

}
