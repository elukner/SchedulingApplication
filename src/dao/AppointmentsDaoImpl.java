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
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This utility class AppointmentsDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the appointments table.
 **/
public class AppointmentsDaoImpl {
    /**
     * This method retrieves a list of appointments from the appointments table in the client_schedule database.
     *
     * @return appointmentsList a list of appointments
     */
    public static ObservableList<Appointments> getAllAppointmentsCustomerID(int customerID) {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE Customer_ID = ?";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            preparedStatement.setInt(1, customerID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int appointmentID = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                String start = resultSet.getString("Start");
                String end = resultSet.getString("End");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, title, description, location, type, start,
                        end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }

    /**
     * TODO This method retrieves a list of appointments from the appointments table in the client_schedule database.
     *  by the total number of customer appointments by type and month
     *
     * @return appointmentsList a list of appointments
     */
    public static ObservableList<String> getAllAppointmentsCustomerIDMT() {
        ObservableList<String> appointmentsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT MONTH(Start) AS Month, Type, COUNT(*) AS Total_Appointments FROM client_schedule.appointments GROUP BY MONTH(Start), Type;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("Type");
                String start = resultSet.getString("Month");
                int totalAppointments = resultSet.getInt("Total_Appointments");
                appointmentsList.add(type+" "+start+" "+totalAppointments);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }


    /**
     * This method retrieves a list of appointments from the appointments table in the client_schedule database.
     *
     * @return appointmentsList a list of appointments
     */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.appointments";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int appointmentID = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                String start = resultSet.getString("Start");
                String end = resultSet.getString("End");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, title, description, location, type, start,
                        end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }

    /**
     * This method inserts a appointment into the appointments table in the client_schedule database.
     *
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createdBy
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int insertAppointments(int appointmentID, String title, String description, String location, String type, String start,
                                         String end, String createdBy, String lastUpdatedBy,
                                         int customerID, int userID, int contactID) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`appointments` " +
                "(`Appointment_ID`, `Title`, `Description`, `Location`, `Type`, " +
                "`Start`, `End`, `Create_Date`, `Created_By`, `Last_Update`, " +
                "`Last_Updated_By`, `Customer_ID`, `User_ID`, `Contact_ID`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?, ?, ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, appointmentID);
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, description);
        preparedStatement.setString(4, location);
        preparedStatement.setString(5, type);
        preparedStatement.setString(6, start);
        preparedStatement.setString(7, end);
        preparedStatement.setString(8, createdBy);
        preparedStatement.setString(9, lastUpdatedBy);
        preparedStatement.setInt(10, customerID);
        preparedStatement.setInt(11, userID);
        preparedStatement.setInt(12, contactID);


        return preparedStatement.executeUpdate();
    }

    /**
     * This method selects appointments from the appointments table in the client_schedule database.
     *
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectAppointment() throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.appointments";


        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int appointmentID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            String start = resultSet.getString("Start");
            String end = resultSet.getString("End");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int customerID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");
            System.out.print(appointmentID + " " + " | ");
            System.out.print(title + " " + " | ");
            System.out.print(description + " " + " | ");
            System.out.print(location + " " + " | ");
            System.out.print(type + " " + " | ");
            System.out.print(start + " " + " | ");
            System.out.print(end + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.print(customerID + " " + " | ");
            System.out.print(userID + " " + " | ");
            System.out.println(contactID);
        }
    }


    /**
     * This method selects an appointment from the appointments table in the client_schedule database.
     *
     * @param appointmentID appointment ID to be selected from the appointments table
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static void selectAppointment(int appointmentID) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE Appointment_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1, appointmentID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            appointmentID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            String start = resultSet.getString("Start");
            String end = resultSet.getString("End");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int customerID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");
            System.out.print(appointmentID + " " + " | ");
            System.out.print(title + " " + " | ");
            System.out.print(description + " " + " | ");
            System.out.print(location + " " + " | ");
            System.out.print(type + " " + " | ");
            System.out.print(start + " " + " | ");
            System.out.print(end + " " + " | ");
            System.out.print(createDate + " " + " | ");
            System.out.print(createdBy + " " + " | ");
            System.out.print(lastUpdate + " " + " | ");
            System.out.print(lastUpdatedBy + " " + " | ");
            System.out.print(customerID + " " + " | ");
            System.out.print(userID + " " + " | ");
            System.out.println(contactID);
        }
    }

    /**
     * This method updates a appointment into the appointments table in the client_schedule database.
     *
     * @param appointmentID
     * @param title
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int updateAppointment(int appointmentID, String title, String description, String location,String type,
                                        String start,
                                        String end, String lastUpdate, String lastUpdatedBy,
                                        int customerID, int userID, int contactID) throws SQLException {
//        String sqlStatement = "UPDATE `client_schedule`.`appointments` SET `Title` = ? WHERE (`Appointment_ID` = ?)";

        String sqlStatement = "UPDATE `client_schedule`.`appointments` SET `Title` = ?, `Description` = ?, " +
                "`Location` = ?, `Type` = ?, `Start` = ?, `End` = ?, `Last_Update` = ?, `Last_Updated_By` = ?, " +
                "`Customer_ID` = ?, `User_ID` = ?, `Contact_ID` = ? WHERE (`Appointment_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setString(5, start);
        preparedStatement.setString(6, end);
        preparedStatement.setString(7, lastUpdate);
        preparedStatement.setString(8, lastUpdatedBy);
        preparedStatement.setInt(9, customerID);
        preparedStatement.setInt(10, userID);
        preparedStatement.setInt(11, contactID);
        preparedStatement.setInt(12, appointmentID);


        return preparedStatement.executeUpdate();
    }

    /**
     * This method deletes a appointment into the appointments table in the client_schedule database.
     *
     * @param appointmentID
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int deleteAppointment(int appointmentID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`appointments` WHERE (`Appointment_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, appointmentID);

        return preparedStatement.executeUpdate();
    }
}
