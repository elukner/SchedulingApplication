package test;

import dao.AppointmentsDaoImpl;
import helper.JDBC;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DaoTest {
    @Test
    void getUpcomingAppointmentWithin15Min() {
    }

    @Test
    void testHasOverlappingAppointments() throws SQLException {
        JDBC.openConnection();
//      boolean hasOverlapped = AppointmentsDaoImpl.hasOverlappingAppointments(2,
//               LocalDateTime.parse("2024-02-01 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//               LocalDateTime.parse("2024-02-01 17:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        boolean hasOverlapped2 = AppointmentsDaoImpl.hasOverlappingAppointmentsTemp(1,
                LocalDateTime.parse("2020-05-29 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2020-05-29 13:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

       System.out.println(hasOverlapped2);
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
