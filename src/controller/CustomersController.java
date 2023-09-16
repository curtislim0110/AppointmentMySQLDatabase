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
import model.customers;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private ComboBox<?> comboboxCountry;

    @FXML
    private ComboBox<?> comboboxFirstLevel;

    @FXML
    private TableView<customers> tableCustomers;

    @FXML
    private TextField textAddress;

    @FXML
    private TextField textCustomerID;

    @FXML
    private TextField textCustomerNAme;

    @FXML
    private TextField textPhone;

    @FXML
    private TextField textPostal;

    @FXML
    void onActionAdd(ActionEvent event) {

    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionUpdate(ActionEvent event) {

    }

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

        // when opening customers menu, display all customers in database joined with their first division name
        tableCustomers.setItems(customersDAO.getAllCustomers());
        columnID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        columnPostal.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        columnFirstLevel.setCellValueFactory(new PropertyValueFactory<>("firstDivisionName"));
        columncountry.setCellValueFactory(new PropertyValueFactory<>("countryName"));

    }
}
