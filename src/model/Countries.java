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
public class Countries {
    private int countryID;
    private String country;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    /**
     * Class Countries Constructor
     *
     * @param countryID
     * @param country
     */
    public Countries(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * Getter of countryID of country
     *
     * @return countryID of country
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Setter of countryID of country
     *
     * @param countryID of country
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Getter of country of country
     *
     * @return country of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter of country of country
     *
     * @param country of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter of createDate of country
     *
     * @return createDate of country
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter of createDate of country
     *
     * @param createDate of country
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter of createdBy of country
     *
     * @return of country
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter of createdBy of country
     *
     * @param createdBy of country
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter of lastUpdate of country
     *
     * @return lastUpdate of country
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter of lastUpdate of country
     *
     * @param lastUpdate of country
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter of lastUpdatedBy of country
     *
     * @return lastUpdatedBy of country
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter of lastUpdatedBy of country
     *
     * @param lastUpdatedBy of country
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
