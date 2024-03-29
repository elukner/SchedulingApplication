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
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This utility class ContactsDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the contacts table.
 **/
public class ContactsDaoImpl {

    /**
     * This method retrieves a list of contacts from the contacts table in the client_schedule database.
     *
     * @return contactsList a list of contacts
     */
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.contacts";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int contactID = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");
                String email = resultSet.getString("Email");
                Contacts contact = new Contacts(contactID, contactName, email);
                contactsList.add(contact);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactsList;


    }

    /**
     * Retrieves a list of contacts from the contacts table in the client_schedule database based on the specified contact name.
     *
     * @param contactName The name of the contact to filter the list.
     * @return contactsList A list of contacts matching the provided contact name.
     */
    public static ObservableList<Contacts> getContact(String contactName) {
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.contacts WHERE Contact_Name = ?";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setString(1, contactName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int contactID = resultSet.getInt("Contact_ID");
                contactName = resultSet.getString("Contact_Name");
                String email = resultSet.getString("Email");
                Contacts contact = new Contacts(contactID, contactName, email);
                contactsList.add(contact);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactsList;


    }

    /**
     * This method selects contacts from the contacts table in the client_schedule database.
     *
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectContact() throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.contacts";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int contactID = resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");
            System.out.print(contactID + " " + " | ");
            System.out.print(contactName + " " + " | ");
            System.out.println(email);
        }

    }

    /**
     * This method selects a contact from the contacts table in the client_schedule database.
     *
     * @param contactID contact ID to be selected from the contacts table
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectContact(int contactID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.contacts WHERE Contact_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1, contactID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            contactID = resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");
            System.out.print(contactID + " " + " | ");
            System.out.print(contactName + " " + " | ");
            System.out.println(email);
        }


    }

    /**
     * This method inserts a contact into the contacts table in the client_schedule database.
     *
     * @param contactID   The ID of the contact.
     * @param contactName The name of the contact.
     * @param email       The email address of the contact.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int insertContact(int contactID, String contactName, String email) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`contacts` (`Contact_ID`, `Contact_Name`, `Email`) VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, contactID);
        preparedStatement.setString(2, contactName);
        preparedStatement.setString(3, email);

        return preparedStatement.executeUpdate();
    }


    /**
     * This method updates a contact into the contacts table in the client_schedule database.
     *
     * @param contactID    The ID of the contact to be updated.
     * @param contactName  The new name for the contact.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int updateContact(int contactID, String contactName) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`contacts` SET `Contact_Name` = ? WHERE (`Contact_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1, contactName);
        preparedStatement.setInt(2, contactID);


        return preparedStatement.executeUpdate();
    }

    /**
     * This method deletes a contact into the contacts table in the client_schedule database.
     *
     * @param contactID    The ID of the contact to be deleted.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int deleteContact(int contactID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`contacts` WHERE (`Contact_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, contactID);

        return preparedStatement.executeUpdate();
    }

}
