package test;

import dao.CountriesDAO;
import helper.JDBC;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CountriesDAOTest {
    @Test
    public void testInsertCountries() throws SQLException {
        JDBC.openConnection();
        //launch(args);

        int rowsUpdated = CountriesDAO.insertCountries(4,"Finland", "Test User", "Test User" );

        if(rowsUpdated>0){
            System.out.println("Insert Successful");
        }else{
            System.out.println("Insert Failed");
        }


        JDBC.closeConnection();
    }

}
