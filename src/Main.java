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
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginscreen.fxml"));
        primaryStage.setTitle("Main Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        // Setting and displaying the Locale to french for testing only
        // Locale.setDefault(new Locale("fr"));
        Locale new_locale = Locale.getDefault();
        System.out.println("Current system locale: " + new_locale);

        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
