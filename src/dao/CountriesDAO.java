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

/**
 *
 */
public class CountriesDAO {

    /**
     * This method is used to insert new records into the Countries table from client_schedule database
     * @param countryID
     * @param country
     * @param createdBy
     * @param lastUpdatedBy
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     * closed PreparedStatement or the SQL statement returns a ResultSet object
     * java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     * that was specified by the setQueryTimeout method has been exceeded and
     * has at least attempted to cancel the currently running Statement
     */
    public static int insertCountries(int countryID, String country, String createdBy, String lastUpdatedBy) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`countries` (`Country_ID`, `Country`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`) VALUES (?, ?, NOW(), ?, NOW(), ?);";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1,countryID);
        preparedStatement.setString(2,country);
        preparedStatement.setString(3,createdBy);
        preparedStatement.setString(4,lastUpdatedBy);

        //returns rows that were updated
        return preparedStatement.executeUpdate();
    }

    public static boolean selectCountries() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.countries";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        return preparedStatement.execute();
    }

    public static int updateCountries(String countryName, int countryID){
        //UPDATE `client_schedule`.`countries` SET `Country` = 'Poland' WHERE (`Country_ID` = '4');
        return countryID;
    }

    public static int deleteCountries(String countryName, int countryID){
        //DELETE FROM `client_schedule`.`countries` WHERE (`Country_ID` = '4');
        return countryID;
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
