package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Appointments;

public class AppointmentsTest {
    @Test
    public void testGettersAndSetters() {

        // Create an instance of Appointments
        Appointments appointment = new Appointments(1, "Title", "Desc", "2023-01-01", "2023-01-02",
                "2023-01-01", "User1", "2023-01-02", "User2");


        // Test getters
        assertEquals(1, appointment.getAppointmentID(), "Appointment ID should match");
        assertEquals("Title", appointment.getTitle(), "Title should match");
        assertEquals("Desc", appointment.getDescription(), "Description should match");
        assertEquals("2023-01-01", appointment.getStart(), "Start date should match");
        assertEquals("2023-01-02", appointment.getEnd(), "End date should match");
        assertEquals("2023-01-01", appointment.getCreateDate(), "Create date should match");
        assertEquals("User1", appointment.getCreatedBy(), "Created by should match");
        assertEquals("2023-01-02", appointment.getLastUpdate(), "Last update date should match");
        assertEquals("User2", appointment.getLastUpdatedBy(), "Last updated by should match");

        // Test setters
        appointment.setAppointmentID(2);
        appointment.setTitle("New Title");
        appointment.setDescription("New Desc");
        appointment.setStart("2024-01-01");
        appointment.setEnd("2024-01-02");
        appointment.setCreateDate("2024-01-01");
        appointment.setCreatedBy("New User");
        appointment.setLastUpdate("2024-01-02");
        appointment.setLastUpdatedBy("New User2");

        assertEquals(2, appointment.getAppointmentID(), "Updated Appointment ID should match");
        assertEquals("New Title", appointment.getTitle(), "Updated Title should match");
        assertEquals("New Desc", appointment.getDescription(), "Updated Description should match");
        assertEquals("2024-01-01", appointment.getStart(), "Updated Start date should match");
        assertEquals("2024-01-02", appointment.getEnd(), "Updated End date should match");
        assertEquals("2024-01-01", appointment.getCreateDate(), "Updated Create date should match");
        assertEquals("New User", appointment.getCreatedBy(), "Updated Created by should match");
        assertEquals("2024-01-02", appointment.getLastUpdate(), "Updated Last update date should match");
        assertEquals("New User2", appointment.getLastUpdatedBy(), "Updated Last updated by should match");
    }
}
