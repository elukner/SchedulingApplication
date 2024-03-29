package helper;
/**
 * Project: SchedulingApplication
 * Package: helper
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DateProcessing class provides utility methods for processing and formatting dates.
 */
public class DateProcessing {

    /**
     * Parses a date string in the format "yyyy-MM-dd" and returns a LocalDate object.
     *
     * @param dateTime The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     */
    public static LocalDate getDate(String dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateTime.substring(0, 10), dateTimeFormatter);
        return localDate;
    }

    /**
     * Parses a date-time string in the format "yyyy-MM-dd HH:mm:ss" and returns a LocalDate object.
     *
     * @param dateTime The date-time string to be parsed.
     * @return A LocalDate object representing the date part of the parsed date-time.
     */
    public static LocalDate getDateFromDateTime(String dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return LocalDate.of(localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth());
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" and returns a LocalDate object.
     *
     * @param dateTime The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     */
    public static LocalDate getFormatedDate(String dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateTime, dateTimeFormatter);
        return localDate;
    }

    /**
     * Checks if the specified end date is after or equal to the given start date.
     *
     * @param startDate The starting date of the appointment.
     * @param endDate   The ending date of the appointment.
     * @return true if the end date is after or equal to the start date, false otherwise.
     */
    public static boolean isValidAppointmentEndDate(LocalDate startDate, LocalDate endDate) {
        // Check if the start date is before or equal to the end date
        return !endDate.isBefore(startDate);
    }

}
