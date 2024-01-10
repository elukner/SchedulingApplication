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
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This utility class FirstLevelDivisionsDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the first_level_divisions table.
 **/
public class FirstLevelDivisionsDaoImpl {
    /**
     * This method inserts a first level division into the first_level_divisions table in the client_schedule database.
     *
     * @param divisionID
     * @param division
     * @param createdBy
     * @param lastUpdatedBy
     * @param countryID
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int insertFirstLevelDivisions(int divisionID, String division, String createdBy, String lastUpdatedBy, int countryID) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`first_level_divisions` (`Division_ID`, `Division`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`, `COUNTRY_ID`) VALUES (?, ?, NOW(), ?, NOW(), ?, ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, divisionID);
        preparedStatement.setString(2, division);
        preparedStatement.setString(3, createdBy);
        preparedStatement.setString(4, lastUpdatedBy);
        preparedStatement.setInt(5, countryID);

        return preparedStatement.executeUpdate();
    }

    /**
     * This method selects first level divisions from the first_level_divisions table in the client_schedule database.
     *
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectFirstLevelDivisions() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int divisionID = resultSet.getInt("Division_ID");
            String division = resultSet.getString("Division");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int countryID = resultSet.getInt("Country_ID");
            System.out.print(divisionID + " " + " | ");
            System.out.print(division + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.println(countryID);
        }


    }

    /**
     * This method selects a first level division from the first_level_divisions table in the client_schedule database.
     *
     * @param divisionID first level division ID to be selected from the first_level_divisions table
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectFirstLevelDivisions(int divisionID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions WHERE Division_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1, divisionID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            divisionID = resultSet.getInt("Division_ID");
            String division = resultSet.getString("Division");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int countryID = resultSet.getInt("Country_ID");
            System.out.print(divisionID + " " + " | ");

            System.out.print(division + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.println(countryID);
        }


    }

    /**
     * This method updates a first level division into the first_level_divisions table in the client_schedule database.
     *
     * @param divisionID
     * @param division
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int updateFirstLevelDivisions(int divisionID, String division) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`first_level_divisions` SET `Division` = ? WHERE (`Division_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1, division);
        preparedStatement.setInt(2, divisionID);


        return preparedStatement.executeUpdate();
    }

    /**
     * This method deletes a first level division into the first_level_divisions table in the client_schedule database.
     *
     * @param divisionID
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int deleteFirstLevelDivisions(int divisionID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`first_level_divisions` WHERE (`Division_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, divisionID);

        return preparedStatement.executeUpdate();
    }

    /**
     * This method retrieves a list of first_level_divisions from the first_level_divisions
     * table in the client_schedule database.
     *
     * @return firstLevelDivisionsList a list of first_level_divisions
     */
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int divisionID = resultSet.getInt("Division_ID");
                String division = resultSet.getString("Division");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int countryID = resultSet.getInt("Country_ID");
                FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, division,
                        createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);
                firstLevelDivisionsList.add(firstLevelDivision);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstLevelDivisionsList;
    }
    //        Country and first-level division data is prepopulated in separate combo boxes or
//        lists in the user interface for the user to choose. The first-level list
//        should be filtered by the user’s
//        selection of a country (e.g., when choosing U.S., filter so it only shows states).

    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisionsFilteredCountry(int countryIDWanted) {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions WHERE COUNTRY_ID = ?";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setInt(1, countryIDWanted);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int divisionID = resultSet.getInt("Division_ID");
                String division = resultSet.getString("Division");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int countryID = resultSet.getInt("Country_ID");
                FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, division,
                        createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);
                firstLevelDivisionsList.add(firstLevelDivision);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstLevelDivisionsList;
    }

    public static ObservableList<FirstLevelDivisions> getFirstLevelDivision(String divisionWanted) {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions WHERE Division = ?";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setString(1, divisionWanted);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int divisionID = resultSet.getInt("Division_ID");
                String division = resultSet.getString("Division");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int countryID = resultSet.getInt("Country_ID");
                FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, division,
                        createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);
                firstLevelDivisionsList.add(firstLevelDivision);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstLevelDivisionsList;
    }
}
