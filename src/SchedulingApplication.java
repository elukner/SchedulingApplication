/**
 * Project: SchedulingApplication
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import helper.FileIOManager;
import helper.JDBC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.ZoneId;

/**
 * The main class for the Scheduling Application, extending JavaFX's Application class.
 * This class manages the initialization and termination of the application, including the UI setup.
 */
public class SchedulingApplication extends Application {

    /**
     * The main entry point for the JavaFX application.
     * Initializes the primary stage and sets up the login view.
     *
     * @param primaryStage The primary stage of the application.
     * @throws Exception If an exception occurs during the initialization.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("Scheduling Desktop Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The method called when the application is stopped.
     * Performs cleanup tasks, such as deleting the current login activity file.
     *
     * @throws Exception If an exception occurs during the cleanup.
     */
    @Override
    public void stop() throws Exception{
        FileIOManager.deleteCurrentFile();

    }

    /**
     * The main method of the application.
     * Opens a connection to the database, launches the JavaFX application, and closes the database connection afterward.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
        //ZoneId.getAvailableZoneIds().stream().filter(c->c.contains("America")).forEach(System.out::println);
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }


}
