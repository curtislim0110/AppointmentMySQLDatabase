package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
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



    public void onActionLoginButton(ActionEvent actionEvent) throws IOException {

        // get username and password from text fields
        String usernameInput = usernametxt.getText();
        String passwordInput = passwordtxt.getText();

        loginAttemptTXT(true);
        loginAttemptTXT(false);



    }

    public void loginAttemptTXT(boolean loginBooleanType) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String filename = "login_activity.txt";

        FileWriter currentFileWriter = new FileWriter(filename, true);
        PrintWriter loginOutputFile = new PrintWriter(currentFileWriter);

        if (loginBooleanType) {
            loginOutputFile.println(usernametxt.getText() + " had a successful login at " + now + " " + ZoneId.systemDefault());
        }

        if (!loginBooleanType) {
            loginOutputFile.println(usernametxt.getText() + " had a failed login at " + now + " " + ZoneId.systemDefault());
        }

        // File must be closed or else output is blank
        loginOutputFile.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login Initialized!");
    }



}
