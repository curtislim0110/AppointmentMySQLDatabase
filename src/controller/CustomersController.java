package controller;

import DAO.appointmentsDAO;
import DAO.countriesDAO;
import DAO.customersDAO;
import DAO.firstleveldivisionsDAO;
import helper.lambdaAlertInterface;
import javafx.collections.ObservableList;
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
import model.appointments;
import model.countries;
import model.customers;
import model.firstleveldivisions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  This class controls the customers screen.  The user can add, update, or delete customers on this screen.
 */
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

    /**
     * This is the add customer button.  When pressed, the method first checks to see if there are any empty text
     * fields or missing selections and displays an appropriate error message.  If there is no error message, a new
     * customer is added an informational message is displayed.
     * @param event
     */
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

    /**
     * This is the update customer button.  When pressed, the method first checks to see if there are any empty text
     * fields or missing selections and displays an appropriate error message.  If there is no error message, the
     * existing customer is updated based on the text fields and combo box selections.  A informational message is
     * displaying after successfully updating a customer.
     * @param event
     */
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

    /**
     * This is the delete customer button.  When pressed, the method first checks to see if the user has already
     * selected a customer from the table.  If not, an error message displays.  If a customer was selected, the method
     * checks to see if there are any appointments associated with a customer.  Next, a deletion confirmation message
     * is displaying, informing the user if there are any existing appointments associated with the selected customer.
     * AFter the user confirms deletion, the customer and any associated appointments are also deleted.
     */
    @FXML
    void onActionDelete(ActionEvent event) {

        // load a customer object with the mouse-selected customer
        customers currentCustomer = tableCustomers.getSelectionModel().getSelectedItem();

        // if no customer is selected, display error alert and exit
        if (currentCustomer == null) {
            currentAlert.lambdaAlertMethod(10);
            return;
        }

        // load all appointments in database
        ObservableList<appointments> appointmentList = appointmentsDAO.getAllAppointments();

        // load the current customer ID
        int currentCustomerID = tableCustomers.getSelectionModel().getSelectedItem().getCustomerID();

        // keeps track of the total number of appointments associated with a customer, used in displaying a custom message
        int appointmentCount = 0;

        // iterate over appointment list, counting appointments associated with a customer in customerAppointmentCount
        for (appointments currentappointment : appointmentList) {
            int loopCustomerID = currentappointment.getCustomerID();
            if (loopCustomerID == currentCustomerID) {
                appointmentCount = appointmentCount + 1;
            }
        }
        // Displays an alert letting the user know they have associated appointments, the quantity and allows them to agree to delete associated appointments as well.

        if (appointmentCount > 0) {
            Alert associatedAppoint = new Alert(Alert.AlertType.WARNING);
            associatedAppoint.setTitle("WARNING");
            associatedAppoint.setContentText("There are " + appointmentCount + " associated appointments for this customer. Confirm deletion.");
            associatedAppoint.getButtonTypes().clear();
            associatedAppoint.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            associatedAppoint.showAndWait();

            if (associatedAppoint.getResult() == ButtonType.OK) {
                // first delete appointments associated with the customer
                for (appointments currentappointment : appointmentList) {
                    if (currentappointment.getCustomerID() == currentCustomerID)
                        appointmentsDAO.deleteAppointment(currentappointment.getAppointmentID());
                }

                // next delete the customer
                customersDAO.deleteCustomer(tableCustomers.getSelectionModel().getSelectedItem().getCustomerID());

                // display confirmation message
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Confirmation");
                confirmation.setContentText("Customer with " + appointmentCount + " appointments was deleted");
                confirmation.getButtonTypes().clear();
                confirmation.getButtonTypes().addAll(ButtonType.OK);
                confirmation.showAndWait();

                // refresh the customer table
                tableCustomers.setItems(customersDAO.getAllCustomers());
            }
            else if (associatedAppoint.getResult() == ButtonType.CANCEL) {
                associatedAppoint.close();
            }
        }

        // If there are no associated appointments for the selected user - an alert will be generated asking to confirm removal of the selected customer
        if (appointmentCount == 0) {
            Alert deleteconfirm = new Alert(Alert.AlertType.WARNING);
            deleteconfirm.setTitle("Warning");
            deleteconfirm.setContentText("Delete customer with no appointments?");
            deleteconfirm.getButtonTypes().clear();
            deleteconfirm.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            deleteconfirm.showAndWait();

            if (deleteconfirm.getResult() == ButtonType.OK) {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Confirmation");
                confirmation.setContentText("Customer with no appointments was deleted");
                confirmation.getButtonTypes().clear();
                confirmation.getButtonTypes().addAll(ButtonType.OK);
                confirmation.showAndWait();
                customersDAO.deleteCustomer(tableCustomers.getSelectionModel().getSelectedItem().getCustomerID());
                tableCustomers.setItems(customersDAO.getAllCustomers());
            }

            else if (deleteconfirm.getResult() == ButtonType.CANCEL) {
                deleteconfirm.close();
            }
        }
    }

    /**
     * Returns the user to the main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This mouse click method activates when the user clicks a row on the table.  The selected row
     * is used to populate text fields and set values to combo boxes.
     * @param event
     */
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
     * Lambda Expression #1, interface and description is in /helper/lambdaAlertInterface.  The lambda code is useful
     * because slightly reduces the length of the expression, improving code readability.
     * This method displays different alerts based on the parameter integer value.
     */
    lambdaAlertInterface currentAlert = e -> {
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
    };

    /**
     * This method initializes the table with columns and the combo box with a list of users.  First level divisions
     * are not loaded here, and are loaded dynamically after the user makes a selection on the country combo box.
     * @param url
     * @param resourceBundle
     */
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
