package model;

/**
 * Project: SchedulingApplication
 * Package: model
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a Contact Schedule Report containing information about appointments scheduled for a specific contact.
 */
public class ContactScheduleReport {

    private IntegerProperty contactID;
    private StringProperty contactName;
    private IntegerProperty appointmentID;
    private String title;
    private String type;
    private String description;
    private String start;
    private String end;
    private IntegerProperty customerID;

    /**
     * Constructs a ContactScheduleReport with the specified details.
     *
     * @param contactID The ID of the contact associated with the report.
     * @param contactName The name of the contact associated with the report.
     * @param appointmentID The ID of the appointment included in the report.
     * @param title The title of the appointment.
     * @param type The type of the appointment.
     * @param description The description of the appointment.
     * @param start The start date and time of the appointment.
     * @param end The end date and time of the appointment.
     * @param customerID The ID of the customer associated with the appointment.
     */
    public ContactScheduleReport(IntegerProperty contactID, StringProperty contactName, IntegerProperty appointmentID, String title,
                                 String type, String description, String start, String end, IntegerProperty customerID) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.appointmentID = appointmentID;
        this.title = title;
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
    }

    /**
     * Retrieves the contact ID associated with the report.
     *
     * @return The contact ID.
     */
    public IntegerProperty getContactID() {
        return contactID;
    }

    /**
     * Sets the contact ID for the report.
     *
     * @param contactID The new contact ID to be set.
     */
    public void setContactID(IntegerProperty contactID) {
        this.contactID = contactID;
    }

    /**
     * Retrieves the contact name associated with the report.
     *
     * @return The contact name.
     */
    public StringProperty getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name for the report.
     *
     * @param contactName The new contact name to be set.
     */
    public void setContactName(StringProperty contactName) {
        this.contactName = contactName;
    }

    /**
     * Retrieves the appointment ID included in the report.
     *
     * @return The appointment ID.
     */
    public IntegerProperty getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets the appointment ID for the report.
     *
     * @param appointmentID The new appointment ID to be set.
     */
    public void setAppointmentID(IntegerProperty appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Retrieves the title of the appointment.
     *
     * @return The appointment title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment for the report.
     *
     * @param title The new title to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the type of the appointment.
     *
     * @return The appointment type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment for the report.
     *
     * @param type The new type to be set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the description of the appointment.
     *
     * @return The appointment description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the appointment for the report.
     *
     * @param description The new description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the start date and time of the appointment.
     *
     * @return The start date and time.
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets the start date and time of the appointment for the report.
     *
     * @param start The new start date and time to be set.
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Retrieves the end date and time of the appointment.
     *
     * @return The end date and time.
     */
    public String getEnd() {
        return end;
    }

    /**
     * Sets the end date and time of the appointment for the report.
     *
     * @param end The new end date and time to be set.
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Retrieves the customer ID associated with the appointment.
     *
     * @return The customer ID.
     */
    public IntegerProperty getCustomerID() {
        return customerID;
    }

    /**
     * Sets the customer ID for the report.
     *
     * @param customerID The new customer ID to be set.
     */
    public void setCustomerID(IntegerProperty customerID) {
        this.customerID = customerID;
    }





}
