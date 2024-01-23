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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
 * This FXML class is the scheduling controller that contains business logic for the scheduling view.
 */
public class SchedulingController extends Application implements Initializable {

    @FXML // fx:id="appointmentIDCol"
    private TableColumn<Appointments, Integer> appointmentIDCol; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentIDTxt"
    private TextField appointmentIDTxt; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentTblView"
    private TableView<Appointments> appointmentTblView; // Value injected by FXMLLoader

    @FXML // fx:id="contactCol"
    private TableColumn<?, ?> contactCol; // Value injected by FXMLLoader

    @FXML // fx:id="contactNameComboBox"
    private ComboBox<String> contactNameComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="timeComboBox"
    private ComboBox<String> endTimeComboBox; // Value injected by FXMLLoader


    @FXML // fx:id="datePicker"
    private DatePicker endDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="startDatePicker"
    private DatePicker startDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="startTimeComboBox"
    private ComboBox<String> startTimeComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="customerIDCol"
    private TableColumn<?, ?> customerIDCol; // Value injected by FXMLLoader


    @FXML // fx:id="customerIDComboBox"
    private ComboBox<String> customerIDComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionCol"
    private TableColumn<?, ?> descriptionCol; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTxt"
    private TextField descriptionTxt; // Value injected by FXMLLoader

    @FXML // fx:id="endDateTimeCol"
    private TableColumn<?, ?> endDateTimeCol; // Value injected by FXMLLoader

    @FXML // fx:id="filterAppointmentMonthRBtn"
    private RadioButton filterAppointmentMonthRBtn; // Value injected by FXMLLoader

    @FXML // fx:id="filterAppointmentWeekRBtn"
    private RadioButton filterAppointmentWeekRBtn; // Value injected by FXMLLoader

    @FXML // fx:id="filterAllAppointmentRBtn"
    private RadioButton filterAllAppointmentRBtn; // Value injected by FXMLLoader

    @FXML // fx:id="locationCol"
    private TableColumn<?, ?> locationCol; // Value injected by FXMLLoader

    @FXML // fx:id="locationTxt"
    private TextField locationTxt; // Value injected by FXMLLoader

    @FXML // fx:id="startDateTimeCol"
    private TableColumn<?, ?> startDateTimeCol; // Value injected by FXMLLoader

    @FXML // fx:id="titleCol"
    private TableColumn<Appointments, String> titleCol; // Value injected by FXMLLoader

    @FXML // fx:id="titleTxt"
    private TextField titleTxt; // Value injected by FXMLLoader

    @FXML // fx:id="typeCol"
    private TableColumn<?, ?> typeCol; // Value injected by FXMLLoader

    @FXML // fx:id="typeTxt"
    private TextField typeTxt; // Value injected by FXMLLoader

    @FXML // fx:id="userIDCol"
    private TableColumn<?, ?> userIDCol; // Value injected by FXMLLoader


    @FXML // fx:id="userIDComboBox"
    private ComboBox<String> userIDComboBox; // Value injected by FXMLLoader

    @FXML
    private Label customMessageTxt;


    private Stage stage;
    private Parent scene;

    private Contacts contactsModel;
    private Appointments appointmentsModel;
    private Users usersModel;
    private Customers customersModel;

    private static Appointments selectedAppointments; //new
    private static ObservableList<Appointments> appointmentsList; //new

    private String endTimeSelected;
    private String endDateSelected;

    private String startTimeSelected;
    private String startDateSelected;

    private String customerIDSelected;
    private String userIDSelected;


    /**
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionAppointment(ActionEvent event) throws SQLException {
        clearSelectionAndFormFields();

        appointmentIDTxt.setText(" ");
        appointmentIDTxt.setDisable(true);


        //populating start and end date/time combo box and date picker with default values
        populateStartAndEndDateTime(null, null, null, null);


        // A contact name is assigned to an appointment using a drop-down menu or combo box.
        if (!ContactsDaoImpl.getAllContacts().isEmpty()) {
            for (Contacts contact : ContactsDaoImpl.getAllContacts()) {

                contactNameComboBox.getItems().add(contact.getContactName());
            }
        }

    }

    /**
     * TODO startDateAndTimeTxt.getText(),endDateAndTimeTxt.getText()
     *
     * @param event
     */
    @FXML
    void onActionAdd(ActionEvent event) throws FileNotFoundException, SQLException {
        //All of the appointment fields can be updated except Appointment_ID, which must be disabled.
        // TODO  Write code that enables the user to add, update, and delete appointments.
        // TODO When adding and updating an appointment, record the following data: Appointment_ID, title, description, location,
        //  contact, type, start date and time, end date and time, Customer_ID, and User_ID.

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Users user = UsersDaoImpl.getUser(FileIOManager.readFile()).get(0);


        appointmentsModel = new Appointments((new ReadOnlyStringWrapper(titleTxt.getText())),
                descriptionTxt.getText(), locationTxt.getText(), typeTxt.getText(), getStartDateTimeSelected(),
                getEndDateTimeSelected(), dateTimeFormatter.format(LocalDateTime.now()), user.getUserName(),
                dateTimeFormatter.format(LocalDateTime.now()), user.getUserName(), Integer.parseInt(customerIDSelected),
                Integer.parseInt(userIDSelected), contactsModel.getContactID());


        if(!isValidAppointment()){

            addCustomerDatabase();
            clearSelectionAndFormFields();
        }



        showSchedulingTableView();


    }

    /**
     *  d.  Write code to implement input validation and logical error checks to prevent each of the following changes when
     *  adding or updating information; display a custom message specific for each error check in the user interface:
     *  -scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
     *  -scheduling overlapping appointments for customers
     * @return
     * @throws SQLException
     */
    private boolean isValidAppointment() throws SQLException {
        boolean isValidAppointment=false;
        if(DateTimeProcessing.isOutsideBusinessHours(DateProcessing.getDateFromDateTime(appointmentsModel.getStart()).toString(),
                TimeProcessing.getTimeFromDateTime(appointmentsModel.getStart()))==true ||
                DateTimeProcessing.isOutsideBusinessHours(DateProcessing.getDateFromDateTime(appointmentsModel.getEnd()).toString(),
                        TimeProcessing.getTimeFromDateTime(appointmentsModel.getEnd()))==true){
            //scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
            isValidAppointment=true;
            //display a custom message specific for each error check in the user interface
            setCustomMessage(Alert.AlertType.ERROR,"Appointment outside of business hours",
                    "Please schedule an appointment inside of business hours defined as 8:00 a.m. to 10:00 p.m. ET" +
                            "and Monday through Friday");
        }
        if(AppointmentsDaoImpl.hasOverlappingAppointments(appointmentsModel.getCustomerID(),
                TimeProcessing.getTimeFromDateTime(appointmentsModel.getStart()),
                TimeProcessing.getTimeFromDateTime(appointmentsModel.getEnd()))){
            // -scheduling overlapping appointments for customers
            isValidAppointment=true;
            //display a custom message specific for each error check in the user interface
            setCustomMessage(Alert.AlertType.ERROR,"Scheduling Overlap","Please schedule a non-overlapping " +
                    "appointment for customer");
        }
        return isValidAppointment;
    }

    private String getStartDateTimeSelected() {
        if (startDateSelected != null && startTimeSelected != null) {
            return startDateSelected + " " + startTimeSelected;

        }

        return startDatePicker.getEditor().getText() + " " + startTimeComboBox.getEditor().getText();
    }

    /**
     * Getter and While the appointment times should be stored in Coordinated Universal Time (UTC),
     *
     * @return
     */
    private String getEndDateTimeSelected() {
        if (endDateSelected != null && endTimeSelected != null) {
            return endDateSelected + " " + endTimeSelected;
        }

        return endDatePicker.getEditor().getText() + " " + endTimeComboBox.getEditor().getText();

    }

    private void addCustomerDatabase() throws SQLException {
        AppointmentsDaoImpl.insertAppointments(appointmentsModel.getTitle(),
                appointmentsModel.getDescription(), appointmentsModel.getLocation(),
                appointmentsModel.getType(), appointmentsModel.getStart(), appointmentsModel.getEnd(),
                appointmentsModel.getCreatedBy(), appointmentsModel.getLastUpdatedBy(),
                appointmentsModel.getCustomerID(), appointmentsModel.getUserID(), appointmentsModel.getContactID());
    }

    /**
     * Write code that enables the user to update appointments.
     * TODO All of the original appointment information is displayed on the update form in local time zone.
     *
     * @param actionEvent
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
        appointmentsModel.setStart(getStartDateTimeSelected());
        appointmentsModel.setEnd(getEndDateTimeSelected());
        appointmentsModel.setLastUpdate(DateTimeProcessing.getCurrentLocalDateTimeString());
        appointmentsModel.setLastUpdatedBy(user.getUserName());
        appointmentsModel.setCustomerID(Integer.parseInt(customerIDSelected));
        appointmentsModel.setUserID(Integer.parseInt(userIDSelected));
        appointmentsModel.setContactID(contactsModel.getContactID());

        if(!isValidAppointment()){
            updateCustomerDatabase();
            clearSelectionAndFormFields();
        }

        showSchedulingTableView();

    }

    /**
     * TODO startDateAndTimeTxt.getText(),endDateAndTimeTxt.getText() on update
     *
     * @throws SQLException
     * @throws FileNotFoundException
     */
    private void updateCustomerDatabase() throws SQLException, FileNotFoundException {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        Users user = UsersDaoImpl.getUser(FileIOManager.readFile()).get(0);


//        AppointmentsDaoImpl.updateAppointment(Integer.parseInt(appointmentIDTxt.getText()),
//                titleTxt.getText(),descriptionTxt.getText(),locationTxt.getText(),
//                typeTxt.getText(),startDateAndTimeTxt.getText(),endDateAndTimeTxt.getText(),
//                dateTimeFormatter.format(LocalDateTime.now()), user.getUserName(),
//                Integer.parseInt(customerIDTxt.getText()), Integer.parseInt(userIDTxt.getText()), contactsModel.getContactID());
        System.out.println("This is starttime sent to database: " + appointmentsModel.getStart());
        AppointmentsDaoImpl.updateAppointment(appointmentsModel.getAppointmentID(),
                appointmentsModel.getTitle(), appointmentsModel.getDescription(), appointmentsModel.getLocation(),
                appointmentsModel.getType(), appointmentsModel.getStart(), appointmentsModel.getEnd(),
                appointmentsModel.getLastUpdate(), appointmentsModel.getLastUpdatedBy(),
                appointmentsModel.getCustomerID(), appointmentsModel.getUserID(), appointmentsModel.getContactID());

    }

    /**
     * Write code that enables the user to delete appointments.
     *
     * @param event
     */
    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, FileNotFoundException {
        // TODO  Write code that enables the user to add, update, and delete appointments.
        appointmentsModel = appointmentTblView.getSelectionModel().getSelectedItem();
        showCanceledMessage();
        appointmentsModel.setAppointmentID(Integer.parseInt(appointmentIDTxt.getText()));
        deleteCustomerDatabase();
        clearSelectionAndFormFields();
        showSchedulingTableView();
    }

    /**
     * A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.
     */
    private void showCanceledMessage() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Appointment Canceled");
        a.setHeaderText("Appointment Successfully Canceled");
        a.setContentText("Appointment ID: " + appointmentsModel.getAppointmentID()+
                "\nType of appointment: " + appointmentsModel.getType());

        a.show();
    }

    /**
     * @throws SQLException
     * @throws FileNotFoundException
     */
    private void deleteCustomerDatabase() throws SQLException, FileNotFoundException {

        AppointmentsDaoImpl.deleteAppointment(appointmentsModel.getAppointmentID());

    }



    /**
     * logic to fetch and populate all appointments
     * <p>
     * Write code that enables the user to view appointment schedules by month and week using a TableView and
     * allows the user to choose between these two options using tabs or radio buttons for filtering appointments.
     *
     * @param event
     */
    @FXML
    void onActionFilterAllAppointment(ActionEvent event) {
        populateAllAppointments();
    }

    private void populateAllAppointments() {
        // Clear existing appointments
        appointmentsList.clear();

        try {
            // Fetch and add new all appointments to appointmentsList
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointments());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Update TableView
        appointmentTblView.setItems(appointmentsList);
    }

    /**
     * logic to fetch and populate appointments for the month
     * <p>
     * Write code that enables the user to view appointment schedules by month and week using a TableView and
     * allows the user to choose between these two options using tabs or radio buttons for filtering appointments.
     *
     * @param actionEvent
     */
    @FXML
    void onActionFilterAppointmentMonthRBtn(ActionEvent actionEvent) {
        populateAppointmentsByMonth();

    }

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
     * logic to fetch and populate appointments for the week
     * <p>
     * Write code that enables the user to view appointment schedules by month and week using a TableView and
     * allows the user to choose between these two options using tabs or radio buttons for filtering appointments.
     *
     * @param actionEvent
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
     * Write code that enables the user to view appointment schedules using a TableView
     */
    @FXML
    void showSchedulingTableView() {
        // TODO
        //ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList(); new

        checkUpcomingAppointments(LocalDateTime.now());

        appointmentsList = FXCollections.observableArrayList();

        //Please include each of the following requirements as columns: Appointment_ID, Title, Description, Location,
        // Contact, Type, Start Date and Time, End Date and Time, Customer_ID, User_ID
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        // titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setCellValueFactory(cellDataFeatures -> {
            return cellDataFeatures.getValue().getTitle();
        });
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));


        try {
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointments());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }


        appointmentTblView.setItems(appointmentsList);


        //The Appointment_ID is disabled throughout the application.
        appointmentIDTxt.setDisable(true);


    }

    @FXML
    void onActionSelectCustomerID(ActionEvent event) {
        customerIDSelected= customerIDComboBox.getValue();

//        if (!ContactsDaoImpl.getContact(contactNameComboBox.getValue()).isEmpty())
//            contactsModel = ContactsDaoImpl.getContact(contactNameComboBox.getValue()).get(0);

    }

    @FXML
    void onActionSelectUserID(ActionEvent event) {

        userIDSelected= userIDComboBox.getValue();

    }

    @FXML
    void onStartTimeSelected(ActionEvent event) {
       // startTimeSelected = startTimeComboBox.getEditor().getText();
        startTimeSelected = startTimeComboBox.getValue();
    }

    @FXML
    void onEndTimeSelected(ActionEvent event) {
        //endTimeSelected = endTimeComboBox.getEditor().getText();

        endTimeSelected = endTimeComboBox.getValue();


    }

    @FXML
    void onActionStartDate(ActionEvent event) {
        datePickerStringConverter(startDatePicker);
        startDateSelected = startDatePicker.getValue().toString();

    }

    @FXML
    void onActionEndDate(ActionEvent event) {
        datePickerStringConverter(endDatePicker);
        endDateSelected = endDatePicker.getValue().toString();
    }

    private void datePickerStringConverter(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateTimeFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString != null && !dateString.isEmpty()) {
                    return LocalDate.parse(dateString, dateTimeFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    /**
     * A contact name is assigned to an appointment using a drop-down menu or combo box.
     *
     * @param actionEvent
     */
    @FXML
    void onActionContactNameComboBox(ActionEvent actionEvent) {

        if (!ContactsDaoImpl.getContact(contactNameComboBox.getValue()).isEmpty())
            contactsModel = ContactsDaoImpl.getContact(contactNameComboBox.getValue()).get(0);

    }

    /**
     * TODO
     *
     * @param customMessage
     */
    void setCustomMessage(Alert.AlertType alertType, String title, String customMessage) {
        // TODO A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.

        Alert a = new Alert(alertType);
        a.setTitle(title);
        //a.setHeaderText();
        a.setContentText(customMessage);
        a.showAndWait();


    }


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

    /**
     * TODO comment
     *
     * @param event
     */
    @FXML
    void onActionCancel(ActionEvent event) {
        clearSelectionAndFormFields();

    }

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
        startTimeComboBox.getItems().clear();
        endDatePicker.getEditor().clear();
        endTimeComboBox.getItems().clear();
        customerIDComboBox.getItems().clear();
        userIDComboBox.getItems().clear();
    }


    private void tbleViewSelectionListener() {

        appointmentTblView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null) {
                //Appointments selectedAppointments = appointmentTblView.getSelectionModel().getSelectedItem(); new

                selectedAppointments = appointmentTblView.getSelectionModel().getSelectedItem();

                //All of the appointment fields can be updated except Appointment_ID, which must be disabled.
                // The Appointment_ID is disabled throughout the application.
                appointmentIDTxt.setDisable(true);
                appointmentIDTxt.setText(String.valueOf(selectedAppointments.getAppointmentID()));
                titleTxt.setText(selectedAppointments.getTitle().getValue());
                descriptionTxt.setText(selectedAppointments.getDescription());
                locationTxt.setText(selectedAppointments.getLocation());

                populateContactNameComboBox();

                typeTxt.setText(selectedAppointments.getType());


                //populating start and end date/time combo box and date picker with selected values
                populateStartAndEndDateTime(TimeProcessing.getCorrectTimeSeconds(TimeProcessing.getTime(selectedAppointments.getStart())),
                        DateProcessing.getDate(selectedAppointments.getStart()),
                        TimeProcessing.getCorrectTimeSeconds(TimeProcessing.getTime(selectedAppointments.getEnd())),
                        DateProcessing.getDate(selectedAppointments.getEnd()));



                populateCustomerIDComboBox();
                populateUserIDComboBox();
            }
        });
    }

    private void populateContactNameComboBox() {
        contactNameComboBox.getItems().clear();
        if (!ContactsDaoImpl.getAllContacts().isEmpty()) {
            contactsModel = ContactsDaoImpl.getAllContacts().get(selectedAppointments.getContactID() - 1);

            contactNameComboBox.setValue(contactsModel.getContactName());
            for (Contacts contact : ContactsDaoImpl.getAllContacts()) {

                contactNameComboBox.getItems().add(contact.getContactName());
            }
        }
    }


    private void populateUserIDComboBox() {
        userIDComboBox.getItems().clear();
        if (!UsersDaoImpl.getAllUsers().isEmpty()) {
            usersModel = UsersDaoImpl.getAllUsers().get(selectedAppointments.getUserID()-1);

            userIDComboBox.setValue(Integer.toString(usersModel.getUserID()));
            for (Users user : UsersDaoImpl.getAllUsers()) {

                userIDComboBox.getItems().add(Integer.toString(user.getUserID()));
            }
        }
    }

    private void populateCustomerIDComboBox() {
        customerIDComboBox.getItems().clear();
        if (!CustomersDaoImpl.getAllCustomers().isEmpty()) {
            customersModel = CustomersDaoImpl.getAllCustomers().get(selectedAppointments.getCustomerID()-1);

            customerIDComboBox.setValue(Integer.toString(customersModel.getCustomerID()));
            for (Customers customer : CustomersDaoImpl.getAllCustomers()) {

                customerIDComboBox.getItems().add(Integer.toString(customer.getCustomerID()));
            }
        }
    }

    /**
     * populating start and end date/time combo box and date picker
     *
     * @param startTime
     * @param startDate
     * @param endTime
     * @param endDate
     */
    private void populateStartAndEndDateTime(String startTime, LocalDate startDate, String endTime, LocalDate endDate) {
        ObservableList<String> time = FXCollections.observableArrayList();
        time.addAll(TimeProcessing.generateLocalHoursWithSeconds());

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
     * Note: Since evaluation may be testing your application
     *  outside of business hours, your alerts must be robust enough to trigger an appointment within 15 minutes of the
     *  local time set on the user’s computer, which may or may not be ET.
     * @param userLoginTime
     */
    public static void checkUpcomingAppointments(LocalDateTime userLoginTime) {

        if (!AppointmentsDaoImpl.getUpcomingAppointmentWithin15Min().isEmpty()) {
            // Display an alert with appointment details
            showAppointmentAlert();

        }else{
            showNoAppointmentsMessage();
        }


    }

    /**
     * Write code to provide an alert when there is an appointment within 15 minutes of the user’s log-in.
     *  A custom message should be displayed in the user interface and include the appointment ID, date, and time.
     *  code to show an alert with appointment details
     */
    private static void showAppointmentAlert() {
        if(!AppointmentsDaoImpl.getUpcomingAppointmentWithin15Min().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Upcoming Appointments");
            a.setHeaderText("You have an upcoming appointment!");
            Appointments appointments = AppointmentsDaoImpl.getUpcomingAppointmentWithin15Min().get(0);
            a.setContentText("Appointment ID: " + appointments.getAppointmentID()+
                    "\nDate and Time: " + appointments.getStart());

            a.show();
        }
    }

    /**
     *  If the user does not have any appointments within 15 minutes of logging in, display a custom message in the user
     *  interface indicating there are no upcoming appointments.
     *
     *  code to show a message indicating no upcoming appointments
     */
    private static void showNoAppointmentsMessage() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Upcoming Appointments");
        a.setHeaderText("No upcoming appointment!");
        a.setContentText("There are no upcoming appointments.");
        a.show();
    }

    /**
     * This method initializes this scheduling controller class
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSchedulingTableView();
        tbleViewSelectionListener();

//        // TODO event listeners for radio buttons for filtering
//        filterAllAppointmentRBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldSelection, Boolean newSelection) {
//                if(newSelection.equals(filterAllAppointmentRBtn)){
//                    populateAllAppointments();
//                }
//
//         }
//        }
//
//        );
//
//        filterAppointmentMonthRBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldSelection, Boolean newSelection) {
//                if(newSelection.equals(filterAppointmentMonthRBtn)){
//                    populateAppointmentsByMonth();
//                }
//            }
//        }
//
//        );
//
//       // filterAppointmentWeekRBtn.setOnAction(actionEvent -> onActionFilterAppointmentWeekRBtn(actionEvent));

    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/scheduling.fxml"));
        //   primaryStage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        FileIOManager.deleteCurrentFile();

    }

}