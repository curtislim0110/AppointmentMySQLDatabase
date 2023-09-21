package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DAO.JDBC;
import java.util.Locale;

/**
 * Appointment Scheduling Program
 *
 * Javadoc location: /src/javadoc
 *
 * @author Curtis Lim
 */

public class Main extends Application {
    /**
     * Initializes the program and opens connection to the database.  The database connection is closed after the
     * program is closed.  Removing the comment on Locale.setDefault(new Locale("fr")); will set the locale into
     * french language for testing purposes.
     * @param args
     */
    public static void main(String[] args) {
        // Commented next line is for testing system default language in french
        // Locale.setDefault(new Locale("fr"));
        Locale new_locale = Locale.getDefault();
        System.out.println("Current system locale: " + new_locale);

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    /**
     * Launches the program into the login screen.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        primaryStage.setTitle("Login Screen for Appointment System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
