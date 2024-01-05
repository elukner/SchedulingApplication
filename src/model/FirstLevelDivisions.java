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
public class FirstLevelDivisions {

  private int divisionID;
  private String division;
  private String createDate;
  private String createdBy;
  private String lastUpdate;
  private String lastUpdatedBy;
  private int countryID; //FOREIGN KEY

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

  public int getDivisionID() {
    return divisionID;
  }

  public void setDivisionID(int divisionID) {
    this.divisionID = divisionID;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(String lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public int getCountryID() {
    return countryID;
  }

  public void setCountryID(int countryID) {
    this.countryID = countryID;
  }


}
