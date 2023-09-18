package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DAO.JDBC;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        primaryStage.setTitle("Login Screen for Appointment System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        // Commented next line is for testing system default language in french
        // Locale.setDefault(new Locale("fr"));
        Locale new_locale = Locale.getDefault();
        System.out.println("Current system locale: " + new_locale);

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
