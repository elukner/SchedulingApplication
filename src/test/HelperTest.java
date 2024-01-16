package test;

import helper.DateProcessing;
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
}
