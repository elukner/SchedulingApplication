package test;

import helper.DateProcessing;
import helper.DateTimeProcessing;
import helper.TimeProcessing;
import org.junit.jupiter.api.Test;
import helper.FileIOManager;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        System.out.println(DateProcessing.getDate("2020-05-29 12:00:00"));

    }

    @Test
    public void testGetTime() {
        System.out.println(TimeProcessing.getTime("2020-05-29 12:00:09"));

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

}
