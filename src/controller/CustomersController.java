package controller;

import DAO.countriesDAO;
import DAO.customersDAO;
import helper.lambdaErrorAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.countries;
import model.customers;
import model.firstleveldivisions;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class CustomersController implements Initializable{

    @FXML
    private TableColumn<?, ?> columnAddress;

    @FXML
    private TableColumn<?, ?> columnFirstLevel;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columncountry;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnPhone;

    @FXML
    private TableColumn<?, ?> columnPostal;

    @FXML
    private ComboBox<countries> comboboxCountry;

    @FXML
    private ComboBox<firstleveldivisions> comboboxFirstLevel;

    @FXML
    private TableView<customers> tableCustomers;

    @FXML
    private TextField textAddress;

    @FXML
    private TextField textCustomerID;

    @FXML
    private TextField textCustomerName;

    @FXML
    private TextField textPhone;

    @FXML
    private TextField textPostal;

    @FXML
    void onActionAdd(ActionEvent event) {

        try {
            // Load datas into local variables
            String customerName = textCustomerName.getText();
            String address = textAddress.getText();
            String postal = textPostal.getText();
            String phone = textPhone.getText();

            // Display custom error message using a lambda function if there are any blank textboxes
            if (customerName.isEmpty()) {
                currentError.lambdaError(1);
            }
            else if (address.isEmpty()) {
                currentError.lambdaError(2);
            }
            else if (postal.isEmpty()) {
                currentError.lambdaError(3);
            }
            else if (phone.isEmpty()) {
                currentError.lambdaError(4);
            }



            else {
                System.out.println("Added new customer!");
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionUpdate(ActionEvent event) {

    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Lambda expression generating error messages on incorrect input
    lambdaErrorAlert currentError = e -> {
        if (e == 1) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Name");
            alertError.showAndWait();
        }
        if (e == 2) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Address");
            alertError.showAndWait();
        }
        if (e == 3) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Postal");
            alertError.showAndWait();
        }
        if (e == 4) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Phone");
            alertError.showAndWait();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // when opening customers menu, display all customers in database joined with their first division name
        tableCustomers.setItems(customersDAO.getAllCustomers());
        columnID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        columnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        columnFirstLevel.setCellValueFactory(new PropertyValueFactory<>("firstDivisionName"));
        columncountry.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        // load comboboxes with country and first level division data
        comboboxCountry.setItems(countriesDAO.getAllCountries());


    }
}
