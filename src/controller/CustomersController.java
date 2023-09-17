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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.countries;
import model.customers;
import model.firstleveldivisions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

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
                currentAlert.lambdaAlertMethod(1);
            }
            else if (address.isEmpty()) {
                currentAlert.lambdaAlertMethod(2);
            }
            else if (postal.isEmpty()) {
                currentAlert.lambdaAlertMethod(3);
            }
            else if (phone.isEmpty()) {
                currentAlert.lambdaAlertMethod(4);
            }
            // if no country is selected
            else if (comboboxCountry.getValue() == null) {
                currentAlert.lambdaAlertMethod(5);
            }
            // if no first level division is selected
            else if (currentDivision == null) {
                currentAlert.lambdaAlertMethod(6);
            }
            // if there are no blank textboxes or missing selections, create a new customer and refresh list
            else {
                customersDAO.addCustomer(customerName, address, postal, phone, currentDivision.getDivisionID());
                currentAlert.lambdaAlertMethod(7);
                tableCustomers.setItems(customersDAO.getAllCustomers());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void onActionUpdate(ActionEvent event) {
        try {
            if (tableCustomers.getSelectionModel().getSelectedItem() == null) {
                currentAlert.lambdaAlertMethod(8);
            }
            else {
                // Load data into local variables
                String customerName = textCustomerName.getText();
                String address = textAddress.getText();
                String postal = textPostal.getText();
                String phone = textPhone.getText();
                firstleveldivisions currentDivision = comboboxFirstLevel.getValue();

                // Display custom error message using a lambda function if there are any blank textboxes
                if (customerName.isEmpty()) {
                    currentAlert.lambdaAlertMethod(1);
                }
                else if (address.isEmpty()) {
                    currentAlert.lambdaAlertMethod(2);
                }
                else if (postal.isEmpty()) {
                    currentAlert.lambdaAlertMethod(3);
                }
                else if (phone.isEmpty()) {
                    currentAlert.lambdaAlertMethod(4);
                }
                // if no country is selected
                else if (comboboxCountry.getValue() == null) {
                    currentAlert.lambdaAlertMethod(5);
                }
                // if no first level division is selected
                else if (currentDivision == null) {
                    currentAlert.lambdaAlertMethod(6);
                }
                // if there are no blank textboxes or missing selections, create a new customer and refresh list
                else {
                    customersDAO.updateCustomer(Integer.parseInt(textCustomerID.getText()),customerName, address, postal, phone, currentDivision.getDivisionID());
                    currentAlert.lambdaAlertMethod(9);
                    tableCustomers.setItems(customersDAO.getAllCustomers());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onActionDelete(ActionEvent event) {
        // need to update to delete customer-associated appointments. for now just deleting a customer

        // Error message if no customer selected for deletion
        if (tableCustomers.getSelectionModel().getSelectedItem() == null) {
            currentAlert.lambdaAlertMethod(10);
        }
        else {
            customersDAO.deleteCustomer(tableCustomers.getSelectionModel().getSelectedItem().getCustomerID());
            currentAlert.lambdaAlertMethod(11);
            tableCustomers.setItems(customersDAO.getAllCustomers());
        }

    }


    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    // Clicking a row in the tableview will load the object's data into textboxes and combo boxes
    @FXML
    void onMouseClickTable(MouseEvent event) {
        if (tableCustomers.getSelectionModel().getSelectedItem() == null) {
            // do nothing if an empty space is clicked on the table
        }
        // otherwise, load the selected row of data from the table into the textboxes
        else {
            customers mouseclickCustomer = tableCustomers.getSelectionModel().getSelectedItem();
            textCustomerID.setText(Integer.toString(mouseclickCustomer.getCustomerID()));
            textCustomerName.setText(mouseclickCustomer.getCustomerName());
            textAddress.setText(mouseclickCustomer.getCustomerAddress());
            textPhone.setText(mouseclickCustomer.getCustomerPhone());
            textPostal.setText(mouseclickCustomer.getCustomerPostal());

            // create temporary countries and firstleveldivisions objects to load the combo boxes

            countries currentcountry = new countries(mouseclickCustomer.getCountryID(), mouseclickCustomer.getCountryName());
            comboboxCountry.setValue(currentcountry);

            String currrentDivisionName = customersDAO.getDivisionName(mouseclickCustomer.getFirstDivisionID());
            int currentcountryID = customersDAO.getCountryID(mouseclickCustomer.getFirstDivisionID());
            firstleveldivisions currentfirstlevel = new firstleveldivisions(mouseclickCustomer.getFirstDivisionID(), currrentDivisionName, currentcountryID);
            comboboxFirstLevel.setValue(currentfirstlevel);

        }
    }

    /**
     * Sets the first division combo box list based on the country selected
     */
    @FXML
    void onActionCountryComboBox(ActionEvent event) {
        comboboxFirstLevel.setValue(null);
        countries currentcountry = comboboxCountry.getValue();
        comboboxFirstLevel.setItems(firstleveldivisionsDAO.showFirstLevels(currentcountry.getCountryID()));
    }

    /**
     * Lambda Expression, interface for lambdaAlert is in /helper/lambdaAlert
     */
    lambdaAlert currentAlert = e -> {
        if (e == 1) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Enter Name");
            newAlert.showAndWait();
        }
        else if (e == 2) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Enter Address");
            newAlert.showAndWait();
        }
        else if (e == 3) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Enter Postal");
            newAlert.showAndWait();
        }
        else if (e == 4) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Enter Phone");
            newAlert.showAndWait();
        }
        else if (e == 5) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Select Country");
            newAlert.showAndWait();
        }
        else if (e == 6) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Select First Level Division");
            newAlert.showAndWait();
        }
        else if (e == 7) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("Success");
            newAlert.setContentText("New customer added");
            newAlert.showAndWait();
        }
        else if (e == 8) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("No customer was selected to update");
            newAlert.showAndWait();
        }
        else if (e == 9) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("Success");
            newAlert.setContentText("Updated customer data");
            newAlert.showAndWait();
        }
        else if (e == 10) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("No customer was selected to delete");
            newAlert.showAndWait();
        }
        else if (e == 11) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("Success");
            newAlert.setContentText("Customer deleted");
            newAlert.showAndWait();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // when opening customers menu, display all customers in database joined with their first division name

        columnID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        columnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        columnFirstLevel.setCellValueFactory(new PropertyValueFactory<>("firstDivisionName"));
        columncountry.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        tableCustomers.setItems(customersDAO.getAllCustomers());

        // defunct: used SQL SORT BY query for customer data instead of trying to sort tableview
        // columnID.setSortType(TableColumn.SortType.ASCENDING);

        // load comboboxes with country and first level division data
        comboboxCountry.setItems(countriesDAO.getAllCountries());
        comboboxCountry.setPromptText("Select Country");

        // divisions must be loaded dynamically when the country box is selected, not initialized
        // comboboxFirstLevel.setItems(firstleveldivisionsDAO.getAllFirstLevelDivisions());
        comboboxFirstLevel.setPromptText("Select First Level");




    }
}
