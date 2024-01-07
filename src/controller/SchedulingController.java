package controller;
/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import dao.AppointmentsDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Appointments;
import dao.AppointmentsDaoImpl;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.logging.Level;


/**
 * This FXML class is the scheduling controller that contains business logic for the scheduling view.
 */
public class SchedulingController implements Initializable {

    @FXML // fx:id="appointmentIDCol"
    private TableColumn<?, ?> appointmentIDCol; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentIDTxt"
    private TextField appointmentIDTxt; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentTblView"
    private TableView<Appointments> appointmentTblView; // Value injected by FXMLLoader

    @FXML // fx:id="contactCol"
    private TableColumn<?, ?> contactCol; // Value injected by FXMLLoader

    @FXML // fx:id="contactNameComboBox"
    private ComboBox<?> contactNameComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="customerIDCol"
    private TableColumn<?, ?> customerIDCol; // Value injected by FXMLLoader

    @FXML // fx:id="customerIDTxt"
    private TextField customerIDTxt; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionCol"
    private TableColumn<?, ?> descriptionCol; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTxt"
    private TextField descriptionTxt; // Value injected by FXMLLoader

    @FXML // fx:id="endDateAndTimeTxt"
    private TextField endDateAndTimeTxt; // Value injected by FXMLLoader

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

    @FXML // fx:id="startDateAndTimeTxt"
    private TextField startDateAndTimeTxt; // Value injected by FXMLLoader

    @FXML // fx:id="startDateTimeCol"
    private TableColumn<?, ?> startDateTimeCol; // Value injected by FXMLLoader

    @FXML // fx:id="titleCol"
    private TableColumn<?, ?> titleCol; // Value injected by FXMLLoader

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

    /**
     * TODO
     * @param event
     */
    @FXML
    void onActionAddAppointment(ActionEvent event) {
        // TODO  Write code that enables the user to add, update, and delete appointments.
        // TODO When adding and updating an appointment, record the following data: Appointment_ID, title, description, location,
        //  contact, type, start date and time, end date and time, Customer_ID, and User_ID.
        System.out.println("Add button clicked");

    }

    /**
     * TODO
     * @param actionEvent
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent actionEvent) {
        // TODO  Write code that enables the user to add, update, and delete appointments.
        // TODO When adding and updating an appointment, record the following data: Appointment_ID, title, description, location,
        //  contact, type, start date and time, end date and time, Customer_ID, and User_ID.
        // TODO All of the original appointment information is displayed on the update form in local time zone.
        //TODO All of the appointment fields can be updated except Appointment_ID, which must be disabled.
        System.out.println("update button clicked");
    }

    /**
     * TODO
     * @param event
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        // TODO  Write code that enables the user to add, update, and delete appointments.
        System.out.println("Delete button clicked");
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
     * TODO
     */
    @FXML
    void updateSchedulingTableView(){
        // TODO Please include each of the following requirements as columns: Appointment_ID, Title, Description,
        //  Location, Contact, Type, Start Date and Time, End Date and Time, Customer_ID, User_ID
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));


        try {
            appointmentsList.addAll(AppointmentsDaoImpl.getAllAppointments());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        appointmentTblView.setItems(appointmentsList);

    }

    /**
     * TODO
     * @param actionEvent
     */
    @FXML
    void onActionContactNameComboBox(ActionEvent actionEvent) {
        // TODO -A contact name is assigned to an appointment using a drop-down menu or combo box.
        }

    /**
     * TODO
     * @param customMessage
     */
    void setCustomMessage(String customMessage) {
        // TODO A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.

    }

    /**
     * TODO
     * @param appointmentID
     */
    void autoGenerateAppointmentID(int appointmentID) throws SQLException {
       //Appointments.setAppID();
       // AppointmentsDaoImpl.selectAppointment(appointmentID);

        // TODO The Appointment_ID is auto-generated and disabled throughout the application.

    }
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

//c.  Write code that enables the user to adjust appointment times. While the appointment times should be stored in
// Coordinated Universal Time (UTC), they should be automatically and consistently updated according to the local
// time zone set on the user’s computer wherever appointments are displayed in the application..Note: There are up to
// three time zones in effect. Coordinated Universal Time (UTC) is used for storing the time in the database,
// the user’s local time is used for display purposes, and Eastern Time (ET) is used for the company’s office hours.
// Local time will be checked against ET business hours before they are stored in the database as UTC.


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


    /**
     * This method initializes this scheduling controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateSchedulingTableView();
    }

}