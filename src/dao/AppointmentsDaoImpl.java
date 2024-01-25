package dao;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import helper.DateTimeProcessing;
import helper.JDBC;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This utility class AppointmentsDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the appointments table.
 **/
public class AppointmentsDaoImpl {

    public static ObservableList<Appointments> getUpcomingAppointmentWithin15Min() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT *\n" +
                    "FROM client_schedule.appointments\n" +
                    "WHERE Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 15 MINUTE)";
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
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title), description, location, type, start,
                        end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }
    public static boolean hasOverlappingAppointments(int customerId, String startDateTime, String endDateTime) throws SQLException {

        try{
            // Query to check for overlapping appointments
            String sqlStatement = "SELECT COUNT(*) \n" +
                    "FROM client_schedule.appointments \n" +
                    "WHERE Customer_ID = ? AND ('12:00:00' < TIME(?) AND '13:00:00' > TIME(?))";


            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, endDateTime);
            preparedStatement.setString(3, startDateTime);




            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int overlappingAppointmentsCount = resultSet.getInt(1);
                return overlappingAppointmentsCount > 0;
            }

        }catch (SQLException throwables) {
        throwables.printStackTrace();
        }

        return false;
    }

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
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title), description, location, type, start,
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
     * TODO This method retrieves a list of appointments from the appointments table in the client_schedule database.
     *  by the total number of customer appointments by type and month
     *
     * @return appointmentsList a list of appointments
     */
    public static ObservableList<Appointments> getAllAppointmentsByMonth() {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE MONTH(`Start`) = ?;";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            preparedStatement.setInt(1, LocalDate.now().getMonthValue());
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
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title), description, location, type, start,
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
    public static ObservableList<Appointments> getAllAppointmentsByWeek() {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE WEEK(`Start`) = 2;";
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
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title), description, location, type, start,
                        end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
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
    public static ObservableList<Appointments> getAllAppointmentsLocalDateTime() {
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
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title),
                        description, location, type, DateTimeProcessing.convertTimeToUTCThenLocal(start),
                        DateTimeProcessing.convertTimeToUTCThenLocal(end), createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
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
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title), description, location, type, start,
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
    public static int insertAppointments(StringProperty title, String description, String location, String type, String start,
                                         String end, String createdBy, String lastUpdatedBy,
                                         int customerID, int userID, int contactID) throws SQLException {
        String sqlStatement = "INSERT INTO `client_schedule`.`appointments` " +
                "(`Title`, `Description`, `Location`, `Type`, " +
                "`Start`, `End`, `Create_Date`, `Created_By`, `Last_Update`, " +
                "`Last_Updated_By`, `Customer_ID`, `User_ID`, `Contact_ID`) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?, ?, ?);";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setString(1, title.getValue());
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setString(5, start);
        preparedStatement.setString(6, end);
        preparedStatement.setString(7, createdBy);
        preparedStatement.setString(8, lastUpdatedBy);
        preparedStatement.setInt(9, customerID);
        preparedStatement.setInt(10, userID);
        preparedStatement.setInt(11, contactID);


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
    public static int updateAppointment(int appointmentID, StringProperty title, String description, String location, String type,
                                        String start,
                                        String end, String lastUpdate, String lastUpdatedBy,
                                        int customerID, int userID, int contactID) throws SQLException {
//        String sqlStatement = "UPDATE `client_schedule`.`appointments` SET `Title` = ? WHERE (`Appointment_ID` = ?)";

        String sqlStatement = "UPDATE `client_schedule`.`appointments` SET `Title` = ?, `Description` = ?, " +
                "`Location` = ?, `Type` = ?, `Start` = ?, `End` = ?, `Last_Update` = ?, `Last_Updated_By` = ?, " +
                "`Customer_ID` = ?, `User_ID` = ?, `Contact_ID` = ? WHERE (`Appointment_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setString(1, title.getValue());
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

    public static int resetAutoIncrement() throws SQLException {
        Statement s = JDBC.getConnection().createStatement();

        String sqlStatement1 = "SET @max_value = (SELECT MAX(Appointment_ID) FROM client_schedule.appointments)";
        String sqlStatement2 = "SET @sql = CONCAT('ALTER TABLE client_schedule.appointments AUTO_INCREMENT = ', IFNULL(@max_value + 1, 1))";
        String sqlStatement3 = "PREPARE stmt FROM @sql";
        String sqlStatement4 = "EXECUTE stmt";
        String sqlStatement5 = "DEALLOCATE PREPARE stmt";
        s.addBatch(sqlStatement1);
        s.addBatch(sqlStatement2);
        s.addBatch(sqlStatement3);
        s.addBatch(sqlStatement4);
        s.addBatch(sqlStatement5);
        s.executeBatch();



        return 0;
    }
}
