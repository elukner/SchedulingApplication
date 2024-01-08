package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import dao.UsersDaoImpl;
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;

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
 *
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
 */

/**
 * Create a log-in form with the following capabilities:
 *
 * -accepts username and password and provides an appropriate error message
 *
 * -determines the user’s location (i.e., ZoneId) and displays it in a label on the log-in form
 *
 * -displays the log-in form in English or French based on the user’s computer language setting to translate all the text, labels, buttons, and errors on the form
 *
 * -automatically translates error control messages into English or French based on the user’s computer language setting
 *
 *
 * Note: Some operating systems require a reboot when changing the language settings.
 */
public class LoginController implements Initializable {


    /**
     * This method accepts username and password and provides an appropriate error message.
     *
     * @param username the user's username that is used to log in
     * @param password the user's password that is used to log in
     * @return appropriate error message if user logs in with incorrect username and password
     */
    public String validateLogin(String username, String password){
        return "appropriate error message";
    }

    public void onActionLogInBtn(ActionEvent actionEvent) {
        System.out.println("Here");
    }

    /**
     * This method initializes this login controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
