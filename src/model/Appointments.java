package model;
/**
 * Project: SchedulingApplication
 * Package: model
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

/**
 * This class represents a POJO (Plain Old Java Object) for Appointments retrieved from the client_schedule
 * database Appointments table. This class is utilized for mapping rows obtained from the client_schedule
 * database Appointments table.
 */
public class Appointments {

    /**
     * Unique identifier for the appointment.
     */
    private int appointmentID;

    /**
     * The title of the appointment.
     */
    private StringProperty title;

    /**
     * The description of the appointment.
     */
    private String description;

    /**
     * The location of the appointment.
     */
    private String location;

    /**
     * The type of the appointment.
     */
    private String type;

    /**
     * The start time of the appointment.
     */
    private LocalDateTime start;

    /**
     * The end time of the appointment.
     */
    private LocalDateTime end;

    /**
     * The date when the appointment was created.
     */
    private String createDate;

    /**
     * The user who created the appointment.
     */
    private String createdBy;

    /**
     * The date when the appointment was last updated.
     */
    private String lastUpdate;

    /**
     * The user who last updated the appointment.
     */
    private String lastUpdatedBy;

    /**
     * The ID of the customer associated with the appointment (foreign key).
     */
    private int customerID;

    /**
     * The ID of the user associated with the appointment (foreign key).
     */
    private int userID;

    /**
     * The ID of the contact associated with the appointment (foreign key).
     */
    private int contactID;

    /**
     * Constructs an Appointments object with the specified attributes.
     *
     * @param appointmentID   The unique identifier for the appointment.
     * @param title           The title or subject of the appointment.
     * @param description     The description or details of the appointment.
     * @param location        The location where the appointment will take place.
     * @param type            The type or category of the appointment.
     * @param start           The start date and time of the appointment.
     * @param end             The end date and time of the appointment.
     * @param createDate      The date when the appointment record was created.
     * @param createdBy       The user who created the appointment record.
     * @param lastUpdate      The date when the appointment record was last updated.
     * @param lastUpdatedBy   The user who last updated the appointment record.
     * @param customerID      The unique identifier of the associated customer.
     * @param userID          The unique identifier of the associated user.
     * @param contactID       The unique identifier of the associated contact.
     */
    public Appointments(int appointmentID, StringProperty title, String description, String location, String type,
                        LocalDateTime start,
                        LocalDateTime end, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy,
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
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * Constructs an Appointments object with the specified attributes.
     *
     * @param title           The title or subject of the appointment.
     * @param description     The description or details of the appointment.
     * @param location        The location where the appointment will take place.
     * @param type            The type or category of the appointment.
     * @param start           The start date and time of the appointment.
     * @param end             The end date and time of the appointment.
     * @param createDate      The date when the appointment record was created.
     * @param createdBy       The user who created the appointment record.
     * @param lastUpdate      The date when the appointment record was last updated.
     * @param lastUpdatedBy   The user who last updated the appointment record.
     * @param customerID      The unique identifier of the associated customer.
     * @param userID          The unique identifier of the associated user.
     * @param contactID       The unique identifier of the associated contact.
     */
    public Appointments(StringProperty title, String description, String location, String type, LocalDateTime start,
                        LocalDateTime end, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy,
                        int customerID, int userID, int contactID) {
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
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * Getter for appointmentID of appointment
     *
     * @return appointmentID of appointment
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Setter for appointmentID of appointment
     *
     * @param appointmentID of appointment
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Getter for title of appointment
     *
     * @return title of appointment
     */
    public StringProperty getTitle() {
        return title;
    }

    /**
     * Setter for title of appointment
     *
     * @param title of appointment
     */
    public void setTitle(StringProperty title) {
        this.title = title;
    }

    /**
     * Getter for description of appointment
     *
     * @return description of appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description of appointment
     *
     * @param description of appointment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for location of appointment
     *
     * @return location of appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for location of appointment
     *
     * @param location of appointment
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for type of appointment
     *
     * @return type of appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type of appointment
     *
     * @param type of appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for start of appointment
     *
     * @return start of appointment
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for start of appointment
     *
     * @param start of appointment
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for end of appointment
     *
     * @return end of appointment
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for end of appointment
     *
     * @param end of appointment
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter for createDate of appointment
     *
     * @return createDate of appointment
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter for createDate of appointment
     *
     * @param createDate of appointment
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for createdBy of appointment
     *
     * @return createdBy of appointment
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter for createdBy of appointment
     *
     * @param createdBy of appointment
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for lastUpdate of appointment
     *
     * @return lastUpdate of appointment
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for lastUpdate of appointment
     *
     * @param lastUpdate of appointment
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for lastUpdatedBy of appointment
     *
     * @return lastUpdatedBy of appointment
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter for lastUpdatedBy of appointment
     *
     * @param lastUpdatedBy of appointment
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Getter for customerID of appointment
     *
     * @return customerID of appointment
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Setter for customerID of appointment
     *
     * @param customerID of appointment
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter for userID of appointment
     *
     * @return userID of appointment
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID of appointment
     *
     * @param userID of appointment
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for contactID of appointment
     *
     * @return contactID of appointment
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter for contactID of appointment
     *
     * @param contactID of appointment
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}
