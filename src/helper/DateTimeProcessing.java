package helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeProcessing {

    public static String getCurrentLocalDateTimeString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
