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
 * This class represents a POJO (Plain Old Java Object) for FirstLevelDivisions retrieved from the client_schedule
 * database FirstLevelDivisions table. This class is utilized for mapping rows obtained from the client_schedule
 * database FirstLevelDivisions table.
 */
public class FirstLevelDivisions {

    /**
     * The unique identifier for the first-level division.
     */
    private int divisionID;

    /**
     * The name of the first-level division.
     */
    private String division;

    /**
     * The date and time when the first-level division record was created.
     */
    private String createDate;

    /**
     * The user who created the first-level division record.
     */
    private String createdBy;

    /**
     * The date and time when the first-level division record was last updated.
     */
    private String lastUpdate;

    /**
     * The user who last updated the first-level division record.
     */
    private String lastUpdatedBy;

    /**
     * The foreign key representing the associated country.
     */
    private int countryID;

    /**
     * Constructs a FirstLevelDivisions object.
     *
     * @param divisionID      The ID of the first-level division.
     * @param division        The name of the first-level division.
     * @param createDate      The date when the first-level division was created.
     * @param createdBy       The user who created the first-level division.
     * @param lastUpdate      The date when the first-level division was last updated.
     * @param lastUpdatedBy   The user who last updated the first-level division.
     * @param countryID       The ID of the country associated with the first-level division.
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
