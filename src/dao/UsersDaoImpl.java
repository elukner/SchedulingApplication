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
    public static int insertUsers(int userID, String userName, String password, String createdBy,String lastUpdatedBy) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`users` (`User_ID`, `User_Name`, `Password`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`) VALUES (?, ?, ?, NOW(), ?, NOW(), ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,userID);
        preparedStatement.setString(2,userName);
        preparedStatement.setString(3,password);
        preparedStatement.setString(4,createdBy);
        preparedStatement.setString(5,lastUpdatedBy);
        return preparedStatement.executeUpdate();
    }


    /**
     *
     * @throws SQLException
     */
    public static void selectUsers() throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.users";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int userID=resultSet.getInt("User_ID");
            String userName = resultSet.getString("User_Name");
            String password= resultSet.getString("Password");
            String createDate= resultSet.getString("Create_Date");
            String createdBy= resultSet.getString("Created_By");
            String lastUpdate= resultSet.getString("Last_Update");
            String lastUpdatedBy= resultSet.getString("Last_Updated_By");

            System.out.print(userID + " " + " | ");
            System.out.print(userName+ " " + " | ");
            System.out.print(password+ " " + " | ");
            System.out.print(createDate+ " " + " | ");
            System.out.print(createdBy+ " " + " | ");
            System.out.print(lastUpdate+ " " + " | ");
            System.out.println(lastUpdatedBy);
        }


    }

    /**
     *
     * @throws SQLException
     */
    public static void selectUsers(int userID) throws SQLException {

        String sqlStatement = "SELECT * FROM client_schedule.users WHERE User_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1,userID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            userID=resultSet.getInt("User_ID");
            String userName = resultSet.getString("User_Name");
            String password= resultSet.getString("Password");
            String createDate= resultSet.getString("Create_Date");
            String createdBy= resultSet.getString("Created_By");
            String lastUpdate= resultSet.getString("Last_Update");
            String lastUpdatedBy= resultSet.getString("Last_Updated_By");

            System.out.print(userID + " " + " | ");
            System.out.print(userName+ " " + " | ");
            System.out.print(password+ " " + " | ");
            System.out.print(createDate+ " " + " | ");
            System.out.print(createdBy+ " " + " | ");
            System.out.print(lastUpdate+ " " + " | ");
            System.out.println(lastUpdatedBy);
        }


    }

    /**
     * TODO COMMENT
     * @param userID
     * @param password
     * @return
     * @throws SQLException
     */
    public static int updateUsers(int userID, String password) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`users` SET `Password` = ? WHERE (`User_ID` = ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1,password);
        preparedStatement.setInt(2,userID);


        return preparedStatement.executeUpdate();
    }

    /**
     *Comment TODO
     * @param userID
     * @return
     * @throws SQLException
     */
    public static int deleteUsers(int userID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`users` WHERE (`User_ID` = ?)";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1,userID);

        return preparedStatement.executeUpdate();
    }


    /**
     * This method retrieves a list of users from the users table in the client_schedule database.
     *
     * @return usersList a list of users
     */
    public static ObservableList<Users> getAllUsers(){
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        try{
            String sqlStatement = "SELECT * FROM client_schedule.users";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int userID=resultSet.getInt("User_ID");
                String userName = resultSet.getString("User_Name");
                String password= resultSet.getString("Password");
                String createDate= resultSet.getString("Create_Date");
                String createdBy= resultSet.getString("Created_By");
                String lastUpdate= resultSet.getString("Last_Update");
                String lastUpdatedBy= resultSet.getString("Last_Updated_By");
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
