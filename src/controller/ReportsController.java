package controller;

import DAO.monthtypereportsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.monthtypereports;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @FXML
    private TableColumn<?, ?> columnMonth;

    @FXML
    private TableColumn<?, ?> columnTotalCount;

    @FXML
    private TableColumn<?, ?> columnType;

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
        // Initialize tableview with report data
        columnMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnTotalCount.setCellValueFactory(new PropertyValueFactory<>("appointmentcount"));

        tableMonthType.setItems(monthtypereportsDAO.getMonthTypeReport());
    }
}