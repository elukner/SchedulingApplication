package test;

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
}
