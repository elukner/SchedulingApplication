package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeProcessing {

    public static String getCurrentLocalDateTimeString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static Timestamp getCurrentLocalDateTimeTimeStamp(String dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    public static String getFormatedDateTimeString(Timestamp dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime.toString().substring(0,19), dateTimeFormatter);
        return dateTimeFormatter.format(localDateTime);
    }



    public static LocalDateTime convertTimeToUTCThenLocal() {
        //c.  Write code that enables the user to adjust appointment times. While the appointment times should be stored in
// Coordinated Universal Time (UTC), they should be automatically and consistently updated according to the local
// time zone set on the user’s computer wherever appointments are displayed in the application..Note: There are up to
// three time zones in effect. Coordinated Universal Time (UTC) is used for storing the time in the database,
// the user’s local time is used for display purposes, and Eastern Time (ET) is used for the company’s office hours.
// Local time will be checked against ET business hours before they are stored in the database as UTC.

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utcZonedDateTime.toLocalDateTime();
        System.out.println("Zoned Time: " + Timestamp.valueOf(ldtIn));

        ZonedDateTime zonedDateTimeOut = ldtIn.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTimeOutToLocalTZ = zonedDateTimeOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime localDateTimeOutFinal = zonedDateTimeOutToLocalTZ.toLocalDateTime();
        System.out.println("Local Time: " + Timestamp.valueOf(localDateTimeOutFinal));
        return localDateTimeOutFinal;


    }

    public static String convertTimeToUTCThenLocal(String dateTime) {
        //c.  Write code that enables the user to adjust appointment times. While the appointment times should be stored in
// Coordinated Universal Time (UTC), they should be automatically and consistently updated according to the local
// time zone set on the user’s computer wherever appointments are displayed in the application..Note: There are up to
// three time zones in effect. Coordinated Universal Time (UTC) is used for storing the time in the database,
// the user’s local time is used for display purposes, and Eastern Time (ET) is used for the company’s office hours.
// Local time will be checked against ET business hours before they are stored in the database as UTC.

//        Timestamp dateTimeTimeStamp = getCurrentLocalDateTimeTimeStamp(dateTime);
//        LocalDateTime localDateTime = dateTimeTimeStamp.toLocalDateTime();
//        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
//        ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
//        LocalDateTime ldtIn = utcZonedDateTime.toLocalDateTime();
        Timestamp dateTimeTimeStamp = getCurrentLocalDateTimeTimeStamp(dateTime);
        LocalDateTime ldtIn = dateTimeTimeStamp.toLocalDateTime();
        //System.out.println("Zoned Time: " + Timestamp.valueOf(ldtIn));

        ZonedDateTime zonedDateTimeOut = ldtIn.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTimeOutToLocalTZ = zonedDateTimeOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime localDateTimeOutFinal = zonedDateTimeOutToLocalTZ.toLocalDateTime();
        Timestamp timestamp = Timestamp.valueOf(localDateTimeOutFinal);
        //System.out.println("Local Time: " + timestamp);
        return getFormatedDateTimeString(timestamp);


    }


    /**
     * Convert to a ZonedDate Time in UTC
     * @param dateTime
     * @return
     */
    public static String convertTimeToLocalThenUTC(String dateTime) {
//        c.  Write code that enables the user to adjust appointment times. While the appointment times should be stored in
// Coordinated Universal Time (UTC), they should be automatically and consistently updated according to the local
// time zone set on the user’s computer wherever appointments are displayed in the application..Note: There are up to
// three time zones in effect. Coordinated Universal Time (UTC) is used for storing the time in the database,
// the user’s local time is used for display purposes, and Eastern Time (ET) is used for the company’s office hours.
// Local time will be checked against ET business hours before they are stored in the database as UTC.

        Timestamp dateTimeTimeStamp = getCurrentLocalDateTimeTimeStamp(dateTime);
        LocalDateTime localDateTime = dateTimeTimeStamp.toLocalDateTime();
        ZonedDateTime zdtStart = localDateTime.atZone(ZoneId.systemDefault());
        //System.out.println("Local Time: " + Timestamp.valueOf(localDateTime));



        ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
        localDateTime = utcStart.toLocalDateTime();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        //System.out.println("Zoned time: " + timestamp);

        return getFormatedDateTimeString(timestamp);


    }

}
