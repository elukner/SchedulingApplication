package test;

import helper.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class HelperTest {
    @Test
    public void testReadFile() throws FileNotFoundException {
        System.out.println(FileIOManager.readFile());

    }



    @Test
    public void testWriteToCurrentFile() throws IOException {
        //FileIOManager.writeToCurrentFile();

    }


    @Test
    public void testGetMonth() {
        System.out.println(DateProcessing.getMonth("2020-05-29 12:00:00"));

    }

    @Test
    public void testGetDate() {
        System.out.println(DateProcessing.getDate("2020-05-29"));

    }

    @Test
    public void testGetDateFromDateTime() {
        System.out.println(DateProcessing.getDateFromDateTime("2020-05-29 12:00:00"));

    }

    @Test
    public void testGetTime() {
        System.out.println(TimeProcessing.getTime("12:00:00"));

    }

    @Test
    public void testGetTimeFromDateTime() {
        System.out.println(TimeProcessing.getTimeFromDateTime("2020-05-29 12:00:00"));

    }

    @Test
    public void testGetCurrentLocalDateTimeString() {
        System.out.println(DateTimeProcessing.getCurrentLocalDateTimeString());

    }

    @Test
    public void testGetFormatedTime() {
        System.out.println(DateProcessing.getFormatedDate("1/17/2024").toString());

    }

    @Test
    public void testGetFormatedDate() {
        System.out.println(DateTimeProcessing.getCurrentLocalDateTimeString());

    }

    @Test
    public void testConvertTimeToUTCThenLocal() {
        System.out.println(DateTimeProcessing.convertTimeToUTCThenLocal("2024-01-17 12:00:00"));

    }
    @Test
    public void testConvertTimeToLocalThenUTC() {
        System.out.println(DateTimeProcessing.convertTimeToLocalThenUTC("2024-01-17 04:00:00"));

    }

    @Test
    public void testConvertTimeToUTCThenLocalt() {
        System.out.println(DateTimeProcessing.convertTimeToUTCThenLocal());

    }

    @Test
    public void testGenerateBusinessHours() {
        //System.out.println(TimeProcessing.generateBusinessHours());
        List<String> timeOptions = TimeProcessing.generateLocalBusinessHoursWithSeconds();

        // Print the generated time options
        for (String time : timeOptions) {
            System.out.println(time);
        }
        // Print the generated time options
        for (String time : timeOptions) {
            System.out.println(time + DateTimeProcessing.isOutsideBusinessHours("2024-01-20", time));
        }

    }





}
