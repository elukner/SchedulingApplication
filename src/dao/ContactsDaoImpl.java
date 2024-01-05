package dao;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import model.Contacts;

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
    //list is working as a database
    List<Contacts> contacts;

    /**
     * TODO
     */
    public ContactsDaoImpl() {
        contacts = new ArrayList<Contacts>();
        Contacts contact1 = new Contacts(1, "John Doe", "johndoe@example.com");
        contacts.add(contact1);

    }

    /**
     * retrive list of students from the database
     * @return
     */
    public List<Contacts> getAllContacts() {
        return contacts;
    }

    /**
     *
     * @param contactID
     * @return
     */
    public Contacts getContact(int contactID) {
        return contacts.get(contactID);
    }

    /**
     *
     * @param contact
     */
    public void insertContact(Contacts contact) {
        contacts.get(contact.getContactID()).setContactName(contact.getContactName());
        System.out.println("Student: Roll No " + contact.getContactName() + ", updated in the database");
    }

    /**
     * TODO
     * @param contact
     */
    public void selectContact(Contacts contact) {
        //TODO
    }

    /**
     * TODO
     * @param contact
     */
    public void updateContact(Contacts contact) {
        //TODO
    }

    /**
     *
     * @param contact
     */
    public void deleteContact(Contacts contact) {
        contacts.remove(contact.getContactID());
        System.out.println("Contact: Contact_ID" + contact.getContactID() + ", deleted from database");
    }
}
