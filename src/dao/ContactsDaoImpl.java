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
import java.util.ArrayList;
import java.util.List;

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
 * Comment TODO
 */
public class ContactsDaoImpl {


    /**
     * Comment TODO
     * @return
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
     * Comment TODO
     * @throws SQLException
     */
    public static void selectContact() throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.contacts";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int contactID=resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");
            System.out.print(contactID+ " " + " | ");
            System.out.print(contactName + " " + " | ");
            System.out.println(email);
        }

    }

    /**
     *Comment TODO
     * @throws SQLException
     */
    public static void selectContact(int contactID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.contacts WHERE Contact_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1,contactID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            contactID=resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");
            System.out.print(contactID+ " " + " | ");
            System.out.print(contactName + " " + " | ");
            System.out.println(email);
        }


    }

    /**
     * COMMENT TODO
     * @param contactID
     * @param contactName
     * @param email
     * @return
     * @throws SQLException
     */
    public static int insertContact(int contactID, String contactName, String email) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`contacts` (`Contact_ID`, `Contact_Name`, `Email`) VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,contactID);
        preparedStatement.setString(2,contactName);
        preparedStatement.setString(3,email);

        return preparedStatement.executeUpdate();
    }





    /**
     * COmment TODO
     * @param contactID
     * @param contactName
     * @return
     * @throws SQLException
     */
    public static int updateContact(int contactID, String contactName) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`contacts` SET `Contact_Name` = ? WHERE (`Contact_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1,contactName);
        preparedStatement.setInt(2,contactID);


        return preparedStatement.executeUpdate();
    }

    /**
     * COmment TODO
     * @param contactID
     * @return
     * @throws SQLException
     */
    public static int deleteContact(int contactID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`contacts` WHERE (`Contact_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,contactID);

        return preparedStatement.executeUpdate();
    }

}
