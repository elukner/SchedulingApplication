package model;

/**
 * The UserAppointmentReport class represents a report containing information about a user's appointments.
 * It includes details such as user ID, login date and time, user name, total appointments, and average duration.
 */
public class UserAppointmentReport {

    /**
     * The unique identifier for the user.
     */
    private int userID;

    /**
     * The date and time when the user logged in.
     */
    private String userLogInDateTime;

    /**
     * The name of the user.
     */
    private String userName;

    /**
     * The total number of appointments for the user.
     */
    private int totalAppointments;

    /**
     * The average duration of the user's appointments.
     */
    private double averageDuration;

    /**
     * Constructs a UserAppointmentReport with all available details.
     *
     * @param userID            The unique identifier for the user.
     * @param userLogInDateTime The date and time when the user logged in.
     * @param userName          The name of the user.
     * @param totalAppointments The total number of appointments for the user.
     * @param averageDuration   The average duration of the user's appointments.
     */
    public UserAppointmentReport(int userID, String userLogInDateTime, String userName, int totalAppointments, double averageDuration) {
        this.userID = userID;
        this.userLogInDateTime = userLogInDateTime;
        this.userName = userName;
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
    public UserAppointmentReport(int userID, String userName, int totalAppointments, double averageDuration) {
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
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID.
     *
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the user's login date and time.
     *
     * @return The user's login date and time.
     */
    public String getUserLogInDateTime() {
        return userLogInDateTime;
    }

    /**
     * Sets the user's login date and time.
     *
     * @param userLogInDateTime The login date and time to set.
     */
    public void setUserLogInDateTime(String userLogInDateTime) {
        this.userLogInDateTime = userLogInDateTime;
    }

    /**
     * Gets the user name.
     *
     * @return The user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName The user name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the total number of appointments.
     *
     * @return The total number of appointments.
     */
    public int getTotalAppointments() {
        return totalAppointments;
    }

    /**
     * Sets the total number of appointments.
     *
     * @param totalAppointments The total appointments to set.
     */
    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    /**
     * Gets the average duration of appointments.
     *
     * @return The average duration of appointments.
     */
    public double getAverageDuration() {
        return averageDuration;
    }

    /**
     * Sets the average duration of appointments.
     *
     * @param averageDuration The average duration to set.
     */
    public void setAverageDuration(double averageDuration) {
        this.averageDuration = averageDuration;
    }
}
