package controller;

/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/25/2024
 * Time: 2:04 PM
 */
import helper.FileIOManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * The MainMenuController class serves as the controller for the main menu view in the application.
 * It extends the Application class and implements the Initializable interface to handle JavaFX initialization.
 * This controller manages navigation between different views in the application and provides actions for the main menu buttons.
 */
public class MainMenuController extends Application implements Initializable {

    private Stage stage;
    private Parent scene;

    /**
     * Handles the action when the "Customer Record" button is clicked.
     * Loads the customer record view and navigates to it.
     *
     * @param event The ActionEvent triggered by clicking the button.
     * @throws IOException If an error occurs during the loading of the customer record view.
     */
    @FXML
    void onActionCustomerRecordBtn(ActionEvent event) throws IOException {
        String viewPath = "../view/customerRecord.fxml";
        loadPage(event,viewPath);
    }

    /**
     * Handles the action when the "Exit" button is clicked.
     * Deletes the current file and exits the application.
     *
     * @param event The ActionEvent triggered by clicking the button.
     * @throws IOException If an error occurs during file deletion.
     */
    @FXML
    void onActionExitBtn(ActionEvent event) throws IOException  {
        FileIOManager.deleteCurrentFile();
       System.exit(0);

    }

    /**
     * Handles the action when the "Reports" button is clicked.
     * Loads the reports view and navigates to it.
     *
     * @param event The ActionEvent triggered by clicking the button.
     * @throws IOException If an error occurs during the loading of the reports view.
     */
    @FXML
    void onActionReportsBtn(ActionEvent event) throws IOException  {
        String viewPath = "../view/reports.fxml";
        loadPage(event,viewPath);
    }

    /**
     * Handles the action when the "Scheduling" button is clicked.
     * Loads the scheduling view and navigates to it.
     *
     * @param event The ActionEvent triggered by clicking the button.
     * @throws IOException If an error occurs during the loading of the scheduling view.
     */
    @FXML
    void onActionSchedulingBtn(ActionEvent event) throws IOException {
        String viewPath = "../view/scheduling.fxml";
        loadPage(event,viewPath);

    }

    /**
     * Loads and navigates to a new view specified by the provided FXML file path.
     * This method is called by various button actions to transition to different views within the application.
     *
     * @param event    The ActionEvent triggered by clicking a button that initiates the view transition.
     * @param viewPath The relative path to the FXML file of the view to be loaded.
     * @throws IOException If an error occurs during the loading of the specified FXML file.
     */
    private void loadPage(ActionEvent event,String viewPath) throws IOException {
        // Get the stage from the event's source button
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        // Load the FXML file for the specified view
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        // Set the loaded scene as the content of the stage
        stage.setScene(new Scene(scene));
        // Show the stage with the newly loaded scene
        stage.show();
    }

    /**
     * Initializes the MainMenuController.
     * This method is called automatically after the FXML file is loaded.
     *
     * @param url            The location used to resolve relative paths for the root object or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Starts the JavaFX application, loading and displaying the main stage with the reports view.
     *
     * @param stage The primary stage for the application.
     * @throws Exception If an error occurs during the loading of the reports view.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/reports.fxml"));
        stage.setTitle("Scheduling Desktop Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Stops the application.
     * Deletes the current file upon application termination.
     *
     * @throws Exception If an error occurs during file deletion.
     */
    @Override
    public void stop() throws Exception{
        FileIOManager.deleteCurrentFile();

    }
}
