package dao;

import helper.JDBC;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ContactScheduleReport;
import model.UserAppointmentReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAppointmentReportDaoImpl {
    public static ObservableList<UserAppointmentReport> getUserAppointmentSummary(int userID) {
        ObservableList<UserAppointmentReport> userAppointmentReports = FXCollections.observableArrayList();

        try {
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

            while (resultSet.next()) {
                userID = resultSet.getInt("User_ID");
                String userName = resultSet.getString("User_Name");
                int totalAppointments = resultSet.getInt("Total_Appointments");
                double averageDuration = resultSet.getDouble("Average_Duration");

                UserAppointmentReport userAppointmentReport =
                        new UserAppointmentReport(new ReadOnlyIntegerWrapper(userID), new ReadOnlyStringWrapper(userName),
                                totalAppointments, averageDuration);

                userAppointmentReports.add(userAppointmentReport);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userAppointmentReports;
    }
}
