package controller;
/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */
import dao.*;
import helper.FileIOManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.*;

/**
 * The ReportsController class serves as the controller for the reports view in the application.
 * It extends the Application class and implements the Initializable interface to handle JavaFX initialization.
 * This controller manages the logic for displaying various reports related to user appointments.
 */
public class ReportsController extends Application implements Initializable {

    @FXML // fx:id="report1TableView"
    private TableView<AppointmentReport> report1TableView; // Value injected by FXMLLoader
    @FXML // fx:id="monthCol"
    private TableColumn<AppointmentReport, String> monthCol; // Value injected by FXMLLoader
    @FXML // fx:id="totalAppointmentsCol"
    private TableColumn<AppointmentReport, Integer> totalAppointmentsCol; // Value injected by FXMLLoader
    @FXML // fx:id="typeCol"
    private TableColumn<AppointmentReport, String> typeCol; // Value injected by FXMLLoader


    @FXML // fx:id="contactComboBox"
    private ComboBox<String> contactComboBox; // Value injected by FXMLLoader
    @FXML // fx:id="noAppointmentsLabel"
    private Label noAppointmentsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="report2TableView"
    private TableView<ContactScheduleReport> report2TableView; // Value injected by FXMLLoader
    @FXML // fx:id="appointmentIDCol"
    private TableColumn<ContactScheduleReport, Integer> appointmentIDCol; // Value injected by FXMLLoader
    @FXML // fx:id="contactIDCol"
    private TableColumn<ContactScheduleReport, Integer> contactIDCol; // Value injected by FXMLLoader
    @FXML // fx:id="contactNameCol"
    private TableColumn<ContactScheduleReport, String> contactNameCol; // Value injected by FXMLLoader
    @FXML // fx:id="customerIDCol"
    private TableColumn<ContactScheduleReport, Integer> customerIDCol; // Value injected by FXMLLoader
    @FXML // fx:id="descriptionCol"
    private TableColumn<ContactScheduleReport, String> descriptionCol; // Value injected by FXMLLoader
    @FXML // fx:id="endCol"
    private TableColumn<ContactScheduleReport, String> endCol; // Value injected by FXMLLoader
    @FXML // fx:id="startCol"
    private TableColumn<ContactScheduleReport, String> startCol; // Value injected by FXMLLoader
    @FXML // fx:id="titleCol"
    private TableColumn<ContactScheduleReport, String> titleCol; // Value injected by FXMLLoader
    @FXML // fx:id="typeCol"
    private TableColumn<ContactScheduleReport, String> typeContactAppointmentCol; // Value injected by FXMLLoader


    @FXML // fx:id="report3TableView"
    private TableView<UserAppointmentReport> report3TableView; // Value injected by FXMLLoader
    @FXML // fx:id="userIDCol"
    private TableColumn<UserAppointmentReport, Integer> userIDCol; // Value injected by FXMLLoader
    @FXML // fx:id="userNameCol"
    private TableColumn<UserAppointmentReport, String> userNameCol; // Value injected by FXMLLoader
    @FXML // fx:id="userLogInDateTimeCol"
    private TableColumn<UserAppointmentReport, String> userLogInDateTimeCol; // Value injected by FXMLLoader
    @FXML // fx:id="userTotalAppointmentsCol"
    private TableColumn<UserAppointmentReport, Integer> userTotalAppointmentsCol; // Value injected by FXMLLoader
    @FXML // fx:id="userAverageDurationCol"
    private TableColumn<UserAppointmentReport, Double> userAverageDurationCol; // Value injected by FXMLLoader

    private Stage stage;
    private Parent scene;

    private Contacts contactsModel;

    /**
     * Handles the action event when navigating back to the main menu.
     *
     * @param event The ActionEvent triggered by the back action.
     *
     * @throws IOException If an I/O exception occurs during the navigation process.
     */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Executes the action triggered when the user clicks the "Run Report 2" button.
     * This method is associated with the corresponding FXML event handler and is responsible for loading and displaying
     * the second type of report in the application.
     *
     * @param event The ActionEvent representing the button click.
     */
    @FXML
    void onActionRunReport2(ActionEvent event) {
        loadReport2();

    }

    /**
     * Executes the action triggered when a contact is selected from the contactComboBox.
     * If the selected contact exists in the database, the method sets the contactsModel with the selected contact's details.
     *
     * @param event The ActionEvent representing the contact selection.
     */
    @FXML
    void onSelectContact(ActionEvent event) {
        if (!ContactsDaoImpl.getContact(contactComboBox.getValue()).isEmpty()) {
            contactsModel = ContactsDaoImpl.getContact(contactComboBox.getValue()).get(0);
        }

    }

    /**
     * Populates the contactComboBox with contact names retrieved from the database.
     * This method is called during the initialization to provide contact selection options in the user interface.
     */
    private void populateContactComboBox() {
        if (!ContactsDaoImpl.getAllContacts().isEmpty()) {
            for (Contacts contact : ContactsDaoImpl.getAllContacts()) {

                contactComboBox.getItems().add(contact.getContactName());
            }
        }
    }


    /**
     * Loads the data for the first report into the specified TableView.
     * The method populates the TableView with appointment data retrieved from the AppointmentReportDaoImpl,
     * displaying the total number of customer appointments categorized by type and month.
     * <p>
     * It sets up the necessary cell value factories for the 'Month', 'Type', and 'Total Appointments' columns
     * in the report1TableView. The 'Month' and 'Type' columns are populated directly from the data,
     * while the 'Total Appointments' column is calculated using the getTotalAppointments() method of the data model.
     * <p>
     * Note: Ensure that the report1TableView, monthCol, typeCol, and totalAppointmentsCol are initialized
     * before calling this method to avoid NullPointerException.
     */
    private void loadReport1() {
        // Load data into TableView
        report1TableView.setItems(FXCollections.observableArrayList(AppointmentReportDaoImpl.getAppointmentsByTypeAndMonth()));
        // Initialize columns
        monthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        totalAppointmentsCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getTotalAppointments().asObject());

    }

    /**
     * Loads and displays the second type of report in the application, showing contact schedules.
     * Retrieves contact schedules for the specified contact ID and updates the TableView with the data.
     * If the list of contact schedules is empty, the method hides the TableView and shows a label indicating
     * that there are no appointments for the selected contact.
     *
     * Lambda function justification 1: These lambda functions are used here to tell each column which function to call to update its value.
     */
    private void loadReport2() {

        ObservableList<ContactScheduleReport> contactSchedulesList = ContactScheduleReportDaoImpl.getContactSchedules(contactsModel.getContactID());

        if(contactSchedulesList.isEmpty()){
            // If the list is empty, show the label and hide the table
            hideReport2TableView();
        }else{
            // If there are appointments, show the table and hide the label
            showReport2TableView();
            // Load data into TableView
            report2TableView.setItems(contactSchedulesList);
            // Initialize columns
            contactIDCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getContactID().asObject());
            contactNameCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getContactName());
            appointmentIDCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getAppointmentID().asObject());
            titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
            typeContactAppointmentCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerIDCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCustomerID().asObject());

        }

    }

    /**
     * Displays the TableView for the second type of report while hiding the label indicating no appointments.
     * This method is called when there are appointments to be displayed.
     */
    private void showReport2TableView() {

        noAppointmentsLabel.setVisible(false);
        report2TableView.setVisible(true);

    }

    /**
     * Hides the TableView for the second type of report and shows a label indicating no appointments.
     * This method is called when there are no appointments to be displayed.
     */
    private void hideReport2TableView(){
        noAppointmentsLabel.setVisible(true);
        report2TableView.setVisible(false);
    }


    /**
     * Loads and displays the third type of report in the application, which includes information about a user's appointments
     * with additional user date and timestamp details.
     *
     * @throws FileNotFoundException If the file "login_activity.txt" is not found during the process of retrieving user information.
     */
    private void loadReport3() throws FileNotFoundException {
        // Retrieve user information from the "login_activity.txt" file
        Users user = UsersDaoImpl.getUser(FileIOManager.readFile()).get(0);

        // Fetch user appointment summary with user date and timestamp
        ObservableList<UserAppointmentReport> userAppointmentReports = UserAppointmentReportDaoImpl.getUserAppointmentSummaryWithUserDateTimestamp(user.getUserID());

        // Set the items in the TableView with the fetched user appointment reports
        report3TableView.setItems(FXCollections.observableArrayList(userAppointmentReports));

        // Configure cell value factories for each column in the TableView
        userIDCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getUserID().asObject());
        userNameCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getUserName());
        userLogInDateTimeCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getUserLogInDateTime());
        userTotalAppointmentsCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getTotalAppointments().asObject());
        userAverageDurationCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getAverageDuration().asObject());
    }

    /**
     * Initializes the ReportsController, called automatically after the FXML file is loaded.
     * It loads and displays the first report, shows the TableView for the second report, populates the contact ComboBox,
     * and loads and displays the third report. In case of an exception during the third report loading,
     * a stack trace is printed.
     *
     * @param url            The location used to resolve relative paths for the root object or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        loadReport1();

        showReport2TableView();
        populateContactComboBox();

        try {
            loadReport3();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Starts the JavaFX application, loading and displaying the main stage with the reports view.
     *
     * @param stage The primary stage for the application.
     * @throws Exception If an error occurs during the loading of the FXML file.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/reports.fxml"));
        stage.setTitle("Scheduling Desktop Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Stops the JavaFX application by deleting the current file using FileIOManager.
     *
     * @throws Exception If an exception occurs during the application shutdown process.
     */
    @Override
    public void stop() throws Exception {
        FileIOManager.deleteCurrentFile();

    }
}
