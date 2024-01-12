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
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This utility class UsersDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the users table.
 **/
public class UsersDaoImpl {
    /**
     * This method inserts a user into the users table in the client_schedule database.
     *
     * @param userID
     * @param userName
     * @param password
     * @param createdBy
     * @param lastUpdatedBy
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int insertUsers(int userID, String userName, String password, String createdBy, String lastUpdatedBy) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`users` (`User_ID`, `User_Name`, `Password`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`) VALUES (?, ?, ?, NOW(), ?, NOW(), ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, userID);
        preparedStatement.setString(2, userName);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, createdBy);
        preparedStatement.setString(5, lastUpdatedBy);
        return preparedStatement.executeUpdate();
    }

    /**
     * This method selects users from the users table in the client_schedule database.
     *
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectUsers() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.users";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int userID = resultSet.getInt("User_ID");
            String userName = resultSet.getString("User_Name");
            String password = resultSet.getString("Password");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");

            System.out.print(userID + " " + " | ");
            System.out.print(userName + " " + " | ");
            System.out.print(password + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.println(lastUpdatedBy);
        }


    }

    /**
     * This method selects a user from the users table in the client_schedule database.
     *
     * @param userID is the user ID to be selected from the users table
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectUsers(int userID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.users WHERE User_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1, userID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            userID = resultSet.getInt("User_ID");
            String userName = resultSet.getString("User_Name");
            String password = resultSet.getString("Password");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");

            System.out.print(userID + " " + " | ");
            System.out.print(userName + " " + " | ");
            System.out.print(password + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.println(lastUpdatedBy);
        }


    }


    /**
     * This method updates a user in the users table in the client_schedule database.
     *
     * @param userID
     * @param password
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int updateUsers(int userID, String password) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`users` SET `Password` = ? WHERE (`User_ID` = ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, userID);


        return preparedStatement.executeUpdate();
    }

    /**
     * This method deletes a user in the users table in the client_schedule database.
     *
     * @param userID
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int deleteUsers(int userID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`users` WHERE (`User_ID` = ?)";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, userID);

        return preparedStatement.executeUpdate();
    }


    /**
     * This method retrieves a list of users from the users table in the client_schedule database.
     *
     * @return usersList a list of users
     */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.users";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userID = resultSet.getInt("User_ID");
                String userName = resultSet.getString("User_Name");
                String password = resultSet.getString("Password");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                Users user = new Users(userID, userName, password, createDate,
                        createdBy, lastUpdate, lastUpdatedBy);
                usersList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }



    /**
     * This method retrieves a list of users from the users table in the client_schedule database.
     *
     * @return usersList a list of users
     */
    public static ObservableList<Users> getUser(String userName, String password) {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.users WHERE User_Name = ? OR Password = ?";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userID = resultSet.getInt("User_ID");
                userName = resultSet.getString("User_Name");
                password = resultSet.getString("Password");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                Users user = new Users(userID, userName, password, createDate,
                        createdBy, lastUpdate, lastUpdatedBy);
                usersList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }


    /**
     * This method retrieves a list of users from the users table in the client_schedule database.
     *
     * @return usersList a list of users
     */
    public static ObservableList<Users> getUser(String userName) {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.users WHERE User_Name = ?";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userID = resultSet.getInt("User_ID");
                userName = resultSet.getString("User_Name");
                String password = resultSet.getString("Password");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                Users user = new Users(userID, userName, password, createDate,
                        createdBy, lastUpdate, lastUpdatedBy);
                usersList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }

}
