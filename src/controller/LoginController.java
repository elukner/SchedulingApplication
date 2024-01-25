package controller;


import helper.FileIOManager;
import helper.TimeProcessing;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import dao.UsersDaoImpl;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
 * <p>
 * NOTES
 * The controller package will hold Controller
 * classes that contain Business Logic for your views.
 * <p>
 * Use lambda expressions for event handling within your JavaFX controllers.
 * For instance, if you're handling button clicks or other UI interactions,
 * you can utilize lambda expressions to define event handlers.
 * <p>
 * // Example in a JavaFX controller
 * button.setOnAction(event -> {
 * // Handle button click event here
 * });
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
 * The LoginController class serves as the controller for the login view in the application.
 * It implements the Initializable interface to handle JavaFX initialization.
 * This controller manages user authentication and navigation to the main application view.
 */
public class LoginController implements Initializable {

    @FXML // fx:id="passwordLbl"
    private Label passwordLbl; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTxt"
    private TextField passwordTxt; // Value injected by FXMLLoader

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

    private Users user;
    private int timesClicked;
    private Boolean loginSuccess;

    Locale france = new Locale("fr", "FR");

    /**
     * TODO comment
     *
     * @param event
     */
    @FXML
    void onActionLogInBtn(ActionEvent event) throws IOException {
        setTimesClicked(timesClicked + 1);
//    -accepts username and password and provides an appropriate error message
        if (validateLogin(usernameTxt.getText(), passwordTxt.getText())) {
            setLoginSuccess(true);
            setUserLoggedIn();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } else {
            setLoginSuccess(false);
            setUserLoggedIn();
        }

    }

    public Boolean getLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(Boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public int getTimesClicked() {
        return timesClicked;
    }

    public void setTimesClicked(int timesClicked) {
        this.timesClicked = timesClicked;
    }

    private void setUserLoggedIn() throws IOException {

        ObservableList<Users> userList = UsersDaoImpl.getUser(usernameTxt.getText(), passwordTxt.getText());

        String status = "fail";
        if (!userList.isEmpty()) {
            user = userList.get(0);
            status = getLoginSuccess() ? "success" : "fail";
        }

        FileIOManager.writeToFile(getUserLoggedIn(), getTimesClicked(), LocalDate.now().toString(),
                TimeProcessing.createTimeStamp(), status);
    }

    public String getUserLoggedIn() {
        if (usernameTxt.getText().isEmpty())
            return "null";

        return usernameTxt.getText();
    }

    /**
     * This method accepts username and password and provides an appropriate error message.
     *
     * entering an incorrect username and password
     * @param username the user's username that is used to log in
     * @param password the user's password that is used to log in
     * @return appropriate error message if user logs in with incorrect username and password
     */
    public Boolean validateLogin(String username, String password) {

        Pair<String, Boolean> emptyFieldResult = isEmptyField(usernameTxt, passwordTxt);

        if (emptyFieldResult.getValue()) {
            showAlert(Alert.AlertType.ERROR, "Form", emptyFieldResult.getKey());

            return false;
        }


        ObservableList<Users> userList = UsersDaoImpl.getUser(username, password);

        if (userList.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form", "cUserPassword");
            return false;
        }

        Users user = userList.get(0);

        if (!password.equals(user.getPassword())) {
            showAlert(Alert.AlertType.ERROR, "Form", "cPassword");
            return false;
        }

        if (!username.equalsIgnoreCase(user.getUserName())) {
            showAlert(Alert.AlertType.ERROR, "Form", "cUsername");
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
    private Pair<String, Boolean> isEmptyField(TextField usernameTxt, TextField passwordTxt) {
        boolean isUsernameEmpty = usernameTxt.getText().isEmpty();
        boolean isPasswordEmpty = passwordTxt.getText().isEmpty();

        //Both fields are empty
        if (isUsernameEmpty && isPasswordEmpty) {
            return new Pair<>("eUsernamePassword", true);
        }

        //Username field are empty
        if (isUsernameEmpty) {
            return new Pair<>("eUsername", true);
        }

        //Password field are empty
        if (isPasswordEmpty) {
            return new Pair<>("ePassword", true);
        }

        return new Pair<>("No empty fields", false);
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        //      -automatically translates error control messages into English or French based on the user’s computer language setting
//  Note: Some operating systems require a reboot when changing the language settings.
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Nat", Locale.getDefault());


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
        resourceBundle = ResourceBundle.getBundle("Nat", Locale.getDefault());

        //      -displays the log-in form in English or French based on the user’s computer language setting to translate all the text, labels, buttons, and errors on the form
        //      -determines the user’s location (i.e., ZoneId) and displays it in a label on the log-in form

        loginBtn.setText(resourceBundle.getString("Log") + " " + resourceBundle.getString("in"));
        usernameLbl.setText(resourceBundle.getString("Username"));
        passwordLbl.setText(resourceBundle.getString("Password"));
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


