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

    @FXML // fx:id="customerIDTxt"
    private TextField customerIDTxt; // Value injected by FXMLLoader

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

    @FXML // fx:id="userIDTxt"
    private TextField userIDTxt; // Value injected by FXMLLoader

    @FXML
    private Label customMessageTxt;


    private Stage stage;
    private Parent scene;

    private Contacts contactsModel;
    private Appointments appointmentsModel;

    private static Appointments selectedAppointments; //new
    private static ObservableList<Appointments> appointmentsList; //new

    private String endTimeSelected;
    private String endDateSelected;

    private String startTimeSelected;
    private String startDateSelected;



    /**
     * TODO the total number of customer appointments by type and month
     * @param event
     */
    @FXML
    void onActionReport1(ActionEvent event) {

//        appointmentTblView.getItems().clear();
//
//        ObservableList<String> appointmentsList = AppointmentsDaoImpl.getAllAppointmentsCustomerIDMT();
//
//        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
//        startDateTimeCol.setText("Month");
//        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
//        descriptionCol.setText("Total_Appointments");
//        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Total_Appointments"));
//
//
//        try {
//            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointmentsCustomerIDMT());
//
//        } catch (Exception ex) {
//            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        appointmentTblView.setItems(appointmentsList);
    }

    /**
     * TODO a schedule for each contact in your organization that includes appointment ID, title, type and description,
     *  start date and time, end date and time, and customer ID
     * @param event
     */
    @FXML
    void onActionReport2(ActionEvent event) {
//     SELECT a.appointment_id, a.title, a.appointment_type, a.description, a.start_date, a.end_date,
//         a.customer_id FROM appointments_table a JOIN contacts_table c ON a.contact_id = c.contact_id;

    }

    /**
     * TODO an additional report of your choice that is different from the two other required reports in this prompt
     *  and from the user log-in date and time stamp that will be tracked in part C
     * @param event
     */
    @FXML
    void onActionReport3(ActionEvent event) {

    }


    /**
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionAppointment(ActionEvent event) throws SQLException {
        clearSelectionAndFormFields();

        appointmentIDTxt.setText(" ");
        appointmentIDTxt.setDisable(true);


        //populating start and end date/time combo box and date picker with default values
        populateStartAndEndDateTime(null,null,null,null);



        // A contact name is assigned to an appointment using a drop-down menu or combo box.
        if(!ContactsDaoImpl.getAllContacts().isEmpty()) {
            for (Contacts contact : ContactsDaoImpl.getAllContacts()) {

                contactNameComboBox.getItems().add(contact.getContactName());
            }
        }

    }

    /**
     * TODO startDateAndTimeTxt.getText(),endDateAndTimeTxt.getText()
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
                descriptionTxt.getText(),locationTxt.getText(),typeTxt.getText(),getStartDateTimeSelected(),
                getEndDateTimeSelected(),dateTimeFormatter.format(LocalDateTime.now()),user.getUserName(),
                dateTimeFormatter.format(LocalDateTime.now()),user.getUserName(),Integer.parseInt(customerIDTxt.getText()),
                Integer.parseInt(userIDTxt.getText()),contactsModel.getContactID());
        addCustomerDatabase();
        clearSelectionAndFormFields();
        showSchedulingTableView();


    }

    private String getStartDateTimeSelected() {
        if(startDateSelected!=null && startTimeSelected!=null){
            return DateTimeProcessing.convertTimeToLocalThenUTC(startDateSelected + " " + startTimeSelected);
        }
        return DateTimeProcessing.convertTimeToLocalThenUTC(startDatePicker.getEditor().getText() + " " + startTimeComboBox.getEditor().getText());
    }

    /**
     * Getter and While the appointment times should be stored in Coordinated Universal Time (UTC),
     * @return
     */
    private String getEndDateTimeSelected() {
//        if(endDateSelected!=null && endTimeSelected!=null ){
//            return DateTimeProcessing.convertTimeToLocalThenUTC(endDateSelected + " " + endTimeSelected);
//        }
//        if(endDateSelected!=null && endTimeSelected==null ){
//            return DateTimeProcessing.convertTimeToLocalThenUTC(endDateSelected + " " + endTimeComboBox.getEditor().getText());
//        }
//        if(endDateSelected==null && endTimeSelected!=null ){
//            return DateTimeProcessing.convertTimeToLocalThenUTC(endDatePicker.getEditor().getText() + " " + endTimeSelected);
//        }
//        if(endDateSelected==null && endTimeSelected==null ){
//            return DateTimeProcessing.convertTimeToLocalThenUTC(endDatePicker.getEditor().getText() + " " + endTimeComboBox.getEditor().getText());
//        }
        //return DateTimeProcessing.convertTimeToLocalThenUTC(endDatePicker.getEditor().getText() + " " + endTimeComboBox.getEditor().getText());


        return DateTimeProcessing.convertTimeToLocalThenUTC(endDateSelected + " " + endTimeSelected);

    }

    private void addCustomerDatabase() throws SQLException {
        AppointmentsDaoImpl.insertAppointments(appointmentsModel.getTitle(),
                appointmentsModel.getDescription(),appointmentsModel.getLocation(),
                appointmentsModel.getType(),appointmentsModel.getStart(),appointmentsModel.getEnd(),
                appointmentsModel.getCreatedBy(), appointmentsModel.getLastUpdatedBy(),
                appointmentsModel.getCustomerID(), appointmentsModel.getUserID(), appointmentsModel.getContactID());
    }

    /**
     * Write code that enables the user to update appointments.
     * TODO All of the original appointment information is displayed on the update form in local time zone.
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
        appointmentsModel.setCustomerID(Integer.parseInt(customerIDTxt.getText()));
        appointmentsModel.setUserID(Integer.parseInt(userIDTxt.getText()));
        appointmentsModel.setContactID(contactsModel.getContactID());
        updateCustomerDatabase();
        clearSelectionAndFormFields();
        showSchedulingTableView();

    }

    /**
     * TODO startDateAndTimeTxt.getText(),endDateAndTimeTxt.getText() on update
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
        System.out.println("This is starttime sent to database: "+appointmentsModel.getStart());
        AppointmentsDaoImpl.updateAppointment(appointmentsModel.getAppointmentID(),
                appointmentsModel.getTitle(),appointmentsModel.getDescription(),appointmentsModel.getLocation(),
                appointmentsModel.getType(),appointmentsModel.getStart(),appointmentsModel.getEnd(),
                appointmentsModel.getLastUpdate(), appointmentsModel.getLastUpdatedBy(),
                appointmentsModel.getCustomerID(), appointmentsModel.getUserID(), appointmentsModel.getContactID());

    }

    /**
     * Write code that enables the user to delete appointments.
     * @param event
     */
    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, FileNotFoundException {
        // TODO  Write code that enables the user to add, update, and delete appointments.

        //setCustomMessage(Alert.AlertType.CONFIRMATION,"Appointment Canceled","A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.");
        appointmentsModel = appointmentTblView.getSelectionModel().getSelectedItem();
        appointmentsModel.setAppointmentID(Integer.parseInt(appointmentIDTxt.getText()));
        deleteCustomerDatabase();
        clearSelectionAndFormFields();
        showSchedulingTableView();
    }

    /**
     *
     * @throws SQLException
     * @throws FileNotFoundException
     */
    private void deleteCustomerDatabase() throws SQLException, FileNotFoundException {

        AppointmentsDaoImpl.deleteAppointment(appointmentsModel.getAppointmentID());

    }

    /**
     * TODO
     * @param actionEvent
     */
    @FXML
    void onActionFilterAppointmentMonthRBtn(ActionEvent actionEvent) {
        //TODO Write code that enables the user to view appointment schedules by month and week using a TableView and
        // allows the user to choose between these two options using tabs or radio buttons for filtering appointments.

        System.out.println("Month button clicked");
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    void onActionFilterAppointmentWeekRBtn(ActionEvent actionEvent) {
        //TODO Write code that enables the user to view appointment schedules by month and week using a TableView and
        // allows the user to choose between these two options using tabs or radio buttons for filtering appointments.

        System.out.println("Week button clicked");
    }

    /**
     * Write code that enables the user to view appointment schedules using a TableView
     */
    @FXML
    void showSchedulingTableView(){
        // TODO
        //ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList(); new
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
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointmentsLocalDateTime());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }


        appointmentTblView.setItems(appointmentsList);


    }


    @FXML
    void onStartTimeSelected(ActionEvent event) {
        startTimeSelected = startTimeComboBox.getEditor().getText();

    }

    @FXML
    void onEndTimeSelected(ActionEvent event) {
        endTimeSelected = endTimeComboBox.getEditor().getText();


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

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateTimeFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String dateString) {
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
     * @param actionEvent
     */
    @FXML
    void onActionContactNameComboBox(ActionEvent actionEvent) {

        if(!ContactsDaoImpl.getContact(contactNameComboBox.getValue()).isEmpty())
        contactsModel= ContactsDaoImpl.getContact(contactNameComboBox.getValue()).get(0);

    }

    /**
     * TODO
     * @param customMessage
     */
    void setCustomMessage(Alert.AlertType alertType, String title, String customMessage) {
        // TODO A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.

        Alert a = new Alert(alertType);
        a.setTitle(title);
        a.setContentText(customMessage);
        a.showAndWait();


    }


    /**
     * TODO comment
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * TODO comment
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
        customerIDTxt.clear();
        userIDTxt.clear();
    }



// d.  Write code to implement input validation and logical error checks to prevent each of the following changes when
// adding or updating information; display a custom message specific for each error check in the user interface:
// -scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
// -scheduling overlapping appointments for customers
// -entering an incorrect username and password


//e.  Write code to provide an alert when there is an appointment within 15 minutes of the user’s log-in.
// A custom message should be displayed in the user interface and include the appointment ID, date, and time.
// If the user does not have any appointments within 15 minutes of logging in, display a custom message in the user
// interface indicating there are no upcoming appointments. Note: Since evaluation may be testing your application
// outside of business hours, your alerts must be robust enough to trigger an appointment within 15 minutes of the
// local time set on the user’s computer, which may or may not be ET.

private void tbleViewSelectionListener() {
    appointmentTblView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->
    {if(newSelection !=null) {
        //Appointments selectedAppointments = appointmentTblView.getSelectionModel().getSelectedItem(); new

        selectedAppointments = appointmentTblView.getSelectionModel().getSelectedItem();

        //All of the appointment fields can be updated except Appointment_ID, which must be disabled.
        // The Appointment_ID is disabled throughout the application.
        appointmentIDTxt.setDisable(true);
        appointmentIDTxt.setText(String.valueOf(selectedAppointments.getAppointmentID()));
        titleTxt.setText(selectedAppointments.getTitle().getValue());
        descriptionTxt.setText(selectedAppointments.getDescription());
        locationTxt.setText(selectedAppointments.getLocation());

        if(!ContactsDaoImpl.getAllContacts().isEmpty()) {
            contactsModel = ContactsDaoImpl.getAllContacts().get(selectedAppointments.getContactID() - 1);

            contactNameComboBox.setValue(contactsModel.getContactName());
            for (Contacts contact : ContactsDaoImpl.getAllContacts()) {

                contactNameComboBox.getItems().add(contact.getContactName());
            }
        }

        typeTxt.setText(selectedAppointments.getType());



    //populating start and end date/time combo box and date picker with selected values
        populateStartAndEndDateTime(TimeProcessing.getCorrectTimeSeconds(TimeProcessing.getTime(selectedAppointments.getStart())),
                DateProcessing.getDate(selectedAppointments.getStart()),
                TimeProcessing.getCorrectTimeSeconds(TimeProcessing.getTime(selectedAppointments.getEnd())),
                DateProcessing.getDate(selectedAppointments.getEnd()));


        customerIDTxt.setText(String.valueOf(selectedAppointments.getCustomerID()));
        userIDTxt.setText(String.valueOf(selectedAppointments.getUserID()));
    }
    });
}

    /**
     * populating start and end date/time combo box and date picker
     * @param startTime
     * @param startDate
     * @param endTime
     * @param endDate
     */
    private void populateStartAndEndDateTime(String startTime, LocalDate startDate,String endTime, LocalDate endDate) {
        ObservableList<String> time = FXCollections.observableArrayList();
        time.addAll("00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00",
                "08:00:00", "09:00:00", "10:00:00", "11:00:00",
                "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00");

        if(startTime!=null && startDate!=null && endTime!=null && endDate!=null){
            startTimeComboBox.setItems(time);
            startTimeComboBox.setValue(startTime);
            startTimeComboBox.setEditable(true);
            startDatePicker.getEditor().setText(startDate.toString());
            endTimeComboBox.setItems(time);
            endTimeComboBox.setValue(endTime);
            endTimeComboBox.setEditable(true);
            endDatePicker.getEditor().setText(endDate.toString());

        }else {
            startTimeComboBox.setItems(time);
            startTimeComboBox.setEditable(true);
            endTimeComboBox.setItems(time);
            endTimeComboBox.setEditable(true);
        }

    }

    /**
     * This method initializes this scheduling controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSchedulingTableView();
        tbleViewSelectionListener();

    }



    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/scheduling.fxml"));
        //   primaryStage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void stop() throws Exception{
        FileIOManager.deleteCurrentFile();

    }

}