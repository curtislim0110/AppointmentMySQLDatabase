package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
import DAO.customersDAO;
import DAO.usersDAO;
import helper.lambdaAlert;
import helper.timeHelper;
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
import javafx.stage.Stage;
import model.appointments;
import model.contacts;
import model.customers;
import model.users;

import java.io.IOException;
import java.net.URL;
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

            // need to somehow get dates outside of if statements for appointment comparison
            LocalDate startDate = datePickerStart.getValue();
            LocalDate endDate = datePickerEnd.getValue();
            LocalTime startTime = comboStartTime.getSelectionModel().getSelectedItem();
            LocalTime endTime = comboEndTime.getSelectionModel().getSelectedItem();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

            int customerID = comboCustomerID.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = comboUserID.getSelectionModel().getSelectedItem().getUser_ID();
            int contactID = comboContactName.getSelectionModel().getSelectedItem().getContactID();

            // check for overlapping appointments
            if (appointments.addConflictCheck(customerID, startDateTime, endDateTime)) {
                // error alerts moved into addConflictCheck method
            }

            // if there are no overlapping appointments, add new appointment and refresh table
            else {
                appointmentsDAO.addAppointment(title, description, location, type, startDateTime, endDateTime, customerID, userID, contactID);
                tableAppointments.setItems(appointmentsDAO.getAllAppointments());
            }

        }

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {
        if (true) {
            currentAlert.lambdaAlertMethod(1);
        }
        else if (true) {
            currentAlert.lambdaAlertMethod(1);
        }


    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionAppointmentsAll(ActionEvent event) {

    }

    @FXML
    void onActionAppointmentsMonth(ActionEvent event) {

    }

    @FXML
    void onActionAppointmentsWeek(ActionEvent event) {

    }

    lambdaAlert currentAlert = e -> {
        if (e == 1) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setTitle("Error");
            newAlert.setContentText("Empty text field or missing selection");
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

