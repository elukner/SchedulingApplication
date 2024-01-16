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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

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
 */

public class DateProcessing {
    public static String getMonth(String dateTime){
        //Getting the LocalDateTime Objects from String values
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        String startTimeTxt = dateTimeFormatter.format(LocalDateTime.now());

        LocalDateTime ldtStart = LocalDateTime.parse(startTimeTxt, dateTimeFormatter);


        //Getting the day of the week
        //System.out.println(ldtStart.getDayOfWeek());
        return ldtStart.getMonth().toString();
    }

    public static LocalDate getDate(String dateTime){

        //Showing how to parse the Date/Time String
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateTime.substring(0, 10), dateTimeFormatter);
        // System.out.println("The local date is " + localDate);


        //Getting the day of the week
        //System.out.println(ldtStart.getDayOfWeek());
        return localDate;
    }
}
