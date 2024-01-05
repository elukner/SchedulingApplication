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

/**
 * TODO comment
 */
public class Contacts {

    private int contactID;
    private String contactName;
    private String email;

    /**
     * TODO comment
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
     * TODO comment
     * @return
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * TODO comment
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * TODO comment
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * TODO comment
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * TODO comment
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * TODO comment
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }


}
