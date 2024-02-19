package test;

import dao.AppointmentsDaoImpl;
import helper.JDBC;
import javafx.collections.ObservableList;
import model.Appointments;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsDaoImplTest {

    @Test
    void TestGetUpcomingAppointmentWithin15Min() throws SQLException {
        JDBC.openConnection();
        // Arrange
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Act
        ObservableList<Appointments> result = AppointmentsDaoImpl.getUpcomingAppointmentWithin15Min(currentDateTime);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");

        // You can add more specific assertions based on your expected data.
        // For example, check if the start time of each appointment is within the next 15 minutes.
        LocalDateTime fifteenMinutesLater = LocalDateTime.now().plusMinutes(15);
        for (Appointments appointment : result) {
            assertTrue(appointment.getStart().isBefore(fifteenMinutesLater),
                    "Appointment should start within the next 15 minutes");
        }
        JDBC.closeConnection();
    }

    @Test
    public void testHasOverlappingAppointments_NoOverlap() throws SQLException {
        JDBC.openConnection();
        // Arrange
        int customerId = 1;
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2022, 1, 1, 12, 0);

        // Act
        boolean result = AppointmentsDaoImpl.hasOverlappingAppointments(customerId, startDateTime, endDateTime);

        // Assert
        assertFalse(result, "There should be no overlapping appointments");
        JDBC.closeConnection();
    }

    @Test
    public void testHasOverlappingAppointments_Overlap() throws SQLException {
        JDBC.openConnection();
        // Arrange
        int customerId = 1;
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 11, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2022, 1, 1, 13, 0);

        // Act
        boolean result = AppointmentsDaoImpl.hasOverlappingAppointments(customerId, startDateTime, endDateTime);

        // Assert
        assertTrue(result, "There should be overlapping appointments");
        JDBC.closeConnection();
    }

    @Test
    public void testHasOverlappingAppointments_ExcludeAppointment_NoOverlap() throws SQLException {
        JDBC.openConnection();
        // Arrange
        int customerId = 1;
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        int appointmentID = 2; // Assuming an appointment ID to exclude

        // Act
        boolean result = AppointmentsDaoImpl.hasOverlappingAppointments(customerId, startDateTime, endDateTime, appointmentID);

        // Assert
        assertFalse(result, "There should be no overlapping appointments excluding the specified appointment");
        JDBC.closeConnection();
    }

    @Test
    public void testHasOverlappingAppointments_ExcludeAppointment_Overlap() throws SQLException {
        JDBC.openConnection();
        // Arrange
        int customerId = 1;
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 11, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2022, 1, 1, 13, 0);
        int appointmentID = 2; // Assuming an appointment ID to exclude

        // Act
        boolean result = AppointmentsDaoImpl.hasOverlappingAppointments(customerId, startDateTime, endDateTime, appointmentID);

        // Assert
        assertTrue(result, "There should be overlapping appointments excluding the specified appointment");
        JDBC.closeConnection();
    }


    @Test
    void getAllAppointmentsCustomerID() {
    }

    @Test
    void getAllAppointmentsByMonth() {
    }

    @Test
    void getAllAppointmentsByWeek() {
    }

    @Test
    void getAllAppointments() {
    }

    @Test
    void getAllAppointmentsByLocalTimeZones() {
    }

    @Test
    void insertAppointments() {
    }

    @Test
    void selectAppointment() {
    }

    @Test
    void testSelectAppointment() {
    }

    @Test
    void updateAppointment() {
    }

    @Test
    void deleteAppointment() {
    }

    @Test
    void resetAutoIncrement() {
    }
}