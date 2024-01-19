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
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This utility class CustomersDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the customers table.
 **/
public class CustomersDaoImpl {

    /**
     * This method inserts a customer into the customers table in the client_schedule database.
     *
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createdBy
     * @param lastUpdatedBy
     * @param divisionID
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int insertCustomers(int customerID, String customerName, String address,
                                      String postalCode, String phone, String createdDate, String createdBy, String lastUpdated,
                                      String lastUpdatedBy, int divisionID) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`customers` (`Customer_ID`, `Customer_Name`, `Address`, `Postal_Code`, `Phone`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`, `Division_ID`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, customerID);
        preparedStatement.setString(2, customerName);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, postalCode);
        preparedStatement.setString(5, phone);
        preparedStatement.setString(6, createdDate);
        preparedStatement.setString(7, createdBy);
        preparedStatement.setString(8, lastUpdated);
        preparedStatement.setString(9, lastUpdatedBy);
        preparedStatement.setInt(10, divisionID);

        return preparedStatement.executeUpdate();
    }


    /**
     * This method selects customers from the customers table in the client_schedule database.
     *
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectCustomers() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.customers";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int customerID = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int divisionID = resultSet.getInt("Division_ID");
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
     * This method selects a customer from the customers table in the client_schedule database.
     *
     * @param customerID customer ID to be selected from the customers table
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectCustomers(int customerID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.customers WHERE Customer_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1, customerID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            customerID = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int divisionID = resultSet.getInt("Division_ID");
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
     * This method updates a customer into the customers table in the client_schedule database.
     *
     * @param customerID
     * @param postalCode
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int updateCustomers(int customerID, String customerName, String address,
                                      String postalCode, String phone,String lastUpdated,
                                      String lastUpdatedBy, int divisionID) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`customers` SET `Customer_Name` = ?, `Address` = ?, `Postal_Code` = ?, `Phone` = ?, `Last_Update` = ?, `Last_Updated_By` = ?, `Division_ID` = ? WHERE (`Customer_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setString(5, lastUpdated);
        preparedStatement.setString(6, lastUpdatedBy);
        preparedStatement.setInt(7, divisionID);
        preparedStatement.setInt(8, customerID);

        return preparedStatement.executeUpdate();
    }

    /**
     * This method deletes a customer into the customers table in the client_schedule database.
     *
     * @param customerID
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int deleteCustomers(int customerID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`customers` WHERE (`Customer_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, customerID);

        return preparedStatement.executeUpdate();
    }

    /**
     * This method retrieves a list of customers from the customers table in the client_schedule database.
     *
     * @return customersList a list of customers
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.customers";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int customerID = resultSet.getInt("Customer_ID");
                String customerName = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phone = resultSet.getString("Phone");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int divisionID = resultSet.getInt("Division_ID");
                Customers customer = new Customers(customerID, customerName,
                        address, postalCode, phone,
                        createDate, createdBy,
                        lastUpdate, lastUpdatedBy,
                        divisionID);
                customersList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersList;
    }

    /**
     * This method retrieves a list of customers from the customers table in the client_schedule database.
     *
     * @return customersList a list of customers
     */
    public static ObservableList<Customers> getAllCustomersForTble() {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.customers";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int customerID = resultSet.getInt("Customer_ID");
                String customerName = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phone = resultSet.getString("Phone");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int divisionID = resultSet.getInt("Division_ID");
                Customers customer = new Customers(customerID, customerName,
                        address, postalCode, phone,
                        lastUpdate, lastUpdatedBy,
                        divisionID);
                customersList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersList;
    }

    /**
     * This method retrieves a list of customers from the customers table in the client_schedule database.
     *
     * @return customersList a list of customers
     */
    public static ObservableList<Customers> getAllCustomersAndCountriesForTble() {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT cu.*, c.Country \n" +
                    "FROM client_schedule.customers cu \n" +
                    "JOIN client_schedule.first_level_divisions d ON cu.Division_ID = d.Division_ID\n" +
                    "JOIN client_schedule.countries c ON d.Country_ID = c.Country_ID;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int customerID = resultSet.getInt("Customer_ID");
                String customerName = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phone = resultSet.getString("Phone");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int divisionID = resultSet.getInt("Division_ID");
                String countryName = resultSet.getString("Country");
                Customers customer = new Customers(customerID, customerName,
                        address, postalCode, phone,
                        lastUpdate, lastUpdatedBy,
                        divisionID,countryName);
                customersList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersList;
    }
}
