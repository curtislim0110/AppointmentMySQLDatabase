package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
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

/**
 * This class controls the fxml screen that displays a report of appointments filtered by contact
 */
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
     * When a selection is made from the contact combo box, a list of filtered appointments is displayed
     * @param event
     */
    @FXML
    void onActionComboContact(ActionEvent event) {

        // load table with appointments based on contactID
        int contactID = comboboxContacts.getSelectionModel().getSelectedItem().getContactID();
        tableAppointments.setItems(appointmentsDAO.getApptByContact(contactID));
    }

    /**
     * This method initializes the table with columns and the combo box with a list of contacts
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns
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

        // Initialize combo box list of contacts
        comboboxContacts.setItems(contactsDAO.getAllContacts());
    }
}