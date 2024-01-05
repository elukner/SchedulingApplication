package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

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

public class CustomersDaoImpl {

    public static int insertCustomers(int countryID, String country, String createdBy, String lastUpdatedBy) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`countries` (`Country_ID`, `Country`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`) VALUES (?, ?, NOW(), ?, NOW(), ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,countryID);
        preparedStatement.setString(2,country);
        preparedStatement.setString(3,createdBy);
        preparedStatement.setString(4,lastUpdatedBy);

        return preparedStatement.executeUpdate();
    }


    /**
     *
     * @throws SQLException
     */
    public static void selectCustomers() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.countries";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int countryID = resultSet.getInt("Country_ID");
            String country = resultSet.getString("Country");
            System.out.print(countryID + " " + " | ");
            System.out.println(country);
        }


    }

    /**
     *
     * @throws SQLException
     */
    public static void selectCustomers(int countryID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.countries WHERE Country_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1,countryID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            countryID = resultSet.getInt("Country_ID");
            String country = resultSet.getString("Country");

            System.out.print(countryID + " " + " | ");
            System.out.println(country);
        }


    }

    /**
     *
     * @param countryID
     * @param country
     * @return
     * @throws SQLException
     */
    public static int updateCustomers(int countryID, String country) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`countries` SET `Country` = ? WHERE (`Country_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1,country);
        preparedStatement.setInt(2,countryID);


        return preparedStatement.executeUpdate();
    }

    /**
     *
     * @param countryID
     * @return
     * @throws SQLException
     */
    public static int deleteCustomers(int countryID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`countries` WHERE (`Country_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,countryID);

        return preparedStatement.executeUpdate();
    }

    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers> countriesList = FXCollections.observableArrayList();
        try{
            String sqlStatement = "SELECT * FROM client_schedule.countries";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int customerID=resultSet.getInt("Country_ID");
                String customerName = resultSet.getString("Country");
                String address= resultSet.getString("Country");
                String postalCode= resultSet.getString("Country");
                String phone= resultSet.getString("Country");
                String createDate= resultSet.getString("Country");
                String createdBy= resultSet.getString("Country");
                String lastUpdate= resultSet.getString("Country");
                String lastUpdatedBy= resultSet.getString("Country");
                int divisionID=resultSet.getInt("Country_ID");
                Customers customer = new Customers(customerID, customerName,
                        address, postalCode, phone,
                        createDate, createdBy,
                        lastUpdate, lastUpdatedBy,
                        divisionID);
                countriesList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countriesList;
    }
}
