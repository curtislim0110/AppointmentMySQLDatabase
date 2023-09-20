package controller;

import DAO.reportsortedDAO;
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
import model.reportsorted;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportSortedController implements Initializable {

    @FXML
    private TableColumn<?, ?> columnMonth;

    @FXML
    private TableColumn<?, ?> columnTotalCount;

    @FXML
    private TableColumn<?, ?> columnTypeReport1;

    @FXML
    private TableView<reportsorted> tableMonthType;

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
        tableMonthType.setItems(reportsortedDAO.getMonthTypeReport());

    }
}