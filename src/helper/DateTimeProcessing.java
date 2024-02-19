package helper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The DateTimeProcessing class provides utility methods for processing and formatting date-time information.
 */
public class DateTimeProcessing {


    /**
     * Converts the specified UTC date and time to the local time of the provided time zone.
     *
     * @param utcDateTime   The UTC date and time to be converted.
     * @param userTimeZone  The target time zone for the conversion.
     * @return The LocalDateTime representation of the converted date and time.
     */
    public static LocalDateTime convertUTCToLocal(LocalDateTime utcDateTime, ZoneId userTimeZone) {
//        System.out.println("userTimeZone.getId(): " + userTimeZone.getId());
//        System.out.println("utcDateTime "+ utcDateTime);

        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));

//        System.out.println("utcDateTime.atZone(ZoneId.of(\"UTC\")): " +utcZonedDateTime);
//        System.out.println("utcZonedDateTime.withZoneSameInstant(userTimeZone): "+utcZonedDateTime.withZoneSameInstant(userTimeZone));
//        System.out.println("utcZonedDateTime.withZoneSameInstant(userTimeZone).toLocalDateTime(): "+utcZonedDateTime.withZoneSameInstant(userTimeZone).toLocalDateTime());

        return utcZonedDateTime.withZoneSameInstant(userTimeZone).toLocalDateTime();
    }




    /**
     * Converts the specified local date and time to UTC based on the provided time zone.
     *
     * @param localDateTime The local date and time to be converted.
     * @param userTimeZone  The time zone of the local date and time.
     * @return The LocalDateTime representation of the converted UTC date and time.
     */
    public static LocalDateTime convertLocalToUTC(LocalDateTime localDateTime, ZoneId userTimeZone) {
        ZonedDateTime localZonedDateTime = localDateTime.atZone(userTimeZone);
//        System.out.println("localDateTime.atZone(userTimeZone) "+localDateTime.atZone(userTimeZone));
//        System.out.println("localZonedDateTime.withZoneSameInstant(ZoneId.of(\"UTC\")).toLocalDateTime() "+localZonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());

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
     * Creates a LocalDateTime object from the specified date and time string in UTC format.
     *
     * @param dateTime The date and time string in UTC format ("yyyy-MM-dd HH:mm:ss").
     * @return The LocalDateTime representation of the specified date and time in the user's timezone.
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

    /**
     * Formats the specified LocalDateTime object to a string using the pattern "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted date and time string.
     */
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
     * @param localDate The date in the format "yyyy-MM-dd".
     * @param localTime The time in the format "HH:mm:ss".
     * @return True if the date-time is outside business hours; otherwise, false.
     */
    public static boolean isOutsideBusinessHours(LocalDate localDate, LocalTime localTime) {
         ZoneId etTimeZone = ZoneId.of("America/New_York");
        LocalTime businessStartTime = LocalTime.of(8, 0); // 8:00 AM ET
        LocalTime businessEndTime = LocalTime.of(22, 0); // 10:00 PM ET

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

    /**
     * Checks if the specified end time is after the specified start time.
     *
     * @param startTime The start time.
     * @param endTime   The end time.
     * @return True if the end time is after the start time, false otherwise.
     */
    public static boolean isValidAppointmentEndDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        // Check if the start time is before the end time
        if (startTime.isBefore(endTime)) {
            return true;
        } else {
            return false;
        }
    }

}
