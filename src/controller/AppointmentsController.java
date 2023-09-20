package controller;

import DAO.*;
import helper.lambdaAlert;
import helper.timeHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    @FXML
    private TableColumn<?, ?> columnAppointmentID;

    @FXML
    private TableColumn<?, ?> columnContactName;

    @FXML
    private TableColumn<?, ?> columnCustomerID;

    @FXML
    private TableColumn<?, ?> columnDescription;

    @FXML
    private TableColumn<?, ?> columnEndTime;

    @FXML
    private TableColumn<?, ?> columnLocation;

    @FXML
    private TableColumn<?, ?> columnStartTime;

    @FXML
    private TableColumn<?, ?> columnTitle;

    @FXML
    private TableColumn<?, ?> columnType;

    @FXML
    private TableColumn<?, ?> columnUserID;

    @FXML
    private TableView<appointments> tableAppointments;

    @FXML
    private ComboBox<contacts> comboContactName;

    @FXML
    private ComboBox<customers> comboCustomerID;

    @FXML
    private ComboBox<LocalTime> comboEndTime;

    @FXML
    private ComboBox<LocalTime> comboStartTime;

    @FXML
    private ComboBox<users> comboUserID;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private TextField textAppointmentID;

    @FXML
    private TextField textDescription;

    @FXML
    private TextField textLocation;

    @FXML
    private TextField textTitle;

    @FXML
    private TextField textType;

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionAddAppointment(ActionEvent event) {
        // check for null values or empty text fields and display an error message if input is missing
        if (textTitle.getText().isEmpty() || textDescription.getText().isEmpty() || textLocation.getText().isEmpty() || textType.getText().isEmpty()
                || datePickerStart.getValue() == null || datePickerEnd.getValue() == null || comboStartTime.getValue() == null || comboEndTime.getValue() == null
                || comboCustomerID == null || comboUserID == null || comboContactName == null)  {
            currentAlert.lambdaAlertMethod(1);
        }
        else {
            // if there is input for all textboxes and combo box selections have been made, load data into local variables
            String title = textTitle.getText();
            String description = textDescription.getText();
            String location = textLocation.getText();
            String type = textType.getText();

            LocalDate startDate = datePickerStart.getValue();
            LocalDate endDate = datePickerEnd.getValue();
            LocalTime startTime = comboStartTime.getSelectionModel().getSelectedItem();
            LocalTime endTime = comboEndTime.getSelectionModel().getSelectedItem();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

            int customerID = comboCustomerID.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = comboUserID.getSelectionModel().getSelectedItem().getUser_ID();
            int contactID = comboContactName.getSelectionModel().getSelectedItem().getContactID();

            // verify that start time is before end time
            if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setTitle("Error");
                newAlert.setContentText("Starting date/time must not be equal to or after ending date/time");
                newAlert.showAndWait();
            }

            // check for overlapping appointments
            else if (appointments.addConflictCheck(customerID, startDateTime, endDateTime)) {
                // error alerts moved into addConflictCheck method
            }

            // if there are no errors or overlapping appointments, add a new appointment and refresh table
            else {
                appointmentsDAO.addAppointment(title, description, location, type, startDateTime, endDateTime, customerID, userID, contactID);
                tableAppointments.setItems(appointmentsDAO.getAllAppointments());
            }
        }
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {
        // check for null values or empty text fields and display an error message if input is missing
        if (textTitle.getText().isEmpty() || textDescription.getText().isEmpty() || textLocation.getText().isEmpty() || textType.getText().isEmpty()
                || datePickerStart.getValue() == null || datePickerEnd.getValue() == null || comboStartTime.getValue() == null || comboEndTime.getValue() == null
                || comboCustomerID == null || comboUserID == null || comboContactName == null)  {
            currentAlert.lambdaAlertMethod(1);
        }
        else {
            // if there is input for all textboxes and combo box selections have been made, load data into local variables
            int appointmentID = Integer.parseInt(textAppointmentID.getText());
            String title = textTitle.getText();
            String description = textDescription.getText();
            String location = textLocation.getText();
            String type = textType.getText();

            LocalDate startDate = datePickerStart.getValue();
            LocalDate endDate = datePickerEnd.getValue();
            LocalTime startTime = comboStartTime.getSelectionModel().getSelectedItem();
            LocalTime endTime = comboEndTime.getSelectionModel().getSelectedItem();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

            int customerID = comboCustomerID.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = comboUserID.getSelectionModel().getSelectedItem().getUser_ID();
            int contactID = comboContactName.getSelectionModel().getSelectedItem().getContactID();

            // verify that start time is before end time
            if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setTitle("Error");
                newAlert.setContentText("Starting date/time must not be equal to or after ending date/time");
                newAlert.showAndWait();
            }

            // check for overlapping appointments, the appointment should be able to change type without causing an overlap conflict
            else if (appointments.modifyConflictCheck(customerID, startDateTime, endDateTime, appointmentID)) {
                // error alerts moved into addConflictCheck method
            }

            // if there are no errors or overlapping appointments, add a new appointment and refresh table
            else {
                appointmentsDAO.updateAppointment(appointmentID, title, description, location, type,
                        startDateTime, endDateTime, customerID, userID, contactID);
                tableAppointments.setItems(appointmentsDAO.getAllAppointments());
            }
        }
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        // first verify an appointment has been selected
        appointments selectedAppointment = tableAppointments.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            currentAlert.lambdaAlertMethod(1);
        }
        else {
            Alert deleteconfirm = new Alert(Alert.AlertType.WARNING);
            deleteconfirm.setTitle("Warning");
            deleteconfirm.setContentText("Delete appointment?");
            deleteconfirm.getButtonTypes().clear();
            deleteconfirm.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            deleteconfirm.showAndWait();
            if (deleteconfirm.getResult() == ButtonType.OK) {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Alert");
                confirmation.setContentText("Appointment ID: " + tableAppointments.getSelectionModel().getSelectedItem().getAppointmentID()
                        + " of type: " + tableAppointments.getSelectionModel().getSelectedItem().getType() + " was deleted.");
                confirmation.getButtonTypes().clear();
                confirmation.getButtonTypes().addAll(ButtonType.OK);
                confirmation.showAndWait();
                appointmentsDAO.deleteAppointment(tableAppointments.getSelectionModel().getSelectedItem().getAppointmentID());
                tableAppointments.setItems(appointmentsDAO.getAllAppointments());
            }
            else if (deleteconfirm.getResult() == ButtonType.CANCEL) {
                deleteconfirm.close();
            }
        }
    }

    @FXML
    void onActionAppointmentsAll(ActionEvent event) {
        ObservableList<appointments> allAppointmentsList = appointmentsDAO.getAllAppointments();
        tableAppointments.setItems(allAppointmentsList);
    }

    @FXML
    void onActionAppointmentsMonth(ActionEvent event) {
        // Get all appointments in a list, and initialize a second empty list to contain appointments up to 1 month away
        ObservableList<appointments> allAppointmentsList = appointmentsDAO.getAllAppointments();
        ObservableList<appointments> monthAppointmentsList = FXCollections.observableArrayList();

        // Find the date 1 month away from the current local time
        LocalDateTime monthCalendar = LocalDateTime.now().plusMonths(1);

        // Find all appointment starting times within 1 month of the current time
        for (appointments singleAppointment : allAppointmentsList) {
            if (singleAppointment.getAppointmentStart().isBefore(monthCalendar)) {
                monthAppointmentsList.add(singleAppointment);
            }
        }
        tableAppointments.setItems(monthAppointmentsList);
    }

    @FXML
    void onActionAppointmentsWeek(ActionEvent event) {
        // Get all appointments in a list, and initialize a second empty list to contain appointments up to 1 week away
        ObservableList<appointments> allAppointmentsList = appointmentsDAO.getAllAppointments();
        ObservableList<appointments> weekAppointmentsList = FXCollections.observableArrayList();

        // Find the date 1 qwwk away from the current local time
        LocalDateTime weekCalendar = LocalDateTime.now().plusWeeks(1);

        // Find all appointment starting times within 1 week of the current time

        tableAppointments.setItems(weekAppointmentsList);

    }

    @FXML
    void onMouseClickTable(MouseEvent event) throws SQLException {
        if (tableAppointments.getSelectionModel().getSelectedItem() == null) {
            // do nothing if an empty space is clicked on the table
        }
        // otherwise, load the selected row of data from the table into the textboxes
        else {
            // load text boxes
            appointments mouseclickAppointment = tableAppointments.getSelectionModel().getSelectedItem();
            textAppointmentID.setText(Integer.toString(mouseclickAppointment.getAppointmentID()));
            textTitle.setText(mouseclickAppointment.getTitle());
            textDescription.setText(mouseclickAppointment.getDescription());
            textLocation.setText(mouseclickAppointment.getLocation());
            textType.setText(mouseclickAppointment.getType());

            // load dates
            datePickerStart.setValue(mouseclickAppointment.getAppointmentStart().toLocalDate());
            datePickerEnd.setValue(mouseclickAppointment.getAppointmentEnd().toLocalDate());
            comboStartTime.setValue(mouseclickAppointment.getAppointmentStart().toLocalTime());
            comboEndTime.setValue(mouseclickAppointment.getAppointmentEnd().toLocalTime());

            // set combo box for Customer
            String customerName = customersDAO.getCustomerName((mouseclickAppointment.getCustomerID()));
            String customerAddress = customersDAO.getAddress((mouseclickAppointment.getCustomerID()));
            String postal = customersDAO.getPostal((mouseclickAppointment.getCustomerID()));
            String phone = customersDAO.getPhone((mouseclickAppointment.getCustomerID()));
            int divisionID =  customersDAO.getDivisionID((mouseclickAppointment.getCustomerID()));
            String divisionName = customersDAO.getDivisionName(divisionID);
            int countryID = customersDAO.getCountryID(divisionID);
            String countryName = countriesDAO.getCountryName(countryID);
            customers currentcustomer = new customers(mouseclickAppointment.getCustomerID(), customerName, customerAddress, postal, phone, divisionID, divisionName, countryID, countryName);
            comboCustomerID.setValue(currentcustomer);

            // set combo box for User ID
            String currentUsername = usersDAO.getUserName(mouseclickAppointment.getUserID());
            String currentPassword = usersDAO.getPassword(mouseclickAppointment.getUserID());
            users currentuser = new users(mouseclickAppointment.getUserID(), currentUsername, currentPassword);
            comboUserID.setValue(currentuser);

            // set combo box for Contact Name
            String contactName = contactsDAO.getContactName(mouseclickAppointment.getContactID());
            String email = contactsDAO.getEmail(mouseclickAppointment.getContactID());
            contacts currentContact = new contacts(mouseclickAppointment.getContactID(),contactName,email);
            comboContactName.setValue(currentContact);
        }
    }

    lambdaAlert currentAlert = e -> {
        if (e == 1) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Empty text field or missing selection");
            newAlert.showAndWait();
        }
        if (e == 2) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Select an appointment first");
            newAlert.showAndWait();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // load tableview with appointment data
        columnAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        columnEndTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        columnCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        columnUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        columnContactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        tableAppointments.setItems(appointmentsDAO.getAllAppointments());

        // load combo boxes with appropriate data
        ObservableList<customers> customersList = customersDAO.getAllCustomers();
        comboCustomerID.setItems(customersList);
        ObservableList<users> usersList = usersDAO.getUsersList();
        comboUserID.setItems(usersList);
        ObservableList<contacts> contactsList = contactsDAO.getAllContacts();
        comboContactName.setItems(contactsList);

        // initialize combo box for start time in 30 minute local time increments starting at 8am EST
        comboStartTime.setItems(timeHelper.appointmentHoursEST(LocalTime.of(8, 0)));
        // initialize combo box for end time in 30 minute local time increments starting at 8:30am EST
        comboEndTime.setItems(timeHelper.appointmentHoursEST(LocalTime.of(8, 30)));
    }


}

