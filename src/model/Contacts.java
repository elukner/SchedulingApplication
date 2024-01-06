package model;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

/**
 *  The model package will hold your POJOs.
 *  Define POJO (Plain Old Java Object) classes that Map the ERD.
 *  The POJOs are used to Map rows from the database tables
 */
public class Contacts {

    private int contactID;
    private String contactName;
    private String email;

    /**
     * Class Contacts constructor
     * @param contactID
     * @param contactName
     * @param email
     */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Getter of contactID of contact
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter of contactID of contact
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Getter of contactName of contact
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter of contactName of contact
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter of email of contact
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of email of contact
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }


}
