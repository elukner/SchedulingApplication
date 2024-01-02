package model;
/**
 *  The model package will hold your POJOs.
 *  Define POJO (Plain Old Java Object) classes that Map the ERD.
 *  The POJOs are used to Map rows from the database tables
 */
public class Countries {
    private int countryID;
    private String countryName;
    public Countries(int countryID, String countryName){
        this.countryID=countryID;
        this.countryName =countryName;
    }
    public int getCountryID(){return countryID;}

    public String getCountryName(){return countryName;}
}
