package helper;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

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

    public void convertTimeToUTCThenLocal() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime ldt = ts.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utczdt.toLocalDateTime();

        ZonedDateTime zdtOut = ldtIn.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldtOutFinal = zdtOutToLocalTZ.toLocalDateTime();
    }

    public static Timestamp createTimeStamp(){
        //Getting the LocalDateTime Objects from String values
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        String txtStartTime = "2017-03-29 12:00:00.0";

        LocalDateTime ldtStart = LocalDateTime.parse(txtStartTime, df);


        //Showing how to parse the Date/Time String
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(txtStartTime.substring(0, 10), dFormatter);
        System.out.println("The local date is " + localDate);

        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("kk:mm:ss.S");
        LocalTime localTime = LocalTime.parse(txtStartTime.substring(11), tFormatter);
        System.out.println("The local time is " + localTime);

        //Getting the day of the week
        System.out.println(ldtStart.getDayOfWeek());

        //Convert to a ZonedDate Time in UTC
        ZoneId zid = ZoneId.systemDefault();

        ZonedDateTime zdtStart = ldtStart.atZone(zid);
        System.out.println("Local Time: " + zdtStart);
        ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("Zoned time: " + utcStart);
        ldtStart = utcStart.toLocalDateTime();
        System.out.println("Zoned time with zone stripped:" + ldtStart);
        //Create Timestamp values from Instants to update database
        Timestamp startsqlts = Timestamp.valueOf(ldtStart); //this value can be inserted into database
        System.out.println("Timestamp to be inserted: " +startsqlts);
        return startsqlts;
    }

}
