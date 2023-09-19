package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
import DAO.customersDAO;
import DAO.usersDAO;
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
/*
        // load data into local variables
        string title = titleTxt.getText();
        string description = descriptionTxt.getText();
        string location = locationTxt.getText();
        string type = typeTxt.getText();
        contactId = contactComboBx.getSelectionModel().getSelectedItem().getContactId();
        customerId = customerComboBx.getSelectionModel().getSelectedItem().getCustomerId();
        userId = userComboBx.getSelectionModel().getSelectedItem().getUserId();
        startDate = startDatePicker.getValue();
        endDate = endDatePicker.getValue();
        startTime = startTimeComboBx.getSelectionModel().getSelectedItem();
        endTime = endTimeComboBx.getSelectionModel().getSelectedItem();
        startDateTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(),
                startTime.getHour(), startTime.getMinute());
        endDateTime = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(),
                endTime.getHour(), endTime.getMinute());
*/
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

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

        // initialize combo boxes for time in 30 minute local time increments
        comboStartTime.setItems(timeHelper.appointmentHoursEST());
    }

}

