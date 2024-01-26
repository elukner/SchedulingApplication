package dao;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import helper.JDBC;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The AppointmentReportDaoImpl class provides methods to retrieve appointment-related reports
 * from the database, specifically focusing on the number of appointments by type and month.
 */
public class AppointmentReportDaoImpl {
    /**
     * Retrieves a list of appointment reports containing information about the total number of appointments
     * grouped by type and month.
     *
     * @return An {@code ObservableList} of {@code AppointmentReport} objects, each representing the total
     *         appointments for a specific type and month.
     */
    public static ObservableList<AppointmentReport> getAppointmentsByTypeAndMonth() {
        ObservableList<AppointmentReport> appointmentReportList = FXCollections.observableArrayList();
        try {
            // SQL query to retrieve the total number of appointments grouped by type and month
            String sqlStatement = "        SELECT\n" +
                    "        MONTH(`Start`) AS Month,\n" +
                    "    `Type`,\n" +
                    "        COUNT(*) AS Total_Appointments\n" +
                    "        FROM\n" +
                    "        client_schedule.appointments\n" +
                    "        GROUP BY\n" +
                    "        MONTH(`Start`),\n" +
                    "    `Type`;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String month = resultSet.getString("Month");
                String type = resultSet.getString("Type");
                int totalAppointments = resultSet.getInt("Total_Appointments");
                AppointmentReport appointmentReport = new AppointmentReport(month, type, new ReadOnlyIntegerWrapper(totalAppointments) );
                appointmentReportList.add(appointmentReport);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentReportList;
    }

}
