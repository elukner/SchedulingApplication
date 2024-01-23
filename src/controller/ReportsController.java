package controller;

import dao.AppointmentReportDaoImpl;
import dao.AppointmentsDaoImpl;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.AppointmentReport;
import model.Appointments;

public class ReportsController implements Initializable {

    @FXML // fx:id="contactComboBox"
    private ComboBox<String> contactComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="report1TableView"
    private TableView<AppointmentReport> report1TableView; // Value injected by FXMLLoader

    @FXML // fx:id="report2TableView"
    private TableView<Appointments> report2TableView; // Value injected by FXMLLoader

    @FXML // fx:id="report3TableView"
    private TableView<Appointments> report3TableView; // Value injected by FXMLLoader

    @FXML // fx:id="monthCol"
    private TableColumn<AppointmentReport, String> monthCol; // Value injected by FXMLLoader

    @FXML // fx:id="totalAppointmentsCol"
    private TableColumn<AppointmentReport, Integer> totalAppointmentsCol; // Value injected by FXMLLoader

    @FXML // fx:id="typeCol"
    private TableColumn<AppointmentReport, String> typeCol; // Value injected by FXMLLoader

   // private ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();


    private Stage stage;
    private Parent scene;

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

    @FXML
    void onActionRunReport2(ActionEvent event) {
        loadReport2();

    }

    @FXML
    void onSelectContact(ActionEvent event) {

    }

    /**
     * Loads the data for the first report into the specified TableView.
     * The method populates the TableView with appointment data retrieved from the AppointmentReportDaoImpl,
     * displaying the total number of customer appointments categorized by type and month.
     *
     * It sets up the necessary cell value factories for the 'Month', 'Type', and 'Total Appointments' columns
     * in the report1TableView. The 'Month' and 'Type' columns are populated directly from the data,
     * while the 'Total Appointments' column is calculated using the getTotalAppointments() method of the data model.
     *
     * Note: Ensure that the report1TableView, monthCol, typeCol, and totalAppointmentsCol are initialized
     * before calling this method to avoid NullPointerException.
     */
    private void loadReport1(){

        report1TableView.setItems(FXCollections.observableArrayList(AppointmentReportDaoImpl.getAppointmentsByTypeAndMonth()));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        totalAppointmentsCol.setCellValueFactory(cellDataFeatures -> {
            return cellDataFeatures.getValue().getTotalAppointments().asObject();
        });

    }

    /**
     * TODO a schedule for each contact in your organization that includes appointment ID, title, type and description,
     *  start date and time, end date and time, and customer ID
     */
    private void loadReport2(){


        //     SELECT a.appointment_id, a.title, a.appointment_type, a.description, a.start_date, a.end_date,
//         a.customer_id FROM appointments_table a JOIN contacts_table c ON a.contact_id = c.contact_id;

    }

    /**
     * TODO an additional report of your choice that is different from the two other required reports in this prompt
     *  and from the user log-in date and time stamp that will be tracked in part C
     */
    private void loadReport3(){


//        This report could provide a summary of appointments grouped by the users who created or managed them.
//        The report might include information such as:
//•	User names or IDs
//•	Total number of appointments created by each user
//•	Average or total duration of appointments created by each user
//•	The distribution of appointment types created by each user
//        This report offers insights into the appointment-related activities of different users within the system.
//        It complements the previous reports by focusing on the users' contributions to the appointment scheduling process.
//        The simplicity of this report lies in its straightforward nature and its alignment with the existing data
//        structure related to appointments and users in the application.

//        SELECT
//        u.user_id,
//                u.username,
//                COUNT(a.appointment_id) AS total_appointments,
//        AVG(TIMESTAMPDIFF(MINUTE, a.start_date_time, a.end_date_time)) AS average_duration
//        FROM
//        users u
//        LEFT JOIN
//        appointments a ON u.user_id = a.user_id
//        GROUP BY
//        u.user_id, u.username
//        ORDER BY
//        total_appointments DESC;


//        u.user_id, u.username: Selects the user ID and username from the users table.
//        COUNT(a.appointment_id) AS total_appointments: Counts the total number of appointments for each user.
//        AVG(TIMESTAMPDIFF(MINUTE, a.start_date_time, a.end_date_time)) AS average_duration: Calculates the average duration of appointments for each user in minutes. Adjust the time units based on your preference.
//        FROM users u: Specifies the main table as users and aliases it as u.
//                LEFT JOIN appointments a ON u.user_id = a.user_id: Performs a left join between the users and appointments tables based on the user_id foreign key.
//        GROUP BY u.user_id, u.username: Groups the results by user ID and username.
//        ORDER BY total_appointments DESC: Orders the results by the total number of appointments in descending order.

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadReport1();
        loadReport3();
    }
}
