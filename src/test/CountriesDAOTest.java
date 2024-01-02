package test;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import dao.CountriesDAO;
import helper.JDBC;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesDAOTest {

    @Test
    public void testInsertCountries() throws SQLException {
        JDBC.openConnection();
        CountriesDAO.deleteCountries(4);
        int rowsUpdated = CountriesDAO.insertCountries(4,"Finland", "Test User", "Test User" );

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

        int rowsUpdated = CountriesDAO.updateCountries(4,"Finland");

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

        CountriesDAO.selectCountries();
        CountriesDAO.selectCountries(4); //make this into a full test later

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

        int rowsUpdated = CountriesDAO.deleteCountries(4);

        if(rowsUpdated>0){
            System.out.println("Delete Successful");
        }else{
            System.out.println("Delete Failed");
        }

        JDBC.closeConnection();
    }

}
