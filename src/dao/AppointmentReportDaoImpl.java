package dao;

import helper.JDBC;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentReport;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentReportDaoImpl {
    public static ObservableList<AppointmentReport> getAppointmentsByTypeAndMonth() {
        ObservableList<AppointmentReport> appointmentReportList = FXCollections.observableArrayList();
        try {
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
                AppointmentReport appointmentReport = new AppointmentReport(month, type,totalAppointments );
                appointmentReportList.add(appointmentReport);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentReportList;
    }

}
