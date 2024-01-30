package model;

/**
 * Project: SchedulingApplication
 * Package: model
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

/**
 * This class represents a POJO (Plain Old Java Object) for Contacts retrieved from the client_schedule
 * database Contacts table. This class is utilized for mapping rows obtained from the client_schedule
 * database Contacts table.
 */
public class Contacts {

    private int contactID;
    private String contactName;
    private String email;

    /**
     * Constructor for the Contacts class.
     *
     * @param contactID The unique identifier for the contact.
     * @param contactName The name of the contact.
     * @param email The email address associated with the contact.
     */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Getter of contactID of contact
     *
     * @return contactID of contact
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter of contactID of contact
     *
     * @param contactID of contact
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Getter of contactName of contact
     *
     * @return contactName of contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter of contactName of contact
     *
     * @param contactName of contact
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter of email of contact
     *
     * @return email of contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of email of contact
     *
     * @param email of contact
     */
    public void setEmail(String email) {
        this.email = email;
    }


}
