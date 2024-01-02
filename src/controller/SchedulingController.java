package controller;

import javafx.fxml.Initializable;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import dao.UserDAO;
import dao.CountriesDAO;
//import model.Users;
import model.Countries;

import javax.swing.*;

/**
 * NOTE: Delete When Finished
 *
 * Notes from Requirements:
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
 **/

/**
 * 3.  Add scheduling functionalities to the GUI-based application by doing the following:
 *
 * a.  Write code that enables the user to add, update, and delete appointments.
 * The code should also include the following functionalities:
 *
 * •  A contact name is assigned to an appointment using a drop-down menu or combo box.
 *
 * •  A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.
 *
 * •  The Appointment_ID is auto-generated and disabled throughout the application.
 *
 * •  When adding and updating an appointment, record the following data: Appointment_ID, title, description,
 * location, contact, type, start date and time, end date and time, Customer_ID, and User_ID.
 *
 * •  All of the original appointment information is displayed on the update form in local time zone.
 *
 * •  All of the appointment fields can be updated except Appointment_ID, which must be disabled.
 *
 *
 *
 * b.  Write code that enables the user to view appointment schedules by month and week using a
 * TableView and allows the user to choose between these two options using tabs or radio buttons for
 * filtering appointments. Please include each of the following requirements as columns:
 *
 * •  Appointment_ID
 *
 * •  Title
 *
 * •  Description
 *
 * •  Location
 *
 * •  Contact
 *
 * •  Type
 *
 * •  Start Date and Time
 *
 * •  End Date and Time
 *
 * •  Customer_ID
 *
 * •  User_ID
 *
 *
 *
 * c.  Write code that enables the user to adjust appointment times. While the appointment times should be
 * stored in Coordinated Universal Time (UTC), they should be automatically and consistently updated
 * according to the local time zone set on the user’s computer wherever appointments are displayed in the application.
 *
 *
 *
 * Note: There are up to three time zones in effect. Coordinated Universal Time (UTC) is used for
 * storing the time in the database, the user’s local time is used for display purposes,
 * and Eastern Time (ET) is used for the company’s office hours. Local time will be checked against
 * ET business hours before they are stored in the database as UTC.
 *
 *
 *
 * d.  Write code to implement input validation and logical error checks to prevent each of the
 * following changes when adding or updating information; display a custom message specific for each error check in the user interface:
 *
 * •  scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
 *
 * •  scheduling overlapping appointments for customers
 *
 * •  entering an incorrect username and password
 *
 *
 *
 * e.  Write code to provide an alert when there is an appointment within 15 minutes of the user’s log-in.
 * A custom message should be displayed in the user interface and include the appointment ID, date, and time.
 * If the user does not have any appointments within 15 minutes of logging in, display a custom message
 * in the user interface indicating there are no upcoming appointments.
 *
 *
 *
 * Note: Since evaluation may be testing your application outside of business hours, your alerts must be robust
 * enough to trigger an appointment within 15 minutes of the local time set on the user’s computer,
 * which may or may not be ET.
 *
 *
 *
 * f.  Write code that generates accurate information in each of the following reports and will display
 * the reports in the user interface:
 *
 *
 *
 * Note: You do not need to save and print the reports to a file or provide a screenshot.
 *
 *
 *
 * •  the total number of customer appointments by type and month
 *
 * •  a schedule for each contact in your organization that includes appointment ID, title, type and description,
 * start date and time, end date and time, and customer ID
 *
 * •  an additional report of your choice that is different from the two other required reports in this prompt and
 * from the user log-in date and time stamp that will be tracked in part C
 */

//public class SchedulingController {
//
//}
public class SchedulingController implements Initializable {
    @FXML
    private TableView<Countries> UserTable;
    @FXML
    private TableColumn<?, ?> ID;
    @FXML
    private TableColumn<?, ?> UserName;
    @FXML
    private TableColumn<?, ?> Password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void show(ActionEvent actionEvent){
        ObservableList<Countries> countryList = CountriesDAO.getAllCountries();
        for(Countries country: countryList){
            System.out.println("Country ID: " + country.getCountryID() + "Name: " + country.getCountryName());
        }
    }
}