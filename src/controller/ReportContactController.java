package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
import DAO.countriesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.appointments;
import model.contacts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportContactController implements Initializable {
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
    private TableColumn<?, ?> columnTypeReport2;

    @FXML
    private TableColumn<?, ?> columnUserID;

    @FXML
    private ComboBox<contacts> comboboxContacts;

    @FXML
    private TableView<appointments> tableAppointments;

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionComboContact(ActionEvent event) {

        // load table with appointments based on contactID
        int contactID = comboboxContacts.getSelectionModel().getSelectedItem().getContactID();
        tableAppointments.setItems(appointmentsDAO.getApptByContact(contactID));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // initialize table columns
        columnAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnTypeReport2.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        columnEndTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        columnCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        columnUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        columnContactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));

        // initialist combo box list of contacts
        comboboxContacts.setItems(contactsDAO.getAllContacts());
    }
}