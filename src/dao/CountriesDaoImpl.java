package dao;
/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This utility class CountriesDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the countries table.
 **/
public class CountriesDaoImpl {

    /**
     * This method is used to insert new records into the Countries table from client_schedule database.
     *
     * @param countryID       The ID of the country.
     * @param country         The name of the country.
     * @param createdBy       The user who created the record.
     * @param lastUpdatedBy   The user who last updated the record.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int insertCountries(int countryID, String country, String createdBy, String lastUpdatedBy) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`countries` (`Country_ID`, `Country`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`) VALUES (?, ?, NOW(), ?, NOW(), ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, countryID);
        preparedStatement.setString(2, country);
        preparedStatement.setString(3, createdBy);
        preparedStatement.setString(4, lastUpdatedBy);

        return preparedStatement.executeUpdate();
    }


    /**
     * This method selects countries from the countries table in the client_schedule database.
     *
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectCountries() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.countries";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int countryID = resultSet.getInt("Country_ID");
            String country = resultSet.getString("Country");
            System.out.print(countryID + " " + " | ");
            System.out.println(country);
        }


    }

    /**
     * This method selects a country from the countries table in the client_schedule database.
     *
     * @param countryID country ID to be selected from the countries table
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectCountries(int countryID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.countries WHERE Country_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1, countryID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            countryID = resultSet.getInt("Country_ID");
            String country = resultSet.getString("Country");

            System.out.print(countryID + " " + " | ");
            System.out.println(country);
        }


    }

    /**
     * This method is used to update new records into the countries table from client_schedule database.
     *
     * @param countryID       The ID of the country.
     * @param country         The name of the country.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int updateCountries(int countryID, String country) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`countries` SET `Country` = ? WHERE (`Country_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1, country);
        preparedStatement.setInt(2, countryID);


        return preparedStatement.executeUpdate();
    }

    /**
     * This method is used to delete new records into the countries table from client_schedule database.
     *
     * @param countryID       The ID of the country.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int deleteCountries(int countryID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`countries` WHERE (`Country_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, countryID);

        return preparedStatement.executeUpdate();
    }

    /**
     * This method retrieves a list of countries from the countries table in the client_schedule database.
     *
     * @return countriesList a list of countries
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.countries";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int countryID = resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                Countries country = new Countries(countryID, countryName);
                countriesList.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countriesList;
    }

    /**
     * Retrieves a list of countries based on the specified country name.
     *
     * @param countryWanted The name of the country to retrieve.
     * @return An ObservableList of Countries that match the specified country name.
     */
    public static ObservableList<Countries> getAllCountries(String countryWanted) {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.countries WHERE Country = ?";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setString(1, countryWanted);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int countryID = resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                Countries country = new Countries(countryID, countryName);
                countriesList.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countriesList;
    }


}
