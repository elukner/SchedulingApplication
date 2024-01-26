package controller;

/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */
import helper.DateTimeProcessing;
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

    /**
     * Handles the action when the "Log In" button is clicked.
     * Increments the timesClicked counter, validates the entered login credentials, and navigates to the main menu view
     * if the login is successful. Sets the login success status and updates the user login information in a log file.
     *
     * @param event The ActionEvent triggered by clicking the "Log In" button.
     * @throws IOException If an error occurs during the loading of the main menu view.
     */
    @FXML
    void onActionLogInBtn(ActionEvent event) throws IOException {
        setTimesClicked(timesClicked + 1);

        // Accepts username and password and provides an appropriate error message
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

    /**
     * Gets the login success status.
     *
     * @return True if login was successful, false otherwise.
     */
    public Boolean getLoginSuccess() {

        return loginSuccess;
    }

    /**
     * Sets the login success status.
     *
     * @param loginSuccess True if login was successful, false otherwise.
     */
    public void setLoginSuccess(Boolean loginSuccess) {

        this.loginSuccess = loginSuccess;
    }

    /**
     * Gets the number of times the "Log In" button has been clicked.
     *
     * @return The number of times the "Log In" button has been clicked.
     */
    public int getTimesClicked() {
        return timesClicked;
    }

    /**
     * Sets the number of times the "Log In" button has been clicked.
     *
     * @param timesClicked The number of times the "Log In" button has been clicked.
     */
    public void setTimesClicked(int timesClicked) {

        this.timesClicked = timesClicked;
    }

    /**
     * Writes user login information to a log file, including the username, times clicked, current date, timestamp, and login status.
     *
     * @throws IOException If an error occurs during file writing.
     */
    private void setUserLoggedIn() throws IOException {

        ObservableList<Users> userList = UsersDaoImpl.getUser(usernameTxt.getText(), passwordTxt.getText());

        String status = "fail";
        if (!userList.isEmpty()) {
            user = userList.get(0);
            status = getLoginSuccess() ? "success" : "fail";
        }

        FileIOManager.writeToFile(getUserLoggedIn(), getTimesClicked(), LocalDate.now().toString(),
                DateTimeProcessing.createTimeStamp(), status);
    }

    /**
     * Gets the username of the user currently logged in.
     * If the username field is empty, returns "null".
     *
     * @return The username of the user currently logged in or "null" if the username field is empty.
     */
    public String getUserLoggedIn() {
        if (usernameTxt.getText().isEmpty())
            return "null";

        return usernameTxt.getText();
    }

    /**
     * Validates the login credentials provided by the user.
     * Checks for empty fields, incorrect username, and incorrect password.
     * Displays appropriate error messages if validation fails.
     *
     * @param username The entered username.
     * @param password The entered password.
     * @return True if the login is valid, false otherwise.
     */
    public Boolean validateLogin(String username, String password) {

        // Check for empty fields
        Pair<String, Boolean> emptyFieldResult = isEmptyField(usernameTxt, passwordTxt);

        if (emptyFieldResult.getValue()) {
            showAlert(Alert.AlertType.ERROR, "Form", emptyFieldResult.getKey());

            return false;
        }

        // Retrieve user list with the provided username and password
        ObservableList<Users> userList = UsersDaoImpl.getUser(username, password);

        // Check if the user list is empty (no matching user found)
        if (userList.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form", "cUserPassword");
            return false;
        }

        Users user = userList.get(0);

        // Check if the provided password matches the user's password
        if (!password.equals(user.getPassword())) {
            showAlert(Alert.AlertType.ERROR, "Form", "cPassword");
            return false;
        }

        // Check if the provided username matches the user's username (case-insensitive)
        if (!username.equalsIgnoreCase(user.getUserName())) {
            showAlert(Alert.AlertType.ERROR, "Form", "cUsername");
            return false;
        }

        // Validation passed, login is valid
        return true;
    }

    /**
     * Checks if the provided text fields are empty.
     *
     * @param usernameTxt The username TextField.
     * @param passwordTxt The password TextField.
     * @return A Pair containing a validation message and a boolean indicating if any field is empty.
     */
    private Pair<String, Boolean> isEmptyField(TextField usernameTxt, TextField passwordTxt) {
        boolean isUsernameEmpty = usernameTxt.getText().isEmpty();
        boolean isPasswordEmpty = passwordTxt.getText().isEmpty();

        // Both fields are empty
        if (isUsernameEmpty && isPasswordEmpty) {
            return new Pair<>("eUsernamePassword", true);
        }

        // Username field is empty
        if (isUsernameEmpty) {
            return new Pair<>("eUsername", true);
        }

        // Password field is empty
        if (isPasswordEmpty) {
            return new Pair<>("ePassword", true);
        }

        // No empty fields
        return new Pair<>("No empty fields", false);
    }
    /**
     * Displays an alert with the specified type, title, and message, taking into account the user's computer language setting.
     * The alert's title and content are automatically translated based on the user's language preference.
     * Note: Some operating systems may require a reboot when changing the language settings.
     *
     * @param alertType The type of the alert (e.g., INFORMATION, WARNING, ERROR).
     * @param title     The title of the alert.
     * @param message   The message to be displayed in the alert.
     */
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Nat", Locale.getDefault());

        Alert alert = new Alert(alertType);
        alert.setTitle(resourceBundle.getString(title));
        alert.setHeaderText(null);
        alert.setContentText(resourceBundle.getString(message));
        alert.show();
    }

    /**
     * Starts the JavaFX application, loading and displaying the login form.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs during the loading of the login form view.
     */
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Initializes the LoginController.
     * This method is called automatically after the FXML file is loaded.
     * It sets up the login form in English or French based on the user's computer language setting.
     * Additionally, it determines the user's location (ZoneId) and displays it in a label on the login form.
     *
     * @param url            The location used to resolve relative paths for the root object or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle = ResourceBundle.getBundle("Nat", Locale.getDefault());

        // Displays the log-in form in English or French based on the user's computer language setting
        // Translates all the text, labels, buttons, and errors on the form
        loginBtn.setText(resourceBundle.getString("Log") + " " + resourceBundle.getString("in"));
        usernameLbl.setText(resourceBundle.getString("Username"));
        passwordLbl.setText(resourceBundle.getString("Password"));
        userLocationIDLbl.setText(ZoneId.systemDefault().toString());

    }
}


