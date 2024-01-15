package test;

import dao.*;

import helper.JDBC;
import model.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DaoTest {
    @Test
    public void testAppointmentDaoImpl() {
        AppointmentsDaoImpl appointmentsDaoImpl = new AppointmentsDaoImpl();




        //print all appointments
        for (Appointments appointments : appointmentsDaoImpl.getAllAppointments()) {
            System.out.println("Appointments: [Appointment_ID : " + appointments.getAppointmentID() + ", Name : " + appointments.getTitle() + " ]");
        }




//        //update student
//        Appointments appointments = appointmentsDaoImpl.getAllAppointments().get(0);
//        appointments.setTitle("Michael");
//        appointmentsDaoImpl.updateAppointment(appointments);
//
//        //get the student
//        appointmentsDaoImpl.getAppointment(0);
//        System.out.println("Student: [RollNo : " + appointments.getAppointmentID() + ", Name : " + appointments.getTitle() + " ]");
    }

    @Test
    public void testContactsDaoImpl() throws SQLException{
        JDBC.openConnection();
        for (Contacts contact : ContactsDaoImpl.getAllContacts()) {
            System.out.println("Appointments: [Appointment_ID : " + contact.getContactID() + ", Name : " + contact.getContactName() + ", Email : " + contact.getEmail() + " ]");
        }
        JDBC.closeConnection();
    }

    @Test
    public void testInsertCountries() throws SQLException {
        JDBC.openConnection();
        CountriesDaoImpl.deleteCountries(4);
        int rowsUpdated = CountriesDaoImpl.insertCountries(4,"Finland", "Test User", "Test User" );

        if(rowsUpdated>0){
            System.out.println("Insert Successful");
        }else{
            System.out.println("Insert Failed");
        }
        CountriesDaoImpl.deleteCountries(4);
        JDBC.closeConnection();
    }
    @Test
    public void testInsertContacts() throws SQLException {
        JDBC.openConnection();
        ContactsDaoImpl.deleteContact(4);
        int rowsUpdated = ContactsDaoImpl.insertContact(4,"John Doe", "jdoe@company.com");

        if(rowsUpdated>0){
            System.out.println("Insert Successful");
        }else{
            System.out.println("Insert Failed");
        }
        ContactsDaoImpl.deleteContact(4);
        JDBC.closeConnection();
    }

    @Test
    public void testUpdateContacts() throws SQLException {
        JDBC.openConnection();
        ContactsDaoImpl.deleteContact(4);
        ContactsDaoImpl.insertContact(4,"John Doe", "jdoe@company.com");
        int rowsUpdated = ContactsDaoImpl.updateContact(4,"John Foe");

        if(rowsUpdated>3){
            System.out.println("Update Successful");
        }else{
            System.out.println("Update Failed");
        }
        ContactsDaoImpl.deleteContact(4);
        JDBC.closeConnection();
    }
    @Test
    public void testUpdateCountries() throws SQLException {
        JDBC.openConnection();

        int rowsUpdated = CountriesDaoImpl.updateCountries(4,"Finland");

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

        CountriesDaoImpl.selectCountries();
        CountriesDaoImpl.selectCountries(4); //make this into a full test later

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
    public void testSelectContacts() throws SQLException {
        JDBC.openConnection();

        ContactsDaoImpl.selectContact();
        ContactsDaoImpl.selectContact(3); //make this into a full test later

        JDBC.closeConnection();
    }

    @Test
    public void testDeleteCountries() throws SQLException {
        JDBC.openConnection();

        int rowsUpdated = CountriesDaoImpl.deleteCountries(4);

        if(rowsUpdated>0){
            System.out.println("Delete Successful");
        }else{
            System.out.println("Delete Failed");
        }

        JDBC.closeConnection();
    }

    @Test
    public void testDeleteContacts() throws SQLException {
        JDBC.openConnection();

        int rowsUpdated = ContactsDaoImpl.deleteContact(4);

        if(rowsUpdated>0){
            System.out.println("Delete Successful");
        }else{
            System.out.println("Delete Failed");
        }

        JDBC.closeConnection();
    }

    @Test
    public void testAppointmentsDaoImpl() throws SQLException {
        JDBC.openConnection();
        for (Appointments appointments : AppointmentsDaoImpl.getAllAppointments()) {
            System.out.println("Appointments: [Appointment_ID : " + appointments.getAppointmentID() + ", Name : " + appointments.getTitle() + " ]");
        }
        AppointmentsDaoImpl.deleteAppointment(3);
        //AppointmentsDaoImpl.insertAppointments(3, "title", "description", "location", "Planning Session", "2020-05-28 12:00:00", "2020-05-28 13:00:00", "script", "script", 1, 1, 3);
      AppointmentsDaoImpl.selectAppointment();
        AppointmentsDaoImpl.selectAppointment(3);
        //AppointmentsDaoImpl.updateAppointment(3,"title 2.0");
        AppointmentsDaoImpl.deleteAppointment(3);
//        int rowsUpdated = AppointmentDaoImpl.deleteAppointment(3);
//
//        if(rowsUpdated>0){
//            System.out.println("Delete Successful");
//        }else{
//            System.out.println("Delete Failed");
//        }

        JDBC.closeConnection();
    }
    @Test
    public void testCustomersDaoImpl() throws SQLException {
        JDBC.openConnection();
        for (Customers customer : CustomersDaoImpl.getAllCustomers()) {
            System.out.println("Customers: [Customer_ID : " + customer.getCustomerID() + ", Name : " + customer.getCustomerName() + " ]");
        }
        CustomersDaoImpl.deleteCustomers(4);
        //CustomersDaoImpl.insertCustomers(4, "Dudley Do-Right", "48 Horse Manor ", "28198", "874-916-2671", "script", "script", 60);
        CustomersDaoImpl.selectCustomers();
        CustomersDaoImpl.selectCustomers(4);
      //  CustomersDaoImpl.updateCustomers(4,"title 2.0");
        CustomersDaoImpl.deleteCustomers(4);

//        int rowsUpdated = AppointmentDaoImpl.deleteAppointment(3);
//
//        if(rowsUpdated>0){
//            System.out.println("Delete Successful");
//        }else{
//            System.out.println("Delete Failed");
//        }

        JDBC.closeConnection();
    }
    @Test
    public void testFirstLevelDivisionsDaoImpl() throws SQLException {
        JDBC.openConnection();
        for (FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisions()) {
            System.out.println("Customers: [Customer_ID : " + firstLevelDivision.getDivisionID() + ", Name : " + firstLevelDivision.getDivision() + " ]");
        }
        FirstLevelDivisionsDaoImpl.deleteFirstLevelDivisions(105);
        FirstLevelDivisionsDaoImpl.insertFirstLevelDivisions(105, "Alberta", "script","script", 3 );
        FirstLevelDivisionsDaoImpl.selectFirstLevelDivisions();
        FirstLevelDivisionsDaoImpl.selectFirstLevelDivisions(105);
        FirstLevelDivisionsDaoImpl.updateFirstLevelDivisions(105,"title 2.0");
      FirstLevelDivisionsDaoImpl.deleteFirstLevelDivisions(105);

//        int rowsUpdated = AppointmentDaoImpl.deleteAppointment(3);
//
//        if(rowsUpdated>0){
//            System.out.println("Delete Successful");
//        }else{
//            System.out.println("Delete Failed");
//        }

        JDBC.closeConnection();
    }

    @Test
    public void testUsersDaoImpl() throws SQLException {
        JDBC.openConnection();
        for (Users user : UsersDaoImpl.getAllUsers()) {
            System.out.println("Customers: [Customer_ID : " + user.getUserID() + ", Name : " + user.getUserName() + " ]");
        }
        UsersDaoImpl.deleteUsers(3);
        UsersDaoImpl.insertUsers(3, "tester", "test", "script","script" );
        UsersDaoImpl.selectUsers();
        UsersDaoImpl.selectUsers(3);
        UsersDaoImpl.updateUsers(3,"title 2.0");
        UsersDaoImpl.deleteUsers(3);

//        int rowsUpdated = AppointmentDaoImpl.deleteAppointment(3);
//
//        if(rowsUpdated>0){
//            System.out.println("Delete Successful");
//        }else{
//            System.out.println("Delete Failed");
//        }

        JDBC.closeConnection();
    }
}
