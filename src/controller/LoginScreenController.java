package controller;

import helper.lambdaTextInterface;
import helper.loginAlert;
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
import java.time.ZonedDateTime;
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

        // If the username and/or password is blank, display language appropriate error message.
        if (usernameInput.isEmpty() || passwordInput.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(languageBundle.getString("error"));
            alert.setContentText(languageBundle.getString("mistake"));
            alert.show();
        }

        // if usersLogin returns false, this failed login attempt is recorded in login_activity.txt
        else if (!usersLogin(usernameInput, passwordInput)) {
            loginAttemptTXT.lambdaBoolean(false);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(languageBundle.getString("error"));
            alert.setContentText(languageBundle.getString("mistake"));
            alert.show();
        }

        // if usersLogin returns true, a match was found in the database. the successful login attempt is recorded in login_activity.txt and the screen changes to the main menu
        else if (usersLogin(usernameInput, passwordInput)) {
            loginAttemptTXT.lambdaBoolean(true);

            // before entering main menu, call login alert function to check for appointments within 15 minutes of login
            loginAlert.loginAppointmentAlert();

            Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


    }

    /**
     * Lambda Expression #2, interface and description is in /helper/lambdaTextInterface
     */
    lambdaTextInterface loginAttemptTXT = successBoolean -> {
        String filename = "login_activity.txt";
        FileWriter currentFileWriter = new FileWriter(filename, true);
        PrintWriter loginOutputFile = new PrintWriter(currentFileWriter);

        if (successBoolean) {
            loginOutputFile.println(usernametxt.getText() + " had a successful login at " + ZonedDateTime.now(ZoneId.of("UTC")));
        }

        else if (!successBoolean) {
            loginOutputFile.println(usernametxt.getText() + " had a failed login at " + ZonedDateTime.now(ZoneId.of("UTC")));
        }

        // File must be closed or else output text file is blank
        loginOutputFile.close();
    };

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
