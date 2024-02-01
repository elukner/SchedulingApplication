package helper;

/**
 * Project: SchedulingApplication
 * Package: helper
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Utility class for time processing operations.
 */
public class TimeProcessing {


    private static LocalTime createLocalTime(String time) {
        // Sample appointment time in UTC
        LocalTime utcAppointmentTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));

        // User's timezone (replace with the actual user's timezone)
        ZoneId userTimeZone = ZoneId.systemDefault();

        // Adjust the appointment time to the user's timezone
        ZonedDateTime userZonedDateTime = ZonedDateTime.of(1970, 1, 1, utcAppointmentTime.getHour(), utcAppointmentTime.getMinute(), utcAppointmentTime.getSecond(), 0, ZoneId.of("UTC"))
                .withZoneSameInstant(userTimeZone);
        LocalTime userAppointmentTime = userZonedDateTime.toLocalTime();

        return userAppointmentTime;
    }

    public static ObservableList<LocalTime> createLocalTimeList(ObservableList<String> times) {
        ObservableList<LocalTime> adjustedDateTimes = FXCollections.observableArrayList();

        for (String time : times) {
            adjustedDateTimes.add(createLocalTime(time));
        }

        return adjustedDateTimes;
    }

    /**
     * Parses the time component from the provided date-time string.
     *
     * @param dateTime The date-time string in "yyyy-MM-dd HH:mm:ss" format.
     * @return LocalTime object representing the parsed time.
     */
    public static LocalTime getTime(String dateTime){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(dateTime.substring(11), timeFormatter);
        return localTime;
    }


    /**
     * Gets the time component from the provided date-time string.
     *
     * @param dateTime The date-time string in "yyyy-MM-dd HH:mm:ss" format.
     * @return String representation of the time with correct seconds.
     */
    public static String getTimeFromDateTime(String dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);

        return getCorrectTimeSeconds(LocalTime.of(localDateTime.getHour(),localDateTime.getMinute(),localDateTime.getMinute()));
    }

    /**
     * Gets the correctly formatted time with seconds.
     *
     * @param time LocalTime object.
     * @return String representation of the time with correct seconds.
     */
    public static String getCorrectTimeSeconds(LocalTime time){

        if(time.getSecond()!=0){
            return time.toString();
        }

        return (time) +":00";
    }

    /**
     * Gets the correctly formatted time with seconds from a string.
     *
     * @param time The time string in "HH:mm:ss" format.
     * @return String representation of the time with correct seconds.
     */

    public static String getCorrectTimeSeconds(String time){

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(time.substring(5), timeFormatter);

        if(localTime.getSecond()!=0){
            return localTime.toString();
        }

        return (localTime) +":00";
    }

    /**
     * Parses the time from the provided date-time string.
     *
     * @param dateTime The date-time string in "HH:mm:ss" format.
     * @return LocalTime object representing the parsed time.
     */
    public static LocalTime getFormatedTime(String dateTime){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(dateTime, timeFormatter);
        return localTime;
    }

    /**
     * Generates a list of business hours in one-hour intervals.
     *
     * @return List of LocalTime objects representing business hours.
     */
    public static List<LocalTime> generateBusinessHours(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<LocalTime> timeOptions = new ArrayList<>();

        // Define business hours
        LocalTime businessStartTime = LocalTime.of(8, 0,0);
        LocalTime businessEndTime = LocalTime.of(22, 0,0);


        // Generate time options within business hours
        LocalTime currentTime = businessStartTime;
        while (currentTime.isBefore(businessEndTime)) {

            timeOptions.add(currentTime);
            currentTime = currentTime.plusHours(1); // Move to the next hour
        }

        return timeOptions;
    }

    /**
     * Generates a list of business hours in one-hour intervals with correct seconds.
     *
     * @return List of String objects representing business hours with correct seconds.
     */
    public static List<String> generateLocalBusinessHoursWithSeconds(){

        List<LocalTime> timeOptions = generateBusinessHours();
        List<String> localTimeOptions  = new ArrayList<>();

        for (LocalTime time : timeOptions) {
            localTimeOptions.add(getCorrectTimeSeconds(convertETToLocalTime(time)));
        }

        return localTimeOptions;
    }


    /**
     * Generates a list of hours in one-hour intervals.
     *
     * @return List of LocalTime objects representing hours.
     */
    public static List<LocalTime> generateHours(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<LocalTime> timeOptions = new ArrayList<>();

        // Define business hours
        LocalTime businessStartTime = LocalTime.of(0, 0,0);
        LocalTime businessEndTime = LocalTime.of(23, 0,0);


        // Generate time options within business hours
        LocalTime currentTime = businessStartTime;
        while (currentTime.isBefore(businessEndTime)) {
            timeOptions.add(currentTime);
            currentTime = currentTime.plusHours(1); // Move to the next hour
        }

        return timeOptions;

//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        List<LocalTime> localTimeOptions = new ArrayList<>();
//
//        // Define business hours
//        LocalTime businessStartTime = LocalTime.of(0, 0, 0);
//        LocalTime businessEndTime = LocalTime.of(23, 0, 0);
//
//        // Get the user's time zone
//        ZoneId userTimeZone = ZoneId.systemDefault();
//
//        // Generate local time options within business hours
//        LocalTime currentTime = businessStartTime;
//        while (currentTime.isBefore(businessEndTime)) {
//            ZonedDateTime userZonedDateTime = ZonedDateTime.of(1970, 1, 1, currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond(), 0, ZoneId.of("UTC"))
//                    .withZoneSameInstant(userTimeZone);
//            LocalTime userLocalTime = userZonedDateTime.toLocalTime();
//
//            localTimeOptions.add(userLocalTime);
//            currentTime = currentTime.plusHours(1); // Move to the next hour
//        }
//
//        return localTimeOptions;
    }

    /**
     * Generates a list of hours in one-hour intervals with correct seconds.
     *
     * @return List of String objects representing hours with correct seconds.
     */
    public static List<String> generateLocalHoursWithSeconds(){

        List<LocalTime> timeOptions = generateHours();
        List<String> localTimeOptions  = new ArrayList<>();

        for (LocalTime time : timeOptions) {
//            localTimeOptions.add(getCorrectTimeSeconds(convertETToLocalTime(time)));
            localTimeOptions.add(getCorrectTimeSeconds(time));
        }

        return localTimeOptions;
    }

    /**
     * Converts Eastern Time (ET) to the local time zone.
     *
     * @param etTime LocalTime object representing Eastern Time.
     * @return LocalTime object representing the converted local time.
     */
    public static LocalTime convertETToLocalTime(LocalTime etTime) {
        // Specify the time zone for Eastern Time
        ZoneId etTimeZone = ZoneId.of("America/New_York");

        // Create a ZonedDateTime with the given ET time and time zone
        ZonedDateTime etDateTime = ZonedDateTime.of(
                ZonedDateTime.now().toLocalDate(),
                etTime,
                etTimeZone
        );

        // Convert the ZonedDateTime to the system default time zone
        ZonedDateTime localDateTime = etDateTime.withZoneSameInstant(ZoneId.systemDefault());

        // Extract the local time
        return localDateTime.toLocalTime();
    }

    public static boolean isValidAppointmentEndTime(LocalDateTime startTime, LocalDateTime endTime) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime startTime = LocalDateTime.parse(startDateTimeString, dateTimeFormatter);
//        LocalDateTime endTime = LocalDateTime.parse(endDateTimeString, dateTimeFormatter);

        // Check if the start time is before the end time
        if (startTime.isBefore(endTime)) {
            return true;
        } else {
            System.out.println("Error: Start time must be before the end time.");
            return false;
        }
    }


}
