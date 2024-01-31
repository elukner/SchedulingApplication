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
 * This class represents a POJO (Plain Old Java Object) for Customers retrieved from the client_schedule
 * database Customers table. This class is utilized for mapping rows obtained from the client_schedule
 * database Customers table.
 */
public class Customers {

    /**
     * The unique identifier for the customer.
     */
    private int customerID;

    /**
     * The name of the customer.
     */
    private String customerName;

    /**
     * The address of the customer.
     */
    private String address;

    /**
     * The postal code of the customer's location.
     */
    private String postalCode;

    /**
     * The phone number associated with the customer.
     */
    private String phone;

    /**
     * The date and time when the customer record was created.
     */
    private String createDate;

    /**
     * The user who created the customer record.
     */
    private String createdBy;

    /**
     * The date and time when the customer record was last updated.
     */
    private String lastUpdate;

    /**
     * The user who last updated the customer record.
     */
    private String lastUpdatedBy;

    /**
     * The foreign key representing the division to which the customer belongs.
     */
    private int divisionID;




    /**
     * The country associated with the customer, fulfilling the requirement for the customer record table view.
     */
    private String country;


    /**
     * Constructs a new Customers object with the provided information, including the customer ID.
     *
     * @param customerID     The ID of the customer.
     * @param customerName   The name of the customer.
     * @param address        The address of the customer.
     * @param postalCode     The postal code of the customer.
     * @param phone          The phone number of the customer.
     * @param createDate     The date when the customer record is created.
     * @param createdBy      The user who created the customer record.
     * @param lastUpdate     The date of the last update to the customer record.
     * @param lastUpdatedBy  The user who last updated the customer record.
     * @param divisionID     The ID of the first-level division associated with the customer.
     * @param country        The country of the customer.
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone,
                     String createDate, String createdBy, String lastUpdate, String lastUpdatedBy,
                     int divisionID, String country) {
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
        this.country = country;
    }

    /**
     * Class Constructor for Customers.
     *
     * @param customerID      The ID of the customer.
     * @param customerName    The name of the customer.
     * @param address         The address of the customer.
     * @param postalCode      The postal code of the customer.
     * @param phone           The phone number of the customer.
     * @param createDate      The date when the customer record was created.
     * @param createdBy       The user who created the customer record.
     * @param lastUpdate      The date of the last update to the customer record.
     * @param lastUpdatedBy   The user who last updated the customer record.
     * @param divisionID      The ID of the first-level division associated with the customer.
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
     * Class constructor for Customers, representing a customer in the application.
     * This constructor is used when createDate and createdBy information is not available.
     *
     * @param customerID The unique identifier for the customer.
     * @param customerName The name of the customer.
     * @param address The address of the customer.
     * @param postalCode The postal code of the customer.
     * @param phone The phone number of the customer.
     * @param lastUpdate The date and time when the customer record was last updated.
     * @param lastUpdatedBy The user who last updated the customer record.
     * @param divisionID The ID of the first-level division associated with the customer.
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
     * Class Constructor for Customers with country information and without createDate, createdBy,
     * lastUpdate, and lastUpdatedBy details.
     *
     * @param customerID    The ID of the customer.
     * @param customerName  The name of the customer.
     * @param address       The address of the customer.
     * @param postalCode    The postal code of the customer.
     * @param phone         The phone number of the customer.
     * @param divisionID    The ID of the first-level division associated with the customer.
     * @param country       The country associated with the customer.
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
     * Constructs a new Customers object with the provided information.
     *
     * @param customerName   The name of the customer.
     * @param address        The address of the customer.
     * @param postalCode     The postal code of the customer.
     * @param phone          The phone number of the customer.
     * @param createDate     The date when the customer record is created.
     * @param createdBy      The user who created the customer record.
     * @param lastUpdate     The date of the last update to the customer record.
     * @param lastUpdatedBy  The user who last updated the customer record.
     * @param divisionID     The ID of the first-level division associated with the customer.
     * @param country        The country of the customer.
     */
    public Customers(String customerName, String address, String postalCode, String phone, String createDate,
                     String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID, String country) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
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
     * Getter method for retrieving the country of a Countries instance.
     * This method is added to fulfill the requirement of displaying the country
     * column in the customer record table view.
     *
     * @return The country associated with the instance.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter method for updating the country of a Countries instance.
     * This method is added to fulfill the requirement of updating the country
     * column in the customer record table view.
     *
     * @param country The new country value to be set.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
