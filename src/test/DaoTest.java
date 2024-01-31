package test;

import dao.*;

import helper.JDBC;
import model.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DaoTest {
    @Test
    public void testCustomersDaoImpl() throws SQLException {
        JDBC.openConnection();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        CustomersDaoImpl.insertCustomers("Dudley Test", "48 Horse Manor ", "28198", "874-916-2671",
                dateTimeFormatter.format(LocalDateTime.now()),"script",dateTimeFormatter.format(LocalDateTime.now()), "script", 60);

        for (Customers customer : CustomersDaoImpl.getAllCustomers()) {
            System.out.println("Customers: [Customer_ID : " + customer.getCustomerID() + ", Name : " + customer.getCustomerName() + " ]");
        }


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
