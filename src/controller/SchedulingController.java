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
import helper.DateProcessing;
import helper.DateTimeProcessing;
import helper.FileIOManager;
import helper.TimeProcessing;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Appointments;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.logging.Level;

import javafx.application.Application;
import model.Contacts;
import model.Customers;
import model.Users;


/**
 * Controller class for the scheduling functionality of the application.
 * Manages interactions with the scheduling.fxml view, handling user actions and updating the display.
 * Implements Initializable for initialization tasks and Application for JavaFX application lifecycle methods.
 */
public class SchedulingController extends Application implements Initializable {

    /**
     * Represents a JavaFX TableColumn for displaying appointment IDs in the user interface.
     */
    @FXML // fx:id="appointmentIDCol"
    private TableColumn<Appointments, Integer> appointmentIDCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TextField for entering appointment IDs in the user interface.
     */
    @FXML // fx:id="appointmentIDTxt"
    private TextField appointmentIDTxt; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableView for displaying appointments in the user interface.
     */
    @FXML // fx:id="appointmentTblView"
    private TableView<Appointments> appointmentTblView; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying contacts in the user interface.
     */
    @FXML // fx:id="contactCol"
    private TableColumn<?, ?> contactCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX ComboBox for selecting contact names in the user interface.
     */
    @FXML // fx:id="contactNameComboBox"
    private ComboBox<String> contactNameComboBox; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX ComboBox for selecting end times in the user interface.
     */
    @FXML // fx:id="timeComboBox"
    private ComboBox<String> endTimeComboBox; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX DatePicker for selecting end dates in the user interface.
     */
    @FXML // fx:id="datePicker"
    private DatePicker endDatePicker; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX DatePicker for selecting start dates in the user interface.
     */
    @FXML // fx:id="startDatePicker"
    private DatePicker startDatePicker; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX ComboBox for selecting start times in the user interface.
     */
    @FXML // fx:id="startTimeComboBox"
    private ComboBox<String> startTimeComboBox; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying customer IDs in the user interface.
     */
    @FXML // fx:id="customerIDCol"
    private TableColumn<?, ?> customerIDCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX ComboBox for selecting customer IDs in the user interface.
     */
    @FXML // fx:id="customerIDComboBox"
    private ComboBox<String> customerIDComboBox; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying descriptions in the user interface.
     */
    @FXML // fx:id="descriptionCol"
    private TableColumn<?, ?> descriptionCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TextField for entering descriptions in the user interface.
     */
    @FXML // fx:id="descriptionTxt"
    private TextField descriptionTxt; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying end date and time in the user interface.
     */
    @FXML // fx:id="endDateTimeCol"
    private TableColumn<Appointments, String> endDateTimeCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX RadioButton for filtering appointments by month in the user interface.
     */
    @FXML // fx:id="filterAppointmentMonthRBtn"
    private RadioButton filterAppointmentMonthRBtn; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX RadioButton for filtering appointments by week in the user interface.
     */
    @FXML // fx:id="filterAppointmentWeekRBtn"
    private RadioButton filterAppointmentWeekRBtn; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX RadioButton for displaying all appointments in the user interface.
     */
    @FXML // fx:id="filterAllAppointmentRBtn"
    private RadioButton filterAllAppointmentRBtn; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying locations in the user interface.
     */
    @FXML // fx:id="locationCol"
    private TableColumn<?, ?> locationCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TextField for entering locations in the user interface.
     */
    @FXML // fx:id="locationTxt"
    private TextField locationTxt; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying start date and time in the user interface.
     */
    @FXML // fx:id="startDateTimeCol"
    private TableColumn<Appointments, String> startDateTimeCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying titles in the user interface.
     */
    @FXML // fx:id="titleCol"
    private TableColumn<Appointments, String> titleCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TextField for entering titles in the user interface.
     */
    @FXML // fx:id="titleTxt"
    private TextField titleTxt; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying appointment types in the user interface.
     */
    @FXML // fx:id="typeCol"
    private TableColumn<?, ?> typeCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TextField for entering appointment types in the user interface.
     */
    @FXML // fx:id="typeTxt"
    private TextField typeTxt; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for displaying user IDs in the user interface.
     */
    @FXML // fx:id="userIDCol"
    private TableColumn<?, ?> userIDCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX ComboBox for selecting user IDs in the user interface.
     */
    @FXML // fx:id="userIDComboBox"
    private ComboBox<String> userIDComboBox; // Value injected by FXMLLoader

    /**
     * Represents the model class for managing data related to contacts in the application.
     */
    private Contacts contactsModel;

    /**
     * Represents the model class for managing data related to appointments in the application.
     */
    private Appointments appointmentsModel;

    /**
     * Represents the model class for managing data related to users in the application.
     */
    private Users usersModel;

    /**
     * Represents the model class for managing data related to customers in the application.
     */
    private Customers customersModel;

    /**
     * Represents the selected appointment in the user interface.
     */
    private static Appointments selectedAppointments;

    /**
     * Represents the list of appointments in the user interface.
     */
    private static ObservableList<Appointments> appointmentsList;

    /**
     * Represents the selected end time in the user interface.
     */
    private String endTimeSelected;

    /**
     * Represents the selected end date in the user interface.
     */
    private String endDateSelected;

    /**
     * Represents the selected start time in the user interface.
     */
    private String startTimeSelected;

    /**
     * Represents the selected start date in the user interface.
     */
    private String startDateSelected;

    /**
     * Represents the selected customer ID in the user interface.
     */
    private String customerIDSelected;
    /**
     * Represents the selected user ID in the user interface.
     */
    private String userIDSelected;

    /**
     * Flag indicating whether there has been a change to the start date picker.
     * This boolean is used to track modifications in the user interface.
     */
    boolean startDatePickerChanged = false;

    /**
     * Flag indicating whether there has been a change to the end date picker.
     * This boolean is used to track modifications in the user interface.
     */
    boolean endDatePickerChanged = false;

    /**
     * Flag indicating whether there has been a change to the start time.
     * This boolean is used to track modifications in the user interface.
     */
    boolean startTimeChanged = false;

    /**
     * Flag indicating whether there has been a change to the end time.
     * This boolean is used to track modifications in the user interface.
     */
    boolean endTimeChanged = false;



    /**
     * Resets the user interface by performing the following actions:
     * 1. Clears the selection and form fields.
     * 2. Sets the appointment ID text field to an empty string and disables it.
     * 3. Populates the start and end date/time combo box and date picker with default values.
     * 4. Populates the contact name combo box with available contact names.
     * 5. Populates the customer ID combo box with customer IDs.
     * 6. Populates the user ID combo box with user IDs.
     * <p>
     * This method is intended to be used for resetting the UI state, making it ready for
     * the input of new appointment details or modification of existing ones.
     *
     * @param event The triggering event that calls for the UI reset.
     */
    @FXML
    void onActionAppointment(ActionEvent event) {
        clearSelectionAndFormFields();

        appointmentIDTxt.setText(" ");
        appointmentIDTxt.setDisable(true);


        //populating start and end date/time combo box and date picker with default values
        populateStartAndEndDateTime(null, null, null, null);


        // A contact name is assigned to an appointment using a drop-down menu or combo box.
        populateContactNameComboBox();

        populateCustomerIDComboBox();

        populateUserIDComboBox();


    }

    /**
     * Populates the contact name combo box with available contact names retrieved from the database.
     *
     * This method iterates through the list of contacts obtained from ContactsDaoImpl and adds each
     * contact's name to the contactNameComboBox if the list is not empty.
     *
     */
    private void populateContactNameComboBox() {
        if (!ContactsDaoImpl.getAllContacts().isEmpty()) {
            for (Contacts contact : ContactsDaoImpl.getAllContacts()) {

                contactNameComboBox.getItems().add(contact.getContactName());
            }
        }
    }


    /**
     * Populates the customer ID combo box with available customer IDs retrieved from the database.
     *
     * This method iterates through the list of customers obtained from CustomersDaoImpl and adds each
     * customer's ID (converted to a string) to the customerIDComboBox if the list is not empty.     *
     */
    private void populateCustomerIDComboBox() {
        if (!CustomersDaoImpl.getAllCustomers().isEmpty()) {
            for (Customers customer : CustomersDaoImpl.getAllCustomers()) {

                customerIDComboBox.getItems().add(Integer.toString(customer.getCustomerID()));

            }
        }
    }

    /**
     * Populates the user ID ComboBox with the user IDs retrieved from the UsersDaoImpl.
     * If the list of users is not empty, iterates through each user and adds their user ID
     * as a string to the ComboBox.     *
     */
    private void populateUserIDComboBox() {
        if (!UsersDaoImpl.getAllUsers().isEmpty()) {
            for (Users user : UsersDaoImpl.getAllUsers()) {

                userIDComboBox.getItems().add(Integer.toString(user.getUserID()));

            }
        }
    }


    /**
     * Creates and initializes an Appointments object based on user input and system data.
     * <p>
     * This method performs the following steps:
     * 1. Initializes a DateTimeFormatter with the pattern "yyyy-MM-dd HH:mm:ss".
     * 2. Retrieves user information from the UsersDaoImpl class using FileIOManager.
     * 3. Creates an AppointmentsModel object with the provided input and system-generated values.
     * 4. Checks if the created appointment is valid using the isValidAppointment() method.
     * 5. If the appointment is valid, adds the customer to the database and clears form fields.
     * 6. Displays the updated scheduling table view using showSchedulingTableView().
     *
     * @param event The triggering event that initiates the addition of the appointment.
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname has failed.
     * @throws SQLException An exception that provides information on a database access error or other errors that may
     *                      occur during the appointment addition process.
     */
    @FXML
    void onActionAdd(ActionEvent event) throws FileNotFoundException, SQLException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Users user = UsersDaoImpl.getUser(FileIOManager.readFile()).get(0);


        appointmentsModel = new Appointments((new ReadOnlyStringWrapper(titleTxt.getText())),
                descriptionTxt.getText(), locationTxt.getText(), typeTxt.getText(), LocalDateTime.parse(getStartDateTimeSelected(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse(getEndDateTimeSelected(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), dateTimeFormatter.format(LocalDateTime.now()), user.getUserName(),
                dateTimeFormatter.format(LocalDateTime.now()), user.getUserName(), Integer.parseInt(customerIDSelected),
                Integer.parseInt(userIDSelected), contactsModel.getContactID());

//        System.out.println(appointmentsModel.getTitle() +
//                appointmentsModel.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
//                appointmentsModel.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (isValidAppointment()) {

            addCustomerDatabase();
            clearSelectionAndFormFields();
        }

        showSchedulingTableView();

    }

    /**
     * Checks the validity of the appointment based on business hours and overlapping appointments.
     *
     * @return {@code true} if the appointment is valid, {@code false} otherwise.
     *
     * @throws SQLException If a SQL exception occurs during the validation process.
     *
     */
    private boolean isValidAppointment() throws SQLException {
        if (DateTimeProcessing.isOutsideBusinessHours(appointmentsModel.getStart().toLocalDate(),
                appointmentsModel.getStart().toLocalTime()) ||
                DateTimeProcessing.isOutsideBusinessHours(appointmentsModel.getEnd().toLocalDate(),
                       appointmentsModel.getEnd().toLocalTime())) {

            //display a custom message specific for each error check in the user interface
            setCustomMessage(Alert.AlertType.ERROR, "Appointment outside of business hours",
                    "Please schedule an appointment inside of business hours defined as 8:00 a.m. to 10:00 p.m. ET" +
                            "and Monday through Friday");
            //scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
            return false;
        }
        if(startDatePickerChanged ||endDatePickerChanged || startTimeChanged || endTimeChanged) {
            if (AppointmentsDaoImpl.hasOverlappingAppointments(appointmentsModel.getCustomerID(),
                    DateTimeProcessing.convertLocalToUTC(appointmentsModel.getStart(), ZoneId.systemDefault()) ,
                    DateTimeProcessing.convertLocalToUTC(appointmentsModel.getEnd(),ZoneId.systemDefault()),appointmentsModel.getAppointmentID())) {
                //display a custom message specific for each error check in the user interface
                setCustomMessage(Alert.AlertType.ERROR, "Scheduling Overlap", "Please schedule a non-overlapping " +
                        "appointment for customer");
                //scheduling overlapping appointments for customers
                return false;
            }
        }else{
            if (AppointmentsDaoImpl.hasOverlappingAppointments(appointmentsModel.getCustomerID(),
                    DateTimeProcessing.convertLocalToUTC(appointmentsModel.getStart(), ZoneId.systemDefault()) ,
                    DateTimeProcessing.convertLocalToUTC(appointmentsModel.getEnd(),ZoneId.systemDefault()))) {
                //display a custom message specific for each error check in the user interface
                setCustomMessage(Alert.AlertType.ERROR, "Scheduling Overlap", "Please schedule a non-overlapping " +
                        "appointment for customer");
                //scheduling overlapping appointments for customers
                return false;
            }
        }

//        if(!DateTimeProcessing.isValidAppointmentEndDateTime(appointmentsModel.getStart(),
//                appointmentsModel.getEnd())){
//            //The application does not allow entering appointments with a start time after the end time.
//            setCustomMessage(Alert.AlertType.ERROR, "Start Date and Time After The Date and Time Date", "The application " +
//                    "does not allow entering appointments with a start date and time after the end date and time.");
//            return false;
//
//        }
        if(!TimeProcessing.isValidAppointmentEndTime(appointmentsModel.getStart().toLocalTime(),
                appointmentsModel.getEnd().toLocalTime())){
            //The application does not allow entering appointments with a start time after the end time.
            setCustomMessage(Alert.AlertType.ERROR, "Start Time After The End Time", "The application " +
                    "does not allow entering appointments with a start time after the end time.");
            return false;

        }
        if(!DateProcessing.isValidAppointmentEndDate(appointmentsModel.getStart().toLocalDate(),
                appointmentsModel.getEnd().toLocalDate())){
            //The application does not allow entering appointments with a start time after the end time.
            setCustomMessage(Alert.AlertType.ERROR, "Start Date After The End Date", "The application " +
                    "does not allow entering appointments with a start date after the end date.");
            return false;

        }


        return true;
    }

    /**
     * Retrieves the selected start date and time as a formatted string.
     *
     * @return A string representing the start date and time, formatted as "yyyy-MM-dd HH:mm".
     *
     */
    private String getStartDateTimeSelected() {
        if (startDateSelected != null && startTimeSelected != null) {
            return startDateSelected + " " + startTimeSelected;

        }

        return startDatePicker.getEditor().getText() + " " + startTimeComboBox.getValue();
    }

    /**
     * Retrieves the selected end date and time as a formatted string.
     *
     * @return A string representing the end date and time, formatted as "yyyy-MM-dd HH:mm:ss".
     */
    private String getEndDateTimeSelected() {
        if (endDateSelected != null && endTimeSelected != null) {
            return endDateSelected + " " + endTimeSelected;
        }

        return endDatePicker.getEditor().getText() + " " + endTimeComboBox.getValue();

    }

    /**
     * Adds a new customer record to the database using the provided appointment model data.
     *
     * @throws SQLException If a SQL exception occurs during the database insertion.
     */
    private void addCustomerDatabase() throws SQLException {
        AppointmentsDaoImpl.resetAutoIncrement();

//        System.out.println("addCustomerDatabase() "+appointmentsModel.getTitle() +
//                appointmentsModel.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " "+
//                appointmentsModel.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        AppointmentsDaoImpl.insertAppointments(appointmentsModel.getTitle(),
                appointmentsModel.getDescription(), appointmentsModel.getLocation(),
                appointmentsModel.getType(), appointmentsModel.getStart() ,
                appointmentsModel.getEnd(),
                appointmentsModel.getCreatedBy(), appointmentsModel.getLastUpdatedBy(),
                appointmentsModel.getCustomerID(), appointmentsModel.getUserID(), appointmentsModel.getContactID());
    }

    /**
     * Handles the action event when updating an appointment.
     *
     * @param actionEvent The ActionEvent triggered by the update action.
     *
     * @throws SQLException If a SQL exception occurs during the update process.
     * @throws FileNotFoundException If a file is not found during the update process.
     *
     */
    @FXML
    void onActionUpdate(ActionEvent actionEvent) throws SQLException, FileNotFoundException {

        Users user = UsersDaoImpl.getUser(FileIOManager.readFile()).get(0);


        //When adding and updating an appointment, record the following data: Appointment_ID, title, description, location,
        //  contact, type, start date and time, end date and time, Customer_ID, and User_ID.

            appointmentsModel = appointmentTblView.getSelectionModel().getSelectedItem();
            appointmentsModel.setAppointmentID(Integer.parseInt(appointmentIDTxt.getText()));
            appointmentsModel.setTitle(new ReadOnlyStringWrapper(titleTxt.getText()));
            appointmentsModel.setDescription(descriptionTxt.getText());
            appointmentsModel.setLocation(locationTxt.getText());
            appointmentsModel.setType(typeTxt.getText());
            appointmentsModel.setStart(LocalDateTime.parse(getStartDateTimeSelected(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            appointmentsModel.setEnd(LocalDateTime.parse(getEndDateTimeSelected(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            appointmentsModel.setLastUpdate(DateTimeProcessing.getCurrentLocalDateTimeString());
            appointmentsModel.setLastUpdatedBy(user.getUserName());
            appointmentsModel.setCustomerID(Integer.parseInt(customerIDSelected));
            appointmentsModel.setUserID(Integer.parseInt(userIDSelected));
            appointmentsModel.setContactID(contactsModel.getContactID());

        if(startDatePickerChanged ||endDatePickerChanged || startTimeChanged || endTimeChanged){
            if (isValidAppointment()) {
                updateCustomerDatabase();
                clearSelectionAndFormFields();
                showSchedulingTableView();
            }
        }else{
            updateCustomerDatabase();
            clearSelectionAndFormFields();
            showSchedulingTableView();

        }

    }

    /**
     * Updates an existing customer record in the database with the provided appointment model data.
     *
     * @throws SQLException If a SQL exception occurs during the database update.
     */
    private void updateCustomerDatabase() throws SQLException {
        AppointmentsDaoImpl.updateAppointment(appointmentsModel.getAppointmentID(),
                appointmentsModel.getTitle(), appointmentsModel.getDescription(), appointmentsModel.getLocation(),
                appointmentsModel.getType(), appointmentsModel.getStart(), appointmentsModel.getEnd(),
                appointmentsModel.getLastUpdate(), appointmentsModel.getLastUpdatedBy(),
                appointmentsModel.getCustomerID(), appointmentsModel.getUserID(), appointmentsModel.getContactID());

    }

    /**
     * Handles the action event when deleting an appointment.
     *
     * @param event The ActionEvent triggered by the delete action.
     *
     * @throws SQLException If a SQL exception occurs during the deletion process.
     * @throws FileNotFoundException If a file is not found during the deletion process.
     */
    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, FileNotFoundException {
        // Retrieve the selected appointment
        appointmentsModel = appointmentTblView.getSelectionModel().getSelectedItem();

        // Show a cancellation message
        showCanceledMessage();

        // Update the appointment ID and delete from the database
        appointmentsModel.setAppointmentID(Integer.parseInt(appointmentIDTxt.getText()));
        deleteAppointmentDatabase();

        // Clear form fields and display the scheduling table view
        clearSelectionAndFormFields();
        showSchedulingTableView();
    }

    /**
     * Displays an information alert confirming the successful cancellation of an appointment.
     */
    private void showCanceledMessage() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Appointment Canceled");
        a.setHeaderText("Appointment Successfully Canceled");
        a.setContentText("Appointment ID: " + appointmentsModel.getAppointmentID() +
                "\nType of appointment: " + appointmentsModel.getType());

        a.show();
    }

    /**
     * Deletes an appointment record from the database using the provided appointment ID.
     *
     * @throws SQLException If a SQL exception occurs during the deletion process.
     */
    private void deleteAppointmentDatabase() throws SQLException {

        AppointmentsDaoImpl.deleteAppointment(appointmentsModel.getAppointmentID());
        AppointmentsDaoImpl.resetAutoIncrement();
    }


    /**
     * Handles the action event when filtering and displaying all appointments.
     *
     * @param event The ActionEvent triggered by the filter all appointments action.
     */
    @FXML
    void onActionFilterAllAppointment(ActionEvent event) {
        populateAllAppointments();
    }

    /**
     * Populates the TableView with all appointments from the database.
     */
    private void populateAllAppointments() {
        // Clear existing appointments
        appointmentsList.clear();

        try {
            // Fetch and add new all appointments to appointmentsList
            //appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointments());
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointmentsByLocalTimeZones());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Update TableView
        appointmentTblView.setItems(appointmentsList);
    }


    /**
     * Handles the action event when filtering and displaying appointments for the current month.
     *
     * @param actionEvent The ActionEvent triggered by the filter appointments for the current month action.
     */
    @FXML
    void onActionFilterAppointmentMonthRBtn(ActionEvent actionEvent) {
        populateAppointmentsByMonth();

    }

    /**
     * Populates the TableView with appointments for the current month from the database.
     */
    private void populateAppointmentsByMonth() {
        // Clear existing appointments
        appointmentsList.clear();
        appointmentsList = FXCollections.observableArrayList();

        try {
            // Fetch and add new month appointments to appointmentsList
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointmentsByMonth());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }


        // Update TableView
        appointmentTblView.setItems(appointmentsList);
    }

    /**
     * Handles the action event when filtering and displaying appointments for the current week.
     *
     * @param actionEvent The ActionEvent triggered by the filter appointments for the current week action.
     */
    @FXML
    void onActionFilterAppointmentWeekRBtn(ActionEvent actionEvent) {
        // Clear existing appointments
        appointmentsList.clear();

        try {
            // Fetch and add new week appointments to appointmentsList
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointmentsByWeek());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }


        // Update TableView
        appointmentTblView.setItems(appointmentsList);
    }

    /**
     * Displays the scheduling table view with appointments and necessary columns.
     * Also, it checks for upcoming appointments based on the current user's local time.
     *
     * @throws FileNotFoundException If an error occurs while reading user data.
     */
    @FXML
    void showSchedulingTableView() throws FileNotFoundException {

        // Check for upcoming appointments
        checkUpcomingAppointments(FileIOManager.readFileCurrentUserDateTime());

        // Initialize ObservableList for appointments
        appointmentsList = FXCollections.observableArrayList();

        // Set up TableView columns
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getTitle());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        endDateTimeCol.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));


        try {
            // Fetch and add all appointments to appointmentsList
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointmentsByLocalTimeZones());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Update TableView with appointments
        appointmentTblView.setItems(appointmentsList);


        // Disable Appointment_ID field throughout the application
        appointmentIDTxt.setDisable(true);

        if(contactNameComboBox.getItems().isEmpty()||startTimeComboBox.getItems().isEmpty()
        ||endTimeComboBox.getItems().isEmpty()||customerIDComboBox.getItems().isEmpty()
        ||userIDComboBox.getItems().isEmpty()){
            populateContactNameComboBox();
            populateStartAndEndDateTime(null,
                    null,
                    null,
                    null);

            // Populate Customer ID and User ID ComboBoxes
            populateCustomerIDComboBox();
            populateUserIDComboBox();
        }



    }

    /**
     * Handles the action event when selecting a customer ID from the ComboBox.
     *
     * @param event The ActionEvent triggered by the customer ID selection.
     */
    @FXML
    void onActionSelectCustomerID(ActionEvent event) {
        customerIDSelected = customerIDComboBox.getValue();
    }

    /**
     * Handles the action event when selecting a user ID from the ComboBox.
     *
     * @param event The ActionEvent triggered by the user ID selection.
     */
    @FXML
    void onActionSelectUserID(ActionEvent event) {

        userIDSelected = userIDComboBox.getValue();

    }

    /**
     * Handles the action event when selecting a start time from the ComboBox.
     *
     * @param event The ActionEvent triggered by the start time selection.
     */
    @FXML
    void onStartTimeSelected(ActionEvent event) {
        startTimeSelected = startTimeComboBox.getValue();
    }

    /**
     * Handles the action event when selecting an end time from the ComboBox.
     *
     * @param event The ActionEvent triggered by the end time selection.
     */
    @FXML
    void onEndTimeSelected(ActionEvent event) {
        endTimeSelected = endTimeComboBox.getValue();
    }

    /**
     * Handles the action event when selecting a start date from the DatePicker.
     *
     * @param event The ActionEvent triggered by the start date selection.
     */
    @FXML
    void onActionStartDate(ActionEvent event) {
        if(startDatePicker.getValue()!=null) {
            datePickerStringConverter(startDatePicker);
            startDateSelected = startDatePicker.getValue().toString();
        }

    }

    /**
     * Handles the action event when selecting an end date from the DatePicker.
     *
     * @param event The ActionEvent triggered by the end date selection.
     */
    @FXML
    void onActionEndDate(ActionEvent event) {
        if(endDatePicker.getValue()!=null){
            datePickerStringConverter(endDatePicker);
            endDateSelected = endDatePicker.getValue().toString();
        }
    }

    /**
     * Sets a StringConverter for the specified DatePicker to format and parse LocalDate objects.
     *
     * @param datePicker The DatePicker for which the StringConverter is set.
     */
    private void datePickerStringConverter(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString != null && !dateString.isEmpty()) {
                    return LocalDate.parse(dateString, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    /**
     * A contact name is assigned to an appointment using a drop-down menu or combo box.
     *
     * @param actionEvent The triggering event that occurs when a contact name is selected from the combo box.
     */
    @FXML
    void onActionContactNameComboBox(ActionEvent actionEvent) {

        if (!ContactsDaoImpl.getContact(contactNameComboBox.getValue()).isEmpty())
            contactsModel = ContactsDaoImpl.getContact(contactNameComboBox.getValue()).get(0);

    }

    /**
     * Displays a custom message using an Alert with the specified type, title, and content text.
     *
     * @param alertType The AlertType for the custom message (e.g., INFORMATION, ERROR).
     * @param title The title of the Alert.
     * @param customMessage The custom message to be displayed in the content text of the Alert.
     */
    void setCustomMessage(Alert.AlertType alertType, String title, String customMessage) {
        Alert a = new Alert(alertType);
        a.setTitle(title);
        a.setContentText(customMessage);
        a.showAndWait();
    }

    /**
     * Handles the action event when navigating back to the main menu.
     *
     * @param event The ActionEvent triggered by the back action.
     *
     * @throws IOException If an I/O exception occurs during the navigation process.
     */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Handles the action event when canceling the current operation.
     *
     * @param event The ActionEvent triggered by the cancel action.
     */
    @FXML
    void onActionCancel(ActionEvent event) {
        clearSelectionAndFormFields();

    }

    /**
     * Clears the selection and form fields in the appointment form.
     */
    private void clearSelectionAndFormFields() {
        appointmentTblView.getSelectionModel().clearSelection();
        appointmentIDTxt.setDisable(true);
        appointmentIDTxt.clear();
        titleTxt.clear();
        descriptionTxt.clear();
        locationTxt.clear();
        contactNameComboBox.getItems().clear();
        typeTxt.clear();
        startDatePicker.getEditor().clear();
        startDatePicker.setValue(null); //new
        startTimeComboBox.getItems().clear();
        endDatePicker.getEditor().clear();
        endDatePicker.setValue(null); //new
        endTimeComboBox.getItems().clear();
        customerIDComboBox.getItems().clear();
        userIDComboBox.getItems().clear();

        startDatePickerChanged = false;
        endDatePickerChanged = false;
        startTimeChanged =false;
        endTimeChanged=false;

    }

    /**
     * Sets up a TableView selection listener to update the form fields when a row is selected.
     * <p>
     * Lambda function justification 2: The lambda expression is used to define the behavior of the
     * selection listener for the Appointment TableView. It simplifies the process of reacting to changes in the selected
     * item within the TableView.
     */
    private void tbleViewSelectionListener() {

        appointmentTblView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null) {
                // Store the selected appointment
                selectedAppointments = appointmentTblView.getSelectionModel().getSelectedItem();

                // Disable and set Appointment_ID
                appointmentIDTxt.setDisable(true);
                appointmentIDTxt.setText(String.valueOf(selectedAppointments.getAppointmentID()));

                // Set other form fields with selected appointment data
                titleTxt.setText(selectedAppointments.getTitle().getValue());
                descriptionTxt.setText(selectedAppointments.getDescription());
                locationTxt.setText(selectedAppointments.getLocation());

                // Populate and update Contact Name ComboBox
                populateContactNameComboBoxWithValue();

                typeTxt.setText(selectedAppointments.getType());


                // Populate and update start and end date/time ComboBoxes
                populateStartAndEndDateTime(TimeProcessing.getCorrectTimeSeconds(selectedAppointments.getStart().toLocalTime()),
                        selectedAppointments.getStart().toLocalDate(),
                        TimeProcessing.getCorrectTimeSeconds(selectedAppointments.getEnd().toLocalTime()),
                        selectedAppointments.getEnd().toLocalDate());

                // Populate Customer ID and User ID ComboBoxes
                populateCustomerIDComboBoxWithValue();
                populateUserIDComboBoxWithValue();
            }
        });
    }

    /**
     * Populates the Contact Name ComboBox with values and sets the selected value.
     */
    private void populateContactNameComboBoxWithValue() {
        contactNameComboBox.getItems().clear();

        if (!ContactsDaoImpl.getAllContacts().isEmpty()) {
            contactsModel = ContactsDaoImpl.getAllContacts().get(selectedAppointments.getContactID() - 1);

            // Set selected value in ComboBox
            contactNameComboBox.setValue(contactsModel.getContactName());

            // Add all contact names to ComboBox
            for (Contacts contact : ContactsDaoImpl.getAllContacts()) {

                contactNameComboBox.getItems().add(contact.getContactName());
            }
        }
    }

    /**
     * Populates the User ID ComboBox with values and sets the selected value.
     *
     */
    private void populateUserIDComboBoxWithValue() {
        userIDComboBox.getItems().clear();

        if (!UsersDaoImpl.getAllUsers().isEmpty()) {
            usersModel = UsersDaoImpl.getAllUsers().get(selectedAppointments.getUserID() - 1);

            // Set selected value in ComboBox
            userIDComboBox.setValue(Integer.toString(usersModel.getUserID()));

            // Add all user IDs to ComboBox
            for (Users user : UsersDaoImpl.getAllUsers()) {

                userIDComboBox.getItems().add(Integer.toString(user.getUserID()));
            }
        }
    }

    /**
     * Populates the Customer ID ComboBox with values and sets the selected value.
     */
    private void populateCustomerIDComboBoxWithValue() {
        customerIDComboBox.getItems().clear();

        if (!CustomersDaoImpl.getAllCustomers().isEmpty()) {
            customersModel = CustomersDaoImpl.getAllCustomers().get(selectedAppointments.getCustomerID() - 1);

            // Set selected value in ComboBox
            customerIDComboBox.setValue(Integer.toString(customersModel.getCustomerID()));

            // Add all customer IDs to ComboBox
            for (Customers customer : CustomersDaoImpl.getAllCustomers()) {

                customerIDComboBox.getItems().add(Integer.toString(customer.getCustomerID()));
            }
        }
    }

    /**
     * Populates the start and end date/time ComboBoxes and DatePickers with the specified values.
     *
     * @param startTime The selected start time for the appointment.
     * @param startDate The selected start date for the appointment.
     * @param endTime The selected end time for the appointment.
     * @param endDate The selected end date for the appointment.
     */
    private void populateStartAndEndDateTime(String startTime, LocalDate startDate, String endTime, LocalDate endDate) {
        ObservableList<String> time = FXCollections.observableArrayList();
        time.addAll(TimeProcessing.generateLocalHoursWithSeconds());
   //     time.addAll(TimeProcessing.createLocalTimeList(TimeProcessing.generateHours()));

        if (startTime != null && startDate != null && endTime != null && endDate != null) {
            startTimeComboBox.setItems(time);
            startTimeComboBox.setValue(startTime);
            startDatePicker.getEditor().setText(startDate.toString());
            endTimeComboBox.setItems(time);
            endTimeComboBox.setValue(endTime);
            endDatePicker.getEditor().setText(endDate.toString());

        } else {
            startTimeComboBox.setItems(time);
            endTimeComboBox.setItems(time);
        }

    }

    /**
     * Checks for upcoming appointments within 15 minutes of user login time.
     *
     * @param userLoginTime The time of user login.
     */
    public static void checkUpcomingAppointments(LocalDateTime userLoginTime) {

        if (!AppointmentsDaoImpl.getUpcomingAppointmentWithin15Min(userLoginTime.toString()).isEmpty()) {
            // Display an alert with appointment details
            showAppointmentAlert(userLoginTime);

        } else {
            showNoAppointmentsMessage();
        }


    }

    /**
     * Displays an alert for upcoming appointments within 15 minutes based on the user's login time.
     *
     * @param userLoginTime The user's login time to check for upcoming appointments.
     */
    private static void showAppointmentAlert(LocalDateTime userLoginTime) {
        if (!AppointmentsDaoImpl.getUpcomingAppointmentWithin15Min(userLoginTime.toString()).isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Upcoming Appointments");
            a.setHeaderText("You have an upcoming appointment!");
            Appointments appointments = AppointmentsDaoImpl.getUpcomingAppointmentWithin15Min(userLoginTime.toString()).get(0);
            a.setContentText("Appointment ID: " + appointments.getAppointmentID() +
                    "\nDate: " + appointments.getStart().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+
                    "\nTime: " + appointments.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

            a.show();
        }
    }

    /**
     * Displays a message indicating no upcoming appointments.
     */
    private static void showNoAppointmentsMessage() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Upcoming Appointments");
        a.setHeaderText("No upcoming appointment!");
        a.setContentText("There are no upcoming appointments.");
        a.show();
    }

    /**
     * Sets up listeners for start and end date pickers, as well as start and end time combo boxes.
     * When the start date picker value changes, the corresponding flag (startDatePickerChanged) is set to true.
     * When the end date picker value changes, the corresponding flag (endDatePickerChanged) is set to true.
     * When the start time combo box selection changes, the corresponding flag (startTimeChanged) is set to false.
     * When the end time combo box selection changes, the corresponding flag (endTimeChanged) is set to false.
     */
    private void startEndDateTimeListiners() {
        startDatePicker.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            startDatePickerChanged = true;
        });
        endDatePicker.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            endDatePickerChanged = true;
        });
        startTimeComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            startTimeChanged = false;
        });
        endTimeComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            endTimeChanged = false;
        });
    }
    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showSchedulingTableView();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tbleViewSelectionListener();
        startEndDateTimeListiners();
    }



    /**
     * Starts the JavaFX application by loading the scheduling.fxml file and displaying the stage.
     *
     * @param stage The primary stage for the application.
     *
     * @throws Exception If an exception occurs during the loading or initialization process.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/scheduling.fxml"));
        //   primaryStage.setTitle("Scheduling Application");
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