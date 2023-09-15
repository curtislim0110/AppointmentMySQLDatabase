package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class loginscreencontroller implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private Button loginButton;

    @FXML
    private Label passwordlabel;

    @FXML
    private TextField passwordtxt;

    @FXML
    private Label titlelabel;

    @FXML
    private Label usernamelabel;

    @FXML
    private TextField usernametxt;



    public void onActionLoginButton(ActionEvent actionEvent) {
        System.out.println("Login Button clicked!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login Initialized!");
    }



}
