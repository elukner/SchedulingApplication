package test;

import dao.AppointmentDaoImpl;
import dao.CountriesDAOImpl;
import helper.JDBC;
import model.Appointments;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DaoTest {
    @Test
    public void testAppointmentDaoImpl() {
        AppointmentDaoImpl appointmentsDaoImpl = new AppointmentDaoImpl();

        //print all appointments
        for (Appointments appointments : appointmentsDaoImpl.getAllAppointments()) {
            System.out.println("Appointments: [Appointment_ID : " + appointments.getAppointmentID() + ", Name : " + appointments.getTitle() + " ]");
        }


        //update student
        Appointments appointments = appointmentsDaoImpl.getAllAppointments().get(0);
        appointments.setTitle("Michael");
        appointmentsDaoImpl.updateAppointment(appointments);

        //get the student
        appointmentsDaoImpl.getAppointment(0);
        System.out.println("Student: [RollNo : " + appointments.getAppointmentID() + ", Name : " + appointments.getTitle() + " ]");
    }


    @Test
    public void testInsertCountries() throws SQLException {
        JDBC.openConnection();
        CountriesDAOImpl.deleteCountries(4);
        int rowsUpdated = CountriesDAOImpl.insertCountries(4,"Finland", "Test User", "Test User" );

        if(rowsUpdated>0){
            System.out.println("Insert Successful");
        }else{
            System.out.println("Insert Failed");
        }

        JDBC.closeConnection();
    }

    @Test
    public void testUpdateCountries() throws SQLException {
        JDBC.openConnection();

        int rowsUpdated = CountriesDAOImpl.updateCountries(4,"Finland");

        if(rowsUpdated>0){
            System.out.println("Update Successful");
        }else{
            System.out.println("Update Failed");
        }

        JDBC.closeConnection();
    }

    @Test
    public void testSelectCountries() throws SQLException {
        JDBC.openConnection();

        CountriesDAOImpl.selectCountries();
        CountriesDAOImpl.selectCountries(4); //make this into a full test later

//        if(CountriesDAO.selectCountries.getFetchSize()>0){
//            for (int i = 0;i<rowsUpdated.getFetchSize();i++ ) {
//                System.out.println(rowsUpdated.next());
//            }
//
//        }else{
//            System.out.println("Select Failed");
//        }

        JDBC.closeConnection();
    }

    @Test
    public void testDeleteCountries() throws SQLException {
        JDBC.openConnection();

        int rowsUpdated = CountriesDAOImpl.deleteCountries(4);

        if(rowsUpdated>0){
            System.out.println("Delete Successful");
        }else{
            System.out.println("Delete Failed");
        }

        JDBC.closeConnection();
    }
}
