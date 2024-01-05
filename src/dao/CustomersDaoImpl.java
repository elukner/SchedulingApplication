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

    public static int insertCustomers(int customerID, String customerName, String address,
                                      String postalCode,String phone, String createdBy,
                                      String lastUpdatedBy, int divisionID) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`customers` (`Customer_ID`, `Customer_Name`, `Address`, `Postal_Code`, `Phone`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`, `Division_ID`) VALUES (?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,customerID);
        preparedStatement.setString(2,customerName);
        preparedStatement.setString(3,address);
        preparedStatement.setString(4,postalCode);
        preparedStatement.setString(5,phone);
        preparedStatement.setString(6,createdBy);
        preparedStatement.setString(7,lastUpdatedBy);
        preparedStatement.setInt(8,divisionID);

        return preparedStatement.executeUpdate();
    }


    /**
     *
     * @throws SQLException
     */
    public static void selectCustomers() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.customers";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int customerID=resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String address= resultSet.getString("Address");
            String postalCode= resultSet.getString("Postal_Code");
            String phone= resultSet.getString("Phone");
            String createDate= resultSet.getString("Create_Date");
            String createdBy= resultSet.getString("Created_By");
            String lastUpdate= resultSet.getString("Last_Update");
            String lastUpdatedBy= resultSet.getString("Last_Updated_By");
            int divisionID=resultSet.getInt("Division_ID");
            System.out.print(customerID + " " + " | ");
            System.out.print(customerName + " " + " | ");
            System.out.print(address + " " + " | ");
            System.out.print(postalCode + " " + " | ");
            System.out.print(phone + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.println(divisionID);
        }


    }

    /**
     *
     * @throws SQLException
     */
    public static void selectCustomers(int customerID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.customers WHERE Customer_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1,customerID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            customerID=resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String address= resultSet.getString("Address");
            String postalCode= resultSet.getString("Postal_Code");
            String phone= resultSet.getString("Phone");
            String createDate= resultSet.getString("Create_Date");
            String createdBy= resultSet.getString("Created_By");
            String lastUpdate= resultSet.getString("Last_Update");
            String lastUpdatedBy= resultSet.getString("Last_Updated_By");
            int divisionID=resultSet.getInt("Division_ID");
            System.out.print(customerID + " " + " | ");
            System.out.print(customerName + " " + " | ");
            System.out.print(address + " " + " | ");
            System.out.print(postalCode + " " + " | ");
            System.out.print(phone + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.println(divisionID);
        }


    }

    /**
     *Comment TODO
     * @param customerID
     * @param postalCode
     * @return
     * @throws SQLException
     */
    public static int updateCustomers(int customerID, String postalCode) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`customers` SET `Postal_Code` = ? WHERE (`Customer_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1,postalCode);
        preparedStatement.setInt(2,customerID);


        return preparedStatement.executeUpdate();
    }

    /**
     *Comment TODO
     * @param customerID
     * @return
     * @throws SQLException
     */
    public static int deleteCustomers(int customerID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`customers` WHERE (`Customer_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,customerID);

        return preparedStatement.executeUpdate();
    }

    /**
     * Comment TODO
     * @return
     */
    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers> countriesList = FXCollections.observableArrayList();
        try{
            String sqlStatement = "SELECT * FROM client_schedule.customers";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int customerID=resultSet.getInt("Customer_ID");
                String customerName = resultSet.getString("Customer_Name");
                String address= resultSet.getString("Address");
                String postalCode= resultSet.getString("Postal_Code");
                String phone= resultSet.getString("Phone");
                String createDate= resultSet.getString("Create_Date");
                String createdBy= resultSet.getString("Created_By");
                String lastUpdate= resultSet.getString("Last_Update");
                String lastUpdatedBy= resultSet.getString("Last_Updated_By");
                int divisionID=resultSet.getInt("Division_ID");
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
