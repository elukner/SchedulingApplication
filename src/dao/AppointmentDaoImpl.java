package dao;

import model.Appointments;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl {

    //list is working as a database
    List<Appointments> appointments;

    public AppointmentDaoImpl() {
        appointments = new ArrayList<Appointments>();
        Appointments appointment1 = new Appointments(1, "Title1", "Desc", "2023-01-01", "2023-01-02",
                "2023-01-01", "User1", "2023-01-02", "User2");
        Appointments appointment2 = new Appointments(2, "Title2", "Desc", "2023-01-01", "2023-01-02",
                "2023-01-01", "User1", "2023-01-02", "User2");
        appointments.add(appointment1);
        appointments.add(appointment1);
    }

    /**
     * retrive list of students from the database
     * @return
     */
    public List<Appointments> getAllAppointments() {
        return appointments;
    }

    /**
     *
     * @param appointmentID
     * @return
     */
    public Appointments getAppointment(int appointmentID) {
        return appointments.get(appointmentID);
    }

    /**
     *
     * @param appointment
     */
    public void insertAppointment(Appointments appointment) {
        appointments.get(appointment.getAppointmentID()).setTitle(appointment.getTitle());
        System.out.println("Student: Roll No " + appointment.getAppointmentID() + ", updated in the database");
    }

    /**
     * TODO
     * @param appointment
     */
    public void selectAppointment(Appointments appointment) {

    }

    /**
     * TODO
     * @param appointment
     */
    public void updateAppointment(Appointments appointment) {

    }

    /**
     *
     * @param appointment
     */
    public void deleteAppointment(Appointments appointment) {
        appointments.remove(appointment.getAppointmentID());
        System.out.println("Appointment: Appointment_ID" + appointment.getAppointmentID() + ", deleted from database");
    }
}
