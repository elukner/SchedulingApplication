package helper;

/**
 * Project: SchedulingApplication
 * Package: helper
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import java.sql.*;


/**
 * The JDBC class provides methods for managing database connections,
 * executing queries, and handling database-related tasks that don't fit
 * directly within DAO classes. It is an abstract class serving as a utility
 * for database-related operations.
 * <p>
 * The class includes methods for opening, getting, and closing a database connection.
 */
public abstract class JDBC {

    /**
     * The protocol used for the JDBC connection.
     */
    private static final String protocol = "jdbc";

    /**
     * The vendor information for the JDBC connection.
     */
    private static final String vendor = ":mysql:";

    /**
     * The location of the database server.
     */
    private static final String location = "//localhost/";

    /**
     * The name of the database.
     */
    private static final String databaseName = "client_schedule";

    /**
     * The complete JDBC URL for connecting to the database, including additional connection settings.
     */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL

    /**
     * The reference to the JDBC driver class.
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference

    /**
     * The username used for authenticating the database connection.
     */
    private static final String userName = "sqlUser"; // Username

    /**
     * The password used for authenticating the database connection.
     */
    private static String password = "Passw0rd!"; // Password
    /**
     * Represents a static field providing a connection to a database.
     * This field is used for managing database connections in the application.
     */
    public static Connection connection;  // Connection Interface

    /**
     * Opens a connection to the MySQL database using the specified URL,
     * username, and password. Prints a success message if the connection is
     * established; otherwise, prints an error message.
     */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            setUTCTimezone();
            System.out.println("This connection successful!");

        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }


    /**
     * Getter for retrieving the database connection once it is already open.
     *
     * @return connection Connection Interface representing the open database connection.
     */
    public static Connection getConnection(){

        return connection;
    }

    /**
     * Closes the open database connection. Prints a success message if the
     * connection is closed; otherwise, prints an error message.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("This connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private static int[] setUTCTimezone() throws SQLException {
        Statement statement = JDBC.getConnection().createStatement();

        String sqlStatement = "SET GLOBAL time_zone =  '+00:00'";
        statement.addBatch(sqlStatement);
        statement.executeBatch();

        return statement.executeBatch();
    }
}
