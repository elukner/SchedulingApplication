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
public class Countries {
    private int countryID;
    private String country;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    public Countries(int countryID, String country){
        this.countryID=countryID;
        this.country =country;
    }
    public int getCountryID(){return countryID;}

    public String getCountryName(){return country;}
}
