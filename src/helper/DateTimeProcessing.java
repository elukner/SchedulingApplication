package helper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The DateTimeProcessing class provides utility methods for processing and formatting date-time information.
 */
public class DateTimeProcessing {


    /**
     * Method to convert UTC to local time
     * @param utcDateTime
     * @param userTimeZone
     * @return
     */
    public static LocalDateTime convertUTCToLocal(LocalDateTime utcDateTime, ZoneId userTimeZone) {
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));
        return utcZonedDateTime.withZoneSameInstant(userTimeZone).toLocalDateTime();
    }


    /**
     * Method to convert local time to UTC
     * @param localDateTime
     * @param userTimeZone
     * @return
     */
    public static LocalDateTime convertLocalToUTC(LocalDateTime localDateTime, ZoneId userTimeZone) {
        ZonedDateTime localZonedDateTime = localDateTime.atZone(userTimeZone);
        return localZonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }

    /**
     * Creates a Timestamp representing the current date and time in UTC.
     *
     * @return Timestamp representing the current date and time in UTC.
     */
    public static Timestamp createTimeStamp(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(currentDateTime, ZoneId.systemDefault());
        ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime utcLocalDateTime = utcZonedDateTime.toLocalDateTime();
        return Timestamp.valueOf(utcLocalDateTime);
    }

    /**
     *
     */
    public static LocalDateTime createLocalDateTime(String dateTime){
        // Sample appointment time in UTC
        LocalDateTime utcAppointmentTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // User's timezone (replace with the actual user's timezone)
        ZoneId userTimeZone = ZoneId.systemDefault();

        // Adjust the appointment time to the user's timezone
        ZonedDateTime userZonedDateTime = utcAppointmentTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(userTimeZone);
        LocalDateTime userAppointmentTime = userZonedDateTime.toLocalDateTime();
        return userAppointmentTime;
    }

    public static String getFormatedDateTime(LocalDateTime dateTime){
        return  dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    /**
     * Retrieves the current date-time in the format "yyyy-MM-dd HH:mm:ss".
     *
     * @return A string representing the current date-time.
     */
    public static String getCurrentLocalDateTimeString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(LocalDateTime.now());
    }



    /**
     * Checks if a given date and time are outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends.
     *
     * @param localDateString The date in the format "yyyy-MM-dd".
     * @param localTimeString The time in the format "HH:mm:ss".
     * @return True if the date-time is outside business hours; otherwise, false.
     */
    public static boolean isOutsideBusinessHours(LocalDate localDate, LocalTime localTime) {
         ZoneId etTimeZone = ZoneId.of("America/New_York");
        LocalTime businessStartTime = LocalTime.of(8, 0); // 8:00 AM ET
        LocalTime businessEndTime = LocalTime.of(22, 0); // 10:00 PM ET


//        // Parse the input string to LocalTime
//        LocalTime localTime = LocalTime.parse(localTimeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
//        LocalDate localDate = LocalDate.parse(localDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Combine the LocalDate and LocalTime to create ZonedDateTime
        ZonedDateTime etDateTime = ZonedDateTime.of(
                localDate,
                localTime,
                ZoneId.systemDefault()
        ).withZoneSameInstant(etTimeZone);

        // Check if the day is a weekend (Saturday or Sunday)
        DayOfWeek dayOfWeek = etDateTime.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

        // Check if the time is outside of business hours
        return isWeekend || etDateTime.toLocalTime().isBefore(businessStartTime)
                || etDateTime.toLocalTime().isAfter(businessEndTime);
    }

}
