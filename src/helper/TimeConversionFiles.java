package helper;

/**
 * Project: SchedulingApplication
 * Package: helper
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

/**
 * And the helper package will hold helper classes
 * that handle tasks such as
 * Date / Time processing, List management, etc.
 *
 * Add a utility or utils package to hold
 * classes for your; Database Connection,
 * Query Execution, Collection (ObservarbleArrayList)
 * Management, Functional Interfaces, and time conversion files.
 *
 * Date/Time Operations: Conversions between different time zones,
 * date formatting, or any other date/time-related functionalities.
 *
 * Other General Utilities: Any other functions that offer
 * support across different parts of the application
 * but don't specifically belong to the DAO or controller logic.
 *
 * c.  Write code that enables the user to adjust appointment times. While the appointment times should be
 * stored in Coordinated Universal Time (UTC), they should be automatically and consistently updated
 * according to the local time zone set on the user’s computer wherever appointments are displayed in the application.
 *
 *
 *
 * Note: There are up to three time zones in effect. Coordinated Universal Time (UTC) is used for
 * storing the time in the database, the user’s local time is used for display purposes,
 * and Eastern Time (ET) is used for the company’s office hours. Local time will be checked against
 * ET business hours before they are stored in the database as UTC.
 */

public class TimeConversionFiles {

}
