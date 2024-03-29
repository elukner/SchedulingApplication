package dao;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import helper.DateTimeProcessing;
import helper.JDBC;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentReport;
import model.ContactScheduleReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * This class provides data access methods for retrieving contact schedules.
 * The schedules include details such as contact information, appointment details, and customer ID.
 */
public class ContactScheduleReportDaoImpl {

    /**
     * Retrieves a list of contact schedules based on the provided contact ID.
     *
     * @param contactID The ID of the contact for which to retrieve schedules.
     * @return An ObservableList of ContactScheduleReport objects representing the contact schedules.
     */
    public static ObservableList<ContactScheduleReport> getContactSchedules(int contactID) {
        ObservableList<ContactScheduleReport> contactSchedulesList = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT c.Contact_ID, c.Contact_Name, a.Appointment_ID, a.Title, " +
                    "a.Type, a.Description, a.Start, a.End, a.Customer_ID \n" +
                    "FROM client_schedule.contacts c \n" +
                    "LEFT JOIN client_schedule.appointments a ON c.Contact_ID = a.Contact_ID \n" +
                    "WHERE c.Contact_ID = ?;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setInt(1, contactID);

            ResultSet resultSet = preparedStatement.executeQuery();

            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));

            while (resultSet.next()) {
                contactID = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");
                int appointmentID = resultSet.getInt("Appointment_ID");
                if (appointmentID == 0){
                    contactSchedulesList.clear();
                    return contactSchedulesList;
                }
                String title = resultSet.getString("Title");
                String type = resultSet.getString("Type");
                String description = resultSet.getString("Description");
                Timestamp start = Timestamp.valueOf(DateTimeProcessing.convertUTCToLocal(resultSet.getTimestamp( "Start",cal).toLocalDateTime(), ZoneId.systemDefault()));
                Timestamp end = Timestamp.valueOf(DateTimeProcessing.convertUTCToLocal(resultSet.getTimestamp( "End",cal).toLocalDateTime(),ZoneId.systemDefault()));
                int customerID = resultSet.getInt("Customer_ID");

                ContactScheduleReport contactScheduleReport = new ContactScheduleReport(new ReadOnlyIntegerWrapper(contactID),
                        new ReadOnlyStringWrapper(contactName),
                        new ReadOnlyIntegerWrapper(appointmentID),title,type,description,start.toLocalDateTime(),
                        end.toLocalDateTime(),new ReadOnlyIntegerWrapper(customerID) );
                contactSchedulesList.add(contactScheduleReport);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactSchedulesList;
    }
}
