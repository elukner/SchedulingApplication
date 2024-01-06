package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsDaoImpl {

    /**
     * retrive list of students from the database
     *
     * @return
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
     * @return
     * @throws SQLException
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
     * @throws SQLException
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
     * @throws SQLException
     */
    public static void selectAppointment(int appointmentID) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE Appointment_ID = ?";
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setInt(1,appointmentID);
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
     * TODO
     *
     * @param appointmentID
     */
    public static int updateAppointment(int appointmentID, String title) throws SQLException {
        String sqlStatement = "UPDATE `client_schedule`.`appointments` SET `Title` = ? WHERE (`Appointment_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);
        preparedStatement.setString(1,title);
        preparedStatement.setInt(2,appointmentID);



        return preparedStatement.executeUpdate();
    }

    /**
     * @param appointmentID
     */
    public static int deleteAppointment(int appointmentID) throws SQLException {
        String sqlStatement = "DELETE FROM `client_schedule`.`appointments` WHERE (`Appointment_ID` = ?)";

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sqlStatement);

        preparedStatement.setInt(1, appointmentID);

        return preparedStatement.executeUpdate();
    }
}