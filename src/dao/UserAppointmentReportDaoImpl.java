package dao;

import helper.FileIOManager;
import helper.JDBC;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ContactScheduleReport;
import model.UserAppointmentReport;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UserAppointmentReportDaoImpl class provides data access methods for retrieving user appointment reports from the database.
 * It includes methods to fetch appointment summaries with different parameters.
 */
public class UserAppointmentReportDaoImpl {

    /**
     * Retrieves a summary of user appointments based on the specified user ID.
     *
     * @param userID The unique identifier for the user.
     * @return An ObservableList containing UserAppointmentReport objects representing the summary of user appointments.
     */
    public static ObservableList<UserAppointmentReport> getUserAppointmentSummary(int userID) {
        ObservableList<UserAppointmentReport> userAppointmentReports = FXCollections.observableArrayList();

        try {
            // SQL query to fetch user appointment summary
            String sqlStatement = "SELECT u.User_ID, u.User_Name, COUNT(a.Appointment_ID) AS Total_Appointments, AVG(TIMESTAMPDIFF(MINUTE, a.Start, a.End)) AS Average_Duration\n" +
                    "FROM\n" +
                    "client_schedule.users u\n" +
                    "LEFT JOIN\n" +
                    "client_schedule.appointments a ON u.User_ID = a.User_ID\n" +
                    "WHERE u.User_ID = ?\n" +
                    "GROUP BY\n" +
                    "u.User_ID, u.User_Name\n" +
                    "ORDER BY\n" +
                    "Total_Appointments;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            // Process the result set and create UserAppointmentReport objects
            while (resultSet.next()) {
                userID = resultSet.getInt("User_ID");
                String userName = resultSet.getString("User_Name");
                int totalAppointments = resultSet.getInt("Total_Appointments");
                double averageDuration = resultSet.getDouble("Average_Duration");

                UserAppointmentReport userAppointmentReport =
                        new UserAppointmentReport(new ReadOnlyIntegerWrapper(userID), new ReadOnlyStringWrapper(userName),
                              new ReadOnlyIntegerWrapper(totalAppointments), new ReadOnlyDoubleWrapper(averageDuration));

                userAppointmentReports.add(userAppointmentReport);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userAppointmentReports;
    }
    /**
     * Retrieves a summary of user appointments with additional user date and timestamp information.
     *
     * @param userID The unique identifier for the user.
     * @return An ObservableList containing UserAppointmentReport objects with user date and timestamp information.
     */
    public static ObservableList<UserAppointmentReport> getUserAppointmentSummaryWithUserDateTimestamp(int userID) {
        ObservableList<UserAppointmentReport> userAppointmentReports = FXCollections.observableArrayList();

        try {
            // SQL query to fetch user appointment summary
            String sqlStatement = "SELECT u.User_ID, u.User_Name, COUNT(a.Appointment_ID) AS Total_Appointments, AVG(TIMESTAMPDIFF(MINUTE, a.Start, a.End)) AS Average_Duration\n" +
                    "FROM\n" +
                    "client_schedule.users u\n" +
                    "LEFT JOIN\n" +
                    "client_schedule.appointments a ON u.User_ID = a.User_ID\n" +
                    "WHERE u.User_ID = ?\n" +
                    "GROUP BY\n" +
                    "u.User_ID, u.User_Name\n" +
                    "ORDER BY\n" +
                    "Total_Appointments;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            // Process the result set and create UserAppointmentReport objects
            while (resultSet.next()) {
                userID = resultSet.getInt("User_ID");
                String userName = resultSet.getString("User_Name");
                int totalAppointments = resultSet.getInt("Total_Appointments");
                double averageDuration = resultSet.getDouble("Average_Duration");

                UserAppointmentReport userAppointmentReport =
                        new UserAppointmentReport(new ReadOnlyIntegerWrapper(userID), new ReadOnlyStringWrapper(userName),
                                new ReadOnlyStringWrapper(FileIOManager.readFileCurrentUserDateAndTimestamp()),
                                new ReadOnlyIntegerWrapper(totalAppointments), new ReadOnlyDoubleWrapper(averageDuration));

                userAppointmentReports.add(userAppointmentReport);
            }
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return userAppointmentReports;
    }
}
