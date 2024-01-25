package helper;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

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
 */

public class TimeProcessing {


    public static Timestamp createTimeStamp(){
        //Getting the LocalDateTime Objects from String values
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        String startTimeTxt = dateTimeFormatter.format(LocalDateTime.now());

        LocalDateTime ldtStart = LocalDateTime.parse(startTimeTxt, dateTimeFormatter);


        //Showing how to parse the Date/Time String
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(startTimeTxt.substring(0, 10), dFormatter);
       // System.out.println("The local date is " + localDate);

        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("kk:mm:ss.S");
        LocalTime localTime = LocalTime.parse(startTimeTxt.substring(11), tFormatter);
        //System.out.println("The local time is " + localTime);

        //Getting the day of the week
        //System.out.println(ldtStart.getDayOfWeek());

        //Convert to a ZonedDate Time in UTC
        ZoneId zid = ZoneId.systemDefault();

        ZonedDateTime zdtStart = ldtStart.atZone(zid);
        //System.out.println("Local Time: " + zdtStart);
        ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
       // System.out.println("Zoned time: " + utcStart);
        ldtStart = utcStart.toLocalDateTime();
        //System.out.println("Zoned time with zone stripped:" + ldtStart);
        //Create Timestamp values from Instants to update database
        Timestamp startsqlts = Timestamp.valueOf(ldtStart); //this value can be inserted into database
        //System.out.println("Timestamp to be inserted: " +startsqlts);
        return startsqlts;
    }

    public static LocalTime getTime(String dateTime){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(dateTime.substring(11), timeFormatter);
        return localTime;
    }



    public static String getTimeFromDateTime(String dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);

        return getCorrectTimeSeconds(LocalTime.of(localDateTime.getHour(),localDateTime.getMinute(),localDateTime.getMinute()));
    }

    public static String getCorrectTimeSeconds(LocalTime time){

        if(time.getSecond()!=0){
            return time.toString();
        }

        return (time) +":00";
    }

    public static String getCorrectTimeSeconds(String time){

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder().appendPattern("HH:mm:ss")
//                .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
//                .toFormatter();
        LocalTime localTime = LocalTime.parse(time.substring(5), timeFormatter);

        if(localTime.getSecond()!=0){
            return localTime.toString();
        }

        return (localTime) +":00";
    }

    public static LocalTime getFormatedTime(String dateTime){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder().appendPattern("HH:mm:ss")
//                .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
//                .toFormatter();
        LocalTime localTime = LocalTime.parse(dateTime, timeFormatter);
        //System.out.println("The local time is " + localTime);

        //Getting the day of the week
        //System.out.println(ldtStart.getDayOfWeek());
        return localTime;
    }

    /**
     *scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
     * @return
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
     *scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
     * @return
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
     *scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
     * @return
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
    }

    /**
     *scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
     * @return
     */
    public static List<String> generateLocalHoursWithSeconds(){

        List<LocalTime> timeOptions = generateHours();
        List<String> localTimeOptions  = new ArrayList<>();

        for (LocalTime time : timeOptions) {
            localTimeOptions.add(getCorrectTimeSeconds(convertETToLocalTime(time)));
        }

        return localTimeOptions;
    }

    /**
     *scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. ET, including weekends
     * @return
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


}
