package controller;

import DAO.monthtypereportsDAO;
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
import model.monthtypereports;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

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
    private TableColumn<?, ?> columnMonth;

    @FXML
    private TableColumn<?, ?> columnStartTime;

    @FXML
    private TableColumn<?, ?> columnTitle;

    @FXML
    private TableColumn<?, ?> columnTotalCount;

    @FXML
    private TableColumn<?, ?> columnTypeReport1;

    @FXML
    private TableColumn<?, ?> columnTypeReport2;

    @FXML
    private TableColumn<?, ?> columnUserID;

    @FXML
    private ComboBox<?> comboboxContacts;

    @FXML
    private TableView<appointments> tableAppointments;

    @FXML
    private TableView<monthtypereports> tableMonthType;

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize month/type/total count report tableview and columns
        columnMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        columnTypeReport1.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnTotalCount.setCellValueFactory(new PropertyValueFactory<>("appointmentcount"));
        tableMonthType.setItems(monthtypereportsDAO.getMonthTypeReport());

        // Initialize contact schedule with combo box and prompt
        comboboxContacts.setPromptText("Select Contact");

    }
}