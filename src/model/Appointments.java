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
public class Appointments {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * Constructor for Appointments class
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     */
    public Appointments(int appointmentID, String title, String description, String location, String type,String start,
                        String end, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy,
                        int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID=customerID;
        this.userID=userID;
        this.contactID=contactID;
    }

    /**
     * Getter for appointmentID of appointment
     * @return appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Setter for appointmentID of appointment
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Getter for title of appointment
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title of appointment
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for description of appointment
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description of appointment
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for location of appointment
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for location of appointment
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for type of appointment
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type of appointment
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for start of appointment
     * @return start
     */
    public String getStart() {
        return start;
    }

    /**
     * Setter for start of appointment
     * @param start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Getter for end of appointment
     * @return end
     */
    public String getEnd() {
        return end;
    }

    /**
     * Setter for end of appointment
     * @param end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Getter for createDate of appointment
     * @return createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter for createDate of appointment
     * @param createDate
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for createdBy of appointment
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter for createdBy of appointment
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for lastUpdate of appointment
     * @return
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for lastUpdate of appointment
     * @param lastUpdate
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for lastUpdatedBy of appointment
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter for lastUpdatedBy of appointment
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Getter for customerID of appointment
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Setter for customerID of appointment
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter for userID of appointment
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID of appointment
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for contactID of appointment
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter for contactID of appointment
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}
