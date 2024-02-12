package dao;

/**
 * Project: SchedulingApplication
 * Package: dao
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import helper.DateProcessing;
import helper.DateTimeProcessing;
import helper.JDBC;
import helper.TimeProcessing;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


/**
 * This utility class AppointmentsDaoImpl interacts with the client_schedule database to
 * perform CRUD operations and handle data retrieval on the appointments table.
 **/
public class AppointmentsDaoImpl {

    /**
     * Retrieves a list of upcoming appointments scheduled to start within the next 15 minutes.
     *
     * @param dateTime The current date and time for reference.
     * @return appointmentsList An ObservableList of Appointments representing the upcoming appointments.
     */
    public static ObservableList<Appointments> getUpcomingAppointmentWithin15Min(String dateTime) {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT *\n" +
                    "FROM client_schedule.appointments\n" +
                    "WHERE Start BETWEEN ? AND DATE_ADD(?, INTERVAL 15 MINUTE)";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
            preparedStatement.setString(1,dateTime);
            preparedStatement.setString(2,dateTime);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set and create Appointments objects
            while (resultSet.next()) {
                int appointmentID = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                Timestamp start = resultSet.getTimestamp("Start");
                Timestamp end = resultSet.getTimestamp("End");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title), description,
                        location, type, start.toLocalDateTime(),
                        end.toLocalDateTime(), createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }

    /**
     * Checks whether there are overlapping appointments for a specific customer within a given time range.
     *
     * @param customerId     The ID of the customer.
     * @param startDateTime  The start date and time of the appointment.
     * @param endDateTime    The end date and time of the appointment.
     * @return true if there are overlapping appointments, {@code false} otherwise.
     * @throws SQLException If a database access error occurs.
     */
    public static boolean hasOverlappingAppointments(int customerId, LocalDateTime startDateTime, LocalDateTime endDateTime) throws SQLException {
        // Query to check for overlapping appointments
        String sql = "SELECT COUNT(*) FROM client_schedule.appointments " +
                "WHERE Customer_ID = ? " +
                "AND ? <= End AND ? >= Start";

        try  {
            PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setString(2, startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            statement.setString(3, endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int overlappingAppointmentsCount = resultSet.getInt(1);
                    return overlappingAppointmentsCount > 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    /**
     * Checks whether there are overlapping appointments for a specific customer within a given time range,
     * excluding the specified appointment ID.
     *
     * @param customerId     The ID of the customer.
     * @param startDateTime  The start date and time of the appointment.
     * @param endDateTime    The end date and time of the appointment.
     * @param appointmentID  The ID of the appointment to exclude from the check.
     * @return true if there are overlapping appointments, {@code false} otherwise.
     * @throws SQLException If a database access error occurs.
     */
    public static boolean hasOverlappingAppointments(int customerId, LocalDateTime startDateTime, LocalDateTime endDateTime, int appointmentID) throws SQLException {
        // Query to check for overlapping appointments, excluding the specified appointment ID
        String sql = "SELECT COUNT(*) FROM client_schedule.appointments " +
                "WHERE Customer_ID = ? " +
                "AND Appointment_ID <> ? " +
                "AND ? <= End AND ? >= Start";

        try {
            PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, appointmentID);
            statement.setString(3, startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            statement.setString(4, endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int overlappingAppointmentsCount = resultSet.getInt(1);
                    return overlappingAppointmentsCount > 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


    /**
     * Retrieves a list of appointments associated with the specified customer ID from the appointments table
     * in the client_schedule database.
     *
     * @param customerID The unique identifier of the customer for whom to retrieve appointments.
     * @return An ObservableList containing the retrieved appointments.
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
                Timestamp start = resultSet.getTimestamp("Start");
                Timestamp end = resultSet.getTimestamp("End");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title),
                        description, location, type, start.toLocalDateTime(),
                        end.toLocalDateTime(), createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }

    /**
     * Retrieves a list of appointments scheduled for the current month.
     *
     * @return An ObservableList of Appointments for the current month.
     */
    public static ObservableList<Appointments> getAllAppointmentsByMonth() {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            // SQL query to select appointments for the current month
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
                Timestamp start = Timestamp.valueOf(DateTimeProcessing.convertUTCToLocal(resultSet.getTimestamp( "Start").toLocalDateTime(),ZoneId.systemDefault()));
                Timestamp end = Timestamp.valueOf(DateTimeProcessing.convertUTCToLocal(resultSet.getTimestamp( "End").toLocalDateTime(),ZoneId.systemDefault()));
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title),
                        description, location, type, start.toLocalDateTime(),
                        end.toLocalDateTime(), createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }

    /**
     * Retrieves a list of appointments scheduled for the current week.
     *
     * @return An ObservableList of Appointments for the current week.
     */
    public static ObservableList<Appointments> getAllAppointmentsByWeek() {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        try {
            // SQL query to select appointments for the current week
            String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE WEEK(DATE(`Start`))=WEEK(CURDATE())";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int appointmentID = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                Timestamp start = resultSet.getTimestamp("Start");
                Timestamp end = resultSet.getTimestamp("End");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title),
                        description, location, type, start.toLocalDateTime(),
                        end.toLocalDateTime(), createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
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
                Timestamp start = resultSet.getTimestamp("Start");
                Timestamp end = resultSet.getTimestamp("End");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title),
                        description, location, type, start.toLocalDateTime(),
                        end.toLocalDateTime(), createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
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
    public static ObservableList<Appointments> getAllAppointmentsByLocalTimeZones() {
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
                Timestamp start = Timestamp.valueOf(DateTimeProcessing.convertUTCToLocal(resultSet.getTimestamp( "Start").toLocalDateTime(),ZoneId.systemDefault()));
                Timestamp end = Timestamp.valueOf(DateTimeProcessing.convertUTCToLocal(resultSet.getTimestamp( "End").toLocalDateTime(),ZoneId.systemDefault()));
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                Appointments appointment = new Appointments(appointmentID, new ReadOnlyStringWrapper(title),
                        description, location, type,start.toLocalDateTime() ,
                       end.toLocalDateTime()  , createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }

    /**
     * This method inserts an appointment into the appointments table in the client_schedule database.
     *
     * @param title           The title of the appointment.
     * @param description     The description of the appointment.
     * @param location        The location of the appointment.
     * @param type            The type of the appointment.
     * @param start           The start time of the appointment.
     * @param end             The end time of the appointment.
     * @param createdBy       The user who created the appointment.
     * @param lastUpdatedBy   The user who last updated the appointment.
     * @param customerID      The ID of the associated customer.
     * @param userID          The ID of the associated user.
     * @param contactID       The ID of the associated contact.
     * @return Either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     *         for SQL statements that return nothing.
     * @throws SQLException  java.sql.SQLException – if a database access error occurs;
     *                      this method is called on a closed PreparedStatement or
     *                      the SQL statement returns a ResultSet object.
     *                      java.sql.SQLTimeoutException – when the driver has determined
     *                      that the timeout value that was specified by the setQueryTimeout
     *                      method has been exceeded and has at least attempted to cancel
     *                      the currently running Statement.
     */
    public static int insertAppointments(StringProperty title, String description, String location, String type,
                                         LocalDateTime start,
                                         LocalDateTime end, String createdBy, String lastUpdatedBy,
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
        preparedStatement.setTimestamp(5, Timestamp.valueOf(DateTimeProcessing.convertLocalToUTC(start, ZoneId.systemDefault())));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(DateTimeProcessing.convertLocalToUTC(end,ZoneId.systemDefault())));
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
     * @param appointmentID   The ID of the appointment to be updated.
     * @param title           The title of the appointment.
     * @param description     The description of the appointment.
     * @param location        The location of the appointment.
     * @param type            The type of the appointment.
     * @param start           The start time of the appointment.
     * @param end             The end time of the appointment.
     * @param lastUpdate      The timestamp indicating the last update of the appointment.
     * @param lastUpdatedBy   The user who last updated the appointment.
     * @param customerID      The ID of the associated customer.
     * @param userID          The ID of the associated user.
     * @param contactID       The ID of the associated contact.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     * for SQL statements that return nothing
     * @throws SQLException java.sql.SQLException – if a database access error occurs; this method is called on a
     *                      closed PreparedStatement or the SQL statement returns a ResultSet object
     *                      java.sql.SQLTimeoutException – when the driver has determined that the timeout value
     *                      that was specified by the setQueryTimeout method has been exceeded and
     *                      has at least attempted to cancel the currently running Statement
     */
    public static int updateAppointment(int appointmentID, StringProperty title, String description, String location, String type,
                                        LocalDateTime start,
                                        LocalDateTime end, String lastUpdate, String lastUpdatedBy,
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
        preparedStatement.setTimestamp(5, Timestamp.valueOf(DateTimeProcessing.convertLocalToUTC(start, ZoneId.systemDefault())));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(DateTimeProcessing.convertLocalToUTC(end,ZoneId.systemDefault())));
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
     * @param appointmentID The ID of the appointment.
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

    /**
     * Resets the auto-increment value of the "Appointment_ID" column in the "client_schedule.appointments" table.
     * This method uses SQL statements to find the maximum current value of the "Appointment_ID" column,
     * and then adjusts the auto-increment value accordingly to avoid potential conflicts.
     * Note: This operation is typically used when deleting records with higher IDs,
     * ensuring that the next inserted record starts with an appropriate ID.
     *
     * @return An integer indicating the success of the operation (0 for success).
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public static int[] resetAutoIncrement() throws SQLException {
        Statement statement = JDBC.getConnection().createStatement();

        String sqlStatement1 = "SET @max_value = (SELECT MAX(Appointment_ID) FROM client_schedule.appointments)";
        String sqlStatement2 = "SET @sql = CONCAT('ALTER TABLE client_schedule.appointments AUTO_INCREMENT = ', IFNULL(@max_value + 1, 1))";
        String sqlStatement3 = "PREPARE stmt FROM @sql";
        String sqlStatement4 = "EXECUTE stmt";
        String sqlStatement5 = "DEALLOCATE PREPARE stmt";
        statement.addBatch(sqlStatement1);
        statement.addBatch(sqlStatement2);
        statement.addBatch(sqlStatement3);
        statement.addBatch(sqlStatement4);
        statement.addBatch(sqlStatement5);
        statement.executeBatch();



        return statement.executeBatch();
    }
}
