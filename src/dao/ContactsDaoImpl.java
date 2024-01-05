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
 * TODO
 */
public class ContactsDaoImpl {



    /**
     * TODO
     */
//    public ContactsDaoImpl() {
//        contacts = new ArrayList<Contacts>();
//        Contacts contact1 = new Contacts(1, "John Doe", "johndoe@example.com");
//        contacts.add(contact1);
//
//    }

    /**
     * retrive list of students from the database
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

//    /**
//     *
//     * @param contactID
//     * @return
//     */
//    public Contacts getContact(int contactID) {
//        return contacts.get(contactID);
//    }
//
//    /**
//     *
//     * @param contact
//     */
//    public void insertContact(Contacts contact) {
//        contacts.get(contact.getContactID()).setContactName(contact.getContactName());
//        System.out.println("Student: Roll No " + contact.getContactName() + ", updated in the database");
//    }
//

    /**
     *
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
//
//    /**
//     * TODO
//     * @param contact
//     */
//    public void updateContact(Contacts contact) {
//        //TODO
//    }
//
//    /**
//     *
//     * @param contact
//     */
//    public void deleteContact(Contacts contact) {
//        contacts.remove(contact.getContactID());
//        System.out.println("Contact: Contact_ID" + contact.getContactID() + ", deleted from database");
//    }
}
