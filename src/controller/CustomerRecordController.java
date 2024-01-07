package controller;
/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
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


/**
 * Write code that provides the following customer record functionalities:
 *
 * Customer records and appointments can be added, updated, and deleted.
 *-When deleting a customer record, all of the customer’s appointments must be deleted first,
 * due to foreign key constraints.
 *-When adding and updating a customer, text fields are used to collect the following data:
 * customer name, address, postal code, and phone number.
 *-Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and
 * country data are collected using separate combo boxes.
 *
 *
 *
 * Note: The address text field should not include first-level division and country data.
 * Please use the following examples to format addresses:
 *
 * •  U.S. address: 123 ABC Street, White Plains
 *
 * •  Canadian address: 123 ABC Street, Newmarket
 *
 * •  UK address: 123 ABC Street, Greenwich, London
 *
 *
 *
 * When updating a customer, the customer data autopopulates in the form.
 *
 *
 *
 * Country and first-level division data is prepopulated in separate combo boxes or lists in the user interface for
 * the user to choose. The first-level list should be filtered by the user’s selection of a country
 * (e.g., when choosing U.S., filter so it only shows states).
 *
 *All of the original customer information is displayed on the update form.
 *Customer_ID must be disabled.
 *
 * All of the fields can be updated except for Customer_ID.
 *
 * Customer data is displayed using a TableView, including first-level division data. A list of all the customers
 * and their information may be viewed in a TableView, and updates of the data can be performed in text
 * fields on the form.
 *
 * When a customer record is deleted, a custom message should display in the user interface.
 */

/**
 * This FXML class is the Customer Record controller that contains business logic for the Customer Record view.
 */
public class CustomerRecordController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method initializes this Customer Record controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
