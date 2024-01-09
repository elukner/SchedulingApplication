package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import dao.UsersDaoImpl;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;

/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

/**
 * NOTES
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
 * This FXML class is the login controller that contains business logic for the login view.
 */
public class LoginController implements Initializable {

    @FXML // fx:id="passwordLbl"
    private Label passwordLbl; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTxt"
    private TextField passwordTxt; // Value injected by FXMLLoader

    @FXML // fx:id="userLocationLbl"
    private Label userLocationLbl; // Value injected by FXMLLoader

    @FXML // fx:id="userLocationIDLbl"
    private Label userLocationIDLbl; // Value injected by FXMLLoader

    @FXML // fx:id="usernameLbl"
    private Label usernameLbl; // Value injected by FXMLLoader

    @FXML // fx:id="usernamerTxt"
    private TextField usernameTxt; // Value injected by FXMLLoader

    @FXML // fx:id="loginBtn"
    private Button loginBtn;// Value injected by FXMLLoader

    private Stage stage;
    private Parent scene;

    Locale france = new Locale("fr", "FR");

    /**
     * TODO comment
     *
     * @param event
     */
    @FXML
    void onActionLogInBtn(ActionEvent event) throws IOException {

//    -accepts username and password and provides an appropriate error message
        if (validateLogin(usernameTxt.getText(), passwordTxt.getText())) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**
     * This method accepts username and password and provides an appropriate error message.
     *
     * @param username the user's username that is used to log in
     * @param password the user's password that is used to log in
     * @return appropriate error message if user logs in with incorrect username and password
     */
    public Boolean validateLogin(String username, String password) {
        ObservableList<Users> userList = UsersDaoImpl.getUser(username, password);

        if (isEmptyField(usernameTxt, passwordTxt)) {
            showAlert(Alert.AlertType.ERROR, "Form", "eUsernamePassword");
            return false;
        }

        if (usernameTxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form", "eUsername");
            return false;
        }

        if (passwordTxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form", "ePassword");
            return false;
        }

        if (!userList.isEmpty() && !usernameTxt.getText().isEmpty() && !passwordTxt.getText().isEmpty()) {
            Users user = userList.get(0);
            if (!password.equals(user.getPassword())) {
                showAlert(Alert.AlertType.ERROR, "Form", "cPassword");
                return false;
            }
            if (!username.equals(user.getUserName())) {
                showAlert(Alert.AlertType.ERROR, "Form", "cUsername");
                return false;
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Form", "cUserPassword");
            return false;
        }
        return true;
    }

    /**
     *
     * @param usernameTxt
     * @param passwordTxt
     * @return
     */
    private boolean isEmptyField(TextField usernameTxt, TextField passwordTxt) {
        return usernameTxt.getText().isEmpty() && passwordTxt.getText().isEmpty();
    }



    @FXML
    public void onActionUsernameTxt(ActionEvent actionEvent) {
//        // action event
//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                usernamerTxt.getText();
//            }
//        };
//
//        // when enter is pressed
//        usernamerTxt.setOnAction(event);


    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        //      -automatically translates error control messages into English or French based on the user’s computer language setting
//  Note: Some operating systems require a reboot when changing the language settings.
        ResourceBundle resourceBundle= ResourceBundle.getBundle("Nat", Locale.getDefault());


        Alert alert = new Alert(alertType);
        alert.setTitle(resourceBundle.getString(title));
        alert.setHeaderText(null);
        alert.setContentText(resourceBundle.getString(message));
        alert.show();
    }


    /**
     * construct and set scenes in the application
     *
     * @param stage
     */
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * This method initializes this login controller class
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle= ResourceBundle.getBundle("Nat", Locale.getDefault());

        //      -displays the log-in form in English or French based on the user’s computer language setting to translate all the text, labels, buttons, and errors on the form
        //      -determines the user’s location (i.e., ZoneId) and displays it in a label on the log-in form

            loginBtn.setText(resourceBundle.getString("Log")+" "+ resourceBundle.getString("in"));
            usernameLbl.setText(resourceBundle.getString("Username"));
            passwordLbl.setText(resourceBundle.getString("Password"));
            userLocationLbl.setText(resourceBundle.getString("Location"));
            userLocationIDLbl.setText(ZoneId.systemDefault().toString());

        //Locale.setDefault(france);
        //loginBtn.setText(resourceBundle.getString("hello"));

//        if (Locale.getDefault().getLanguage().equals("fr")) {
//            System.out.println(resourceBundle.getString("hello"));
//            System.out.println("hello");
//
//        }
    }
}


