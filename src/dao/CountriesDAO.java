package dao;
/**
 * Project: DAODemo2021
 * Package: sample.DAO
 * <p>
 * User: carolyn.sher
 * Date: 9/15/2021
 * Time: 9:53 AM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *The dao package will hold Utility Classes that handle Database Access.
 *
 * Defining DAO or Abstract classes with static methods
 * that perform CRUD (Create Read Update Delete)
 * or INSERT, SELECT, UPDATE,
 * and DELETE operations is highly recommended. For
 * more info on the DAO pattern
 *
 * These classes interact with the database,
 * perform CRUD operations, and handle data retrieval.
 *
 * Responsibility: The dao package primarily focuses on encapsulating
 * the logic associated with database interactions.
 *
 * Purpose: It contains classes responsible for database-related operations,
 * such as CRUD (Create, Read, Update, Delete)
 * operations, querying the database, and managing
 * data retrieval and storage.
 *
 * Components: Typically includes classes that abstract
 * database operations, handle connections, execute queries,
 * and perform data manipulation.
 *
 * Usage: DAO classes are specific to interacting with your
 * database tables/entities. They map Java objects to
 * database entities and handle the
 * interaction between the application and the database.
 **/

public class CountriesDAO {
    public static void createCountries(){

    }

    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();
        try{
            String sqlStatement = "SELECT * FROM client_schedule.countries";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int countryID=resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                Countries country = new Countries(countryID,countryName);
                countriesList.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countriesList;
    }

    /**
     * public static observableList<Countries></Countries> getalicountrlesof0bservableList<Countr1es clist - FXCollections, observableArraycist();
     * return clist; }
     * public static void checkDateConversion()f
     * Systen,out, printla("CREATE DATE TEST");
     * String sql = "select Create_Date fron countries";
     * $\operatorname{tey} 1$
     * PreparedStatement $p s=$ DBConnection.getConnection ().preparestatement (sql);
     * Resultset rs - ps,executequeryo; I
     * hhile(rs.next $\mathrm{O})$ (
     * Tinestanp ts - rs.getTinestanp( calumblabal: "Create_Dete");
     * Systen.out.println("CD: " + ts.toLocalDateTine().toString());
     * \}
     * f catch (SQLException throwables) f
     * thronables, printstackTrace();
     * \}
     * \}
     */

}
