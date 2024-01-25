package controller;

import dao.*;
import helper.FileIOManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.*;


public class ReportsController extends Application implements Initializable {

    @FXML // fx:id="contactComboBox"
    private ComboBox<String> contactComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="report1TableView"
    private TableView<AppointmentReport> report1TableView; // Value injected by FXMLLoader

    @FXML // fx:id="monthCol"
    private TableColumn<AppointmentReport, String> monthCol; // Value injected by FXMLLoader

    @FXML // fx:id="totalAppointmentsCol"
    private TableColumn<AppointmentReport, Integer> totalAppointmentsCol; // Value injected by FXMLLoader

    @FXML // fx:id="typeCol"
    private TableColumn<AppointmentReport, String> typeCol; // Value injected by FXMLLoader


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
     * TODO comment
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionRunReport2(ActionEvent event) {
        loadReport2();

    }

    @FXML
    void onSelectContact(ActionEvent event) {
        if (!ContactsDaoImpl.getContact(contactComboBox.getValue()).isEmpty()) {
            contactsModel = ContactsDaoImpl.getContact(contactComboBox.getValue()).get(0);
        }

    }

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
     * Loads data for the second report into the specified TableView.
     * The method populates the TableView with contact schedule data retrieved from the ContactScheduleReportDaoImpl,
     * specifically fetching contact schedules for the second report.
     * <p>
     * It initializes the columns in the report2TableView, setting up the necessary cell value factories for each column.
     * The columns include 'Contact ID', 'Contact Name', 'Appointment ID', 'Title', 'Type', 'Description',
     * 'Start', 'End', and 'Customer ID'. The data for each column is obtained from the ContactSchedule data model.
     * <p>
     * Note: Ensure that the report2TableView, contactIDCol, contactNameCol, appointmentIDCol, titleCol,
     * typeContactAppointmentCol, descriptionCol, startCol, endCol, and customerIDCol are initialized
     * before calling this method to avoid NullPointerException.
     */
    private void loadReport2() {
        // Load data into TableView
        report2TableView.setItems(FXCollections.observableArrayList(ContactScheduleReportDaoImpl.getContactSchedules(contactsModel.getContactID())));
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        populateContactComboBox();
        loadReport1();
        try {
            loadReport3();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    /**
     * Stops the JavaFX application by deleting the current file using FileIOManager.
     *
     * @throws Exception If an exception occurs during the application shutdown process.
     *
     * @implNote This method calls FileIOManager.deleteCurrentFile() to perform cleanup or
     * necessary actions before stopping the application.
     */
    @Override
    public void stop() throws Exception {
        FileIOManager.deleteCurrentFile();

    }
}
