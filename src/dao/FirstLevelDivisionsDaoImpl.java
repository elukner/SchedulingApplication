package dao;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This utility class FirstLevelDivisionsDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the first_level_divisions table.
 **/
public class FirstLevelDivisionsDaoImpl {
    public static int insertFirstLevelDivisions(int divisionID, String division, String createdBy, String lastUpdatedBy, int countryID) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`first_level_divisions` (`Division_ID`, `Division`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`, `COUNTRY_ID`) VALUES (?, ?, NOW(), ?, NOW(), ?, ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,divisionID);
        preparedStatement.setString(2,division);
        preparedStatement.setString(3,createdBy);
        preparedStatement.setString(4,lastUpdatedBy);
        preparedStatement.setInt(5,countryID);

        return preparedStatement.executeUpdate();
    }


    /**
     *
     * @throws SQLException
     */
    public static void selectFirstLevelDivisions() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int divisionID=resultSet.getInt("Division_ID");
            String division = resultSet.getString("Division");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int countryID=resultSet.getInt("Country_ID");
            System.out.print(divisionID + " " + " | ");
            System.out.print(division + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.println(countryID);
        }


    }

    /**
     *
     * @throws SQLException
     */
    public static void selectFirstLevelDivisions(int divisionID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions WHERE Division_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1,divisionID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            divisionID=resultSet.getInt("Division_ID");
            String division = resultSet.getString("Division");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int countryID=resultSet.getInt("Country_ID");
            System.out.print(divisionID + " " + " | ");
            System.out.print(division + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.println(countryID);
        }


    }

    /**
     *
     * @param divisionID
     * @param division
     * @return
     * @throws SQLException
     */
    public static int updateFirstLevelDivisions(int divisionID, String division) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`first_level_divisions` SET `Division` = ? WHERE (`Division_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1,division);
        preparedStatement.setInt(2,divisionID);


        return preparedStatement.executeUpdate();
    }

    /**
     *
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static int deleteFirstLevelDivisions(int divisionID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`first_level_divisions` WHERE (`Division_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,divisionID);

        return preparedStatement.executeUpdate();
    }

    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions(){
        ObservableList<FirstLevelDivisions> countriesList = FXCollections.observableArrayList();
        try{
            String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int divisionID=resultSet.getInt("Division_ID");
                String division = resultSet.getString("Division");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int countryID=resultSet.getInt("Country_ID");
                FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, division,
                        createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);
                countriesList.add(firstLevelDivision);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countriesList;
    }
}
