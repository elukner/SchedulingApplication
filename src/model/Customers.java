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
 * This class represents a POJO (Plain Old Java Object) for Customers retrieved from the client_schedule
 * database Customers table. This class is utilized for mapping rows obtained from the client_schedule
 * database Customers table.
 */
public class Customers {


    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionID; //FOREIGN KEY

    //Added due to requirement that customer record table view has country column
    private String country;

    /**
     * Class Constructor of Customers
     *
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionID
     */
    public Customers(int customerID, String customerName,
                     String address, String postalCode, String phone,
                     String createDate, String createdBy,
                     String lastUpdate, String lastUpdatedBy,
                     int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     * Class Constructor of Customers without createDate and createdBy
     *
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionID
     */
    public Customers(int customerID, String customerName,
                     String address, String postalCode, String phone,
                     String lastUpdate, String lastUpdatedBy,
                     int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     * Class Constructor of Customers with country and without createDate, createdBy,
     * lastUpdated, lastUpdatedBy
     *
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionID
     */
    public Customers(int customerID, String customerName,
                     String address, String postalCode, String phone,
                     int divisionID, String country) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
        //Added due to requirement that customer record table view has country column
        this.country = country;
    }


    /**
     * Getter of customerID of Customer
     *
     * @return customerID of Customer
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Setter of customerID of Customer
     *
     * @param customerID of Customer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter of customerName of Customer
     *
     * @return customerName of Customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter of customerName of Customer
     *
     * @param customerName of Customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter of address of Customer
     *
     * @return address of Customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter of address of Customer
     *
     * @param address of Customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gettter of postalCode of Customer
     *
     * @return postalCode of Customer
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter of postalCode of Customer
     *
     * @param postalCode of Customer
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter of phone of Customer
     *
     * @return phone of Customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter of phone of Customer
     *
     * @param phone of Customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter of createDate of Customer
     *
     * @return createDate of Customer
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter of createDate of Customer
     *
     * @param createDate of Customer
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter of createdBy of Customer
     *
     * @return createdBy of Customer
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter of createdBy of Customer
     *
     * @param createdBy of Customer
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter of lastUpdate of Customer
     *
     * @return lastUpdate of Customer
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter of lastUpdate of Customer
     *
     * @param lastUpdate of Customer
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter of lastUpdatedBy of Customer
     *
     * @return lastUpdatedBy of Customer
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter of lastUpdatedBy of Customer
     *
     * @param lastUpdatedBy of Customer
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Getter of divisionID of Customer
     *
     * @return divisionID of Customer
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Setter of divisionID of Customer
     *
     * @param divisionID of Customer
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }


    /**
     * Getter of country of Countries
     * Added due to requirement that customer record table view has country column
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter of country of Countries
     * Added due to requirement that customer record table view has country column
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
