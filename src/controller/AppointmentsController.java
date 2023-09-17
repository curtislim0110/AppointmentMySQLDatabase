package controller;

import DAO.customersDAO;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private TableView<?> tableAppointments;

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

        columnAppointmentID.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnEndTime.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnCustomerID.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnUserID.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));
        columnContactName.setCellValueFactory(new PropertyValueFactory<>("xxxxx"));

        tableAppointments.setItems(appointmentsDAO.getAllAppointments());

    }

}

