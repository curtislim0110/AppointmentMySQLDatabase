package controller;

import DAO.countriesDAO;
import DAO.customersDAO;
import DAO.firstleveldivisionsDAO;
import helper.lambdaAlert;
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
            // Load data into local variables
            String customerName = textCustomerName.getText();
            String address = textAddress.getText();
            String postal = textPostal.getText();
            String phone = textPhone.getText();
            firstleveldivisions currentDivision = comboboxFirstLevel.getValue();

            // Display custom error message using a lambda function if there are any blank textboxes
            if (customerName.isEmpty()) {
                currentError.lambdaAlertMethod(1);
            }
            else if (address.isEmpty()) {
                currentError.lambdaAlertMethod(2);
            }
            else if (postal.isEmpty()) {
                currentError.lambdaAlertMethod(3);
            }
            else if (phone.isEmpty()) {
                currentError.lambdaAlertMethod(4);
            }
            // if no country is selected
            else if (comboboxCountry.getValue() == null) {
                currentError.lambdaAlertMethod(5);
            }
            // if no first level division is selected
            else if (currentDivision == null) {
                currentError.lambdaAlertMethod(6);
            }
            // if there are no blank textboxes or missing selections, create a new customer
            else {
                customersDAO.addCustomer(customerName, address, postal, phone, currentDivision.getDivisionID());
                currentError.lambdaAlertMethod(7);
                tableCustomers.setItems(customersDAO.getAllCustomers());

                //customer list not sorting by customerID
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



        // use combobox.setvalue to set value when updating a customer
    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCountryComboBox(ActionEvent event) {
        countries currentcountry = comboboxCountry.getValue();
        comboboxFirstLevel.setItems(firstleveldivisionsDAO.showFirstLevels(currentcountry.getCountryID()));
    }

    /**
     * Lambda Expression, interface for lambdaAlert is in /helper/lambdaAlert
     */
    lambdaAlert currentError = e -> {
        if (e == 1) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Name");
            alertError.showAndWait();
        }
        else if (e == 2) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Address");
            alertError.showAndWait();
        }
        else if (e == 3) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Postal");
            alertError.showAndWait();
        }
        else if (e == 4) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Enter Phone");
            alertError.showAndWait();
        }
        else if (e == 5) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Select Country");
            alertError.showAndWait();
        }
        else if (e == 6) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setContentText("Select First Level Division");
            alertError.showAndWait();
        }
        else if (e == 7) {
            Alert alertError = new Alert(Alert.AlertType.INFORMATION);
            alertError.setTitle("Success");
            alertError.setContentText("New customer added");
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
        comboboxCountry.setPromptText("Select Country");

        // divisions must be loaded dynamically when the country box is selected, not initialized
        // comboboxFirstLevel.setItems(firstleveldivisionsDAO.getAllFirstLevelDivisions());
        comboboxFirstLevel.setPromptText("Select First Level");




    }
}
