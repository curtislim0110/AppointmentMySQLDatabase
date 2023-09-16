package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.usersDAO.usersLogin;

public class LoginScreenController implements Initializable {

    @FXML
    private TextField localtionlabeltxt;

    @FXML
    private Label locationlabel;

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


    public void onActionLoginButton(ActionEvent event) throws IOException {

        ResourceBundle languageBundle = ResourceBundle.getBundle("language/language", Locale.getDefault());

        // get username and password from text fields
        String usernameInput = usernametxt.getText();
        String passwordInput = passwordtxt.getText();

        // call the usersLogin method to see if the login input has a match in the database. if there is a match, store the login data in currentuser
        users currentuser = usersLogin(usernameInput, passwordInput);

        // If the username and/or password is blank, display language appropriate error message.
        if (usernameInput.isEmpty() || passwordInput.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(languageBundle.getString("error"));
            alert.setContentText(languageBundle.getString("mistake"));
            alert.show();
        }

        // currentuser is null if there is no matching user in the database. this failed login attempt is recorded in login_activity.txt
        else if (currentuser == null) {
            loginAttemptTXT(false);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(languageBundle.getString("error"));
            alert.setContentText(languageBundle.getString("mistake"));
            alert.show();

        }

        // if currentuser is not null, a match was found in the database. the successful login attempt is recorded in login_activity.txt and the screen changes to the main menu
        else if (currentuser != null) {
            loginAttemptTXT(true);

            Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


    }

    public void loginAttemptTXT(boolean loginBooleanType) throws IOException {

        String filename = "login_activity.txt";
        FileWriter currentFileWriter = new FileWriter(filename, true);
        PrintWriter loginOutputFile = new PrintWriter(currentFileWriter);

        if (loginBooleanType) {
            loginOutputFile.println(usernametxt.getText() + " had a successful login at " + LocalDateTime.now() + " " + ZoneId.systemDefault());
        }

        if (!loginBooleanType) {
            loginOutputFile.println(usernametxt.getText() + " had a failed login at " + LocalDateTime.now() + " " + ZoneId.systemDefault());
        }

        // File must be closed or else output text file is blank
        loginOutputFile.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Sets the location text box (which is not editable) to system default
        localtionlabeltxt.setText(String.valueOf(ZoneId.systemDefault()));

        // Open the system default language bundle, either english or french, and change labels appropriately
        ResourceBundle languageBundle = ResourceBundle.getBundle("language/language", Locale.getDefault());
        titlelabel.setText(languageBundle.getString("title"));
        usernamelabel.setText(languageBundle.getString("username"));
        passwordlabel.setText(languageBundle.getString("password"));
        loginButton.setText(languageBundle.getString("login"));
        locationlabel.setText(languageBundle.getString("location"));


    }



}
