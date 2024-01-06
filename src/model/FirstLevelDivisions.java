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
 * The model package will hold your POJOs.
 * Define POJO (Plain Old Java Object) classes that Map the ERD.
 * The POJOs are used to Map rows from the database tables
 */
public class FirstLevelDivisions {

    private int divisionID;
    private String division;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int countryID; //FOREIGN KEY

    /**
     * Class constructor for FirstLevelDivisions
     *
     * @param divisionID
     * @param division
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param countryID
     */
    public FirstLevelDivisions(int divisionID, String division,
                               String createDate, String createdBy,
                               String lastUpdate, String lastUpdatedBy,
                               int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * Getter for divisionID of First Level Division
     *
     * @return divisionID of First Level Division
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Setter for divisionID of First Level Division
     *
     * @param divisionID of First Level Division
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Getter for division of First Level Division
     *
     * @return division of First Level Division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Setter for division of First Level Division
     *
     * @param division of First Level Division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Getter for createDate of First Level Division
     *
     * @return createDate of First Level Division
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter for createDate of First Level Division
     *
     * @param createDate of First Level Division
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for createdBy of First Level Division
     *
     * @return createdBy of First Level Division
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter for createdBy of First Level Division
     *
     * @param createdBy of First Level Division
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for lastUpdate of First Level Division
     *
     * @return lastUpdate of First Level Division
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for lastUpdate of First Level Division
     *
     * @param lastUpdate of First Level Division
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for lastUpdatedBy of First Level Division
     *
     * @return lastUpdatedBy of First Level Division
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter for lastUpdatedBy of First Level Division
     *
     * @param lastUpdatedBy of First Level Division
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Getter for countryID  of First Level Division
     *
     * @return countryID  of First Level Division
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Setter for countryID of First Level Division
     *
     * @param countryID of First Level Division
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }


}
