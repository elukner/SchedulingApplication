package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * The UserAppointmentReport class represents a report containing information about a user's appointments.
 * It includes details such as user ID, login date and time, user name, total appointments, and average duration.
 */
public class UserAppointmentReport {

    /**
     * The unique identifier for the user.
     */
    private IntegerProperty userID;

    /**
     * The name of the user.
     */
    private StringProperty userName;

    /**
     * The date and time when the user logged in.
     */
    private StringProperty userLogInDateTime;

    /**
     * The total number of appointments for the user.
     */
    private IntegerProperty totalAppointments;

    /**
     * The average duration of the user's appointments.
     */
    private DoubleProperty averageDuration;

    /**
     * Constructs a UserAppointmentReport with all available details.
     *
     * @param userID            The unique identifier for the user.
     * @param userLogInDateTime The date and time when the user logged in.
     * @param userName          The name of the user.
     * @param totalAppointments The total number of appointments for the user.
     * @param averageDuration   The average duration of the user's appointments.
     */
    public UserAppointmentReport(IntegerProperty userID,StringProperty userName, StringProperty userLogInDateTime,
                                 IntegerProperty totalAppointments, DoubleProperty averageDuration) {
        this.userID = userID;
        this.userName = userName;
        this.userLogInDateTime = userLogInDateTime;
        this.totalAppointments = totalAppointments;
        this.averageDuration = averageDuration;
    }

    /**
     * Constructs a UserAppointmentReport without the login date and time.
     *
     * @param userID            The unique identifier for the user.
     * @param userName          The name of the user.
     * @param totalAppointments The total number of appointments for the user.
     * @param averageDuration   The average duration of the user's appointments.
     */
    public UserAppointmentReport(IntegerProperty userID, StringProperty userName, IntegerProperty totalAppointments,
                                 DoubleProperty averageDuration) {
        this.userID = userID;
        this.userName = userName;
        this.totalAppointments = totalAppointments;
        this.averageDuration = averageDuration;
    }

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public IntegerProperty getUserID() {
        return userID;
    }

    /**
     * Sets the user ID.
     *
     * @param userID The user ID to set.
     */
    public void setUserID(IntegerProperty userID) {
        this.userID = userID;
    }

    /**
     * Gets the user's login date and time.
     *
     * @return The user's login date and time.
     */
    public StringProperty getUserLogInDateTime() {
        return userLogInDateTime;
    }

    /**
     * Sets the user's login date and time.
     *
     * @param userLogInDateTime The login date and time to set.
     */
    public void setUserLogInDateTime(StringProperty userLogInDateTime) {
        this.userLogInDateTime = userLogInDateTime;
    }

    /**
     * Gets the user name.
     *
     * @return The user name.
     */
    public StringProperty getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName The user name to set.
     */
    public void setUserName(StringProperty userName) {
        this.userName = userName;
    }

    /**
     * Gets the total number of appointments.
     *
     * @return The total number of appointments.
     */
    public IntegerProperty getTotalAppointments() {
        return totalAppointments;
    }

    /**
     * Sets the total number of appointments.
     *
     * @param totalAppointments The total appointments to set.
     */
    public void setTotalAppointments(IntegerProperty totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    /**
     * Gets the average duration of appointments.
     *
     * @return The average duration of appointments.
     */
    public DoubleProperty getAverageDuration() {
        return averageDuration;
    }

    /**
     * Sets the average duration of appointments.
     *
     * @param averageDuration The average duration to set.
     */
    public void setAverageDuration(DoubleProperty averageDuration) {
        this.averageDuration = averageDuration;
    }
}
