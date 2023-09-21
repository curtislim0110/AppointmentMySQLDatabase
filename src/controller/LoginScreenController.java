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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.usersDAO.usersLogin;

/**
 *  This class controls the login screen, which is the first screen the user sees when starting the program.
 */
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

    /**
     * This is the action button for the login screen.  When this button is pressed, the username and password
     * is verified with the database.  If there is incomplete or incorrect login information, an error alert will
     * display on the screen.  Otherwise, upon a successful login all appointments beginning within 15 minutes are
     * displayed in alert messages and the screen changes to the main menu.  Additionally, all successful or failed
     * login attempts are recorded in the external file login_activity.txt, located in the root folder.
     * @param event
     * @throws IOException
     */
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
     * Lambda Expression #2, interface and description is in /helper/lambdaTextInterface.  The lambda code slightly
     * reduces the length of the expression, improving code readability.
     * This method appends login_activity.txt with records of successful or failed user logins in UTC.
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

    /**
     * This method initializes labels on the login screen with language depending on the system default setting.
     * English and French are supported languages.  The location label will display the local user ZoneID.
     * @param url
     * @param resourceBundle
     */
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
