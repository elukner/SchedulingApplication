package test;

import dao.AppointmentsDaoImpl;
import helper.JDBC;
import javafx.collections.ObservableList;
import model.Appointments;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeProcessingTest {

    @Test
    void convertUTCToLocal() {
        JDBC.openConnection();
        ObservableList<Appointments> appointmentsList = AppointmentsDaoImpl.getAllAppointmentsByLocalTimeZones();

        for (Appointments appointment : appointmentsList) {
            int appointmentID = appointment.getAppointmentID();
            LocalDateTime startTime = appointment.getStart();
            LocalDateTime endTime = appointment.getEnd();

//            System.out.println("Appointment ID: " + appointmentID);
//            System.out.println("Start Time: " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            System.out.println("End Time: " + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            System.out.println("-----");
        }

        JDBC.closeConnection();
    }
}