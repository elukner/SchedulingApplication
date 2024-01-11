package controller;
/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

/**
 * NOTE: Delete When Finished
 *
 * Notes from Requirements:
 *The controller package will hold Controller
 * classes that contain Business Logic for your views.
 *
 * Use lambda expressions for event handling within your JavaFX controllers.
 * For instance, if you're handling button clicks or other UI interactions,
 * you can utilize lambda expressions to define event handlers.
 *
 * // Example in a JavaFX controller
 * button.setOnAction(event -> {
 *     // Handle button click event here
 * });
 **/

import helper.FileIOManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Write code that generates accurate information in each of the following reports and will display the
 * reports in the user interface:
 *
 * Note: You do not need to save and print the reports to a file or provide a screenshot.
 * -the total number of customer appointments by type and month
 * -a schedule for each contact in your organization that includes appointment ID, title, type and description,
 * start date and time, end date and time, and customer ID
 *-an additional report of your choice that is different from the two other required reports
 * in this prompt and from the user log-in date and time stamp that will be tracked in part C
 */
public class ReportsController extends Application {
    private Stage stage;
    private Parent scene;

    public void onActionBackBtn(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/reports.fxml"));
        //   primaryStage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void stop() throws Exception{
        FileIOManager.deleteCurrentFile();

    }
}
