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
 * This class represents a POJO (Plain Old Java Object) for Users retrieved from the client_schedule
 * database Users table. This class is utilized for mapping rows obtained from the client_schedule database Users table.
 */
public class Users {

    /**
     * The unique identifier for the user.
     */
    private int userID;

    /**
     * Represents the username of the user used for authentication.
     */
    private String userName;

    /**
     * Represents the password associated with the user account.
     */
    private String password;

    /**
     * Represents the timestamp when the user account was created.
     */
    private String createDate;

    /**
     * Represents the user who created this user account.
     */
    private String createdBy;

    /**
     * Represents the timestamp when the user account was last updated.
     */
    private String lastUpdate;

    /**
     * Represents the user who last updated this user account.
     */
    private String lastUpdatedBy;


    /**
     * Class constructor for Users, representing a user in the application.
     *
     * @param userID The unique identifier for the user.
     * @param userName The username associated with the user.
     * @param password The password associated with the user.
     * @param createDate The date and time when the user record was created.
     * @param createdBy The user who created the user record.
     * @param lastUpdate The date and time when the user record was last updated.
     * @param lastUpdatedBy The user who last updated the user record.
     */
    public Users(int userID, String userName, String password, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }


    /**
     * Getter for userID
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID of User
     *
     * @param userID of User
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for userName of User
     *
     * @return userName of User
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for userName of User
     *
     * @param userName of User
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for password of User
     *
     * @return password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password of User
     *
     * @param password of User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for createDate of User
     *
     * @return createDate of User
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter for createDate of User
     *
     * @param createDate of User
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for createdBy of User
     *
     * @return createdBy of User
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter for createdBy of User
     *
     * @param createdBy of User
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for lastUpdate of User
     *
     * @return lastUpdate of User
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for lastUpdate of User
     *
     * @param lastUpdate of User
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for lastUpdatedBy of User
     *
     * @return lastUpdatedBy of User
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter for lastUpdatedBy of User
     *
     * @param lastUpdatedBy of User
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


}
