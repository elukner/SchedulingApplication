package helper;

/**
 * Project: SchedulingApplication
 * Package: helper
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */


import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileIOManager class provides methods for reading from and writing to a file named "login_activity.txt."
 */
public class FileIOManager {

    /**
     * Writes user login activity to the "login_activity.txt" file.
     *
     * @param user          The username associated with the login activity.
     * @param loginAttempts The number of login attempts.
     * @param date          The date of the login activity.
     * @param timeStamps    The timestamp of the login activity.
     * @param success       The success status of the login activity.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public static void writeToFile(String user, int loginAttempts, String date, Timestamp timeStamps, String success ) throws IOException {
        File file = new File ("login_activity.txt");
        if(!file.exists()){
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter("login_activity.txt"),true);
                BufferedWriter bufferedWriter = new BufferedWriter(printWriter);
                //out.txt will appear in the project's root directory under NetBeans projects
                //Note that Notepad will not display the following lines on separate lines
                printWriter.append(user+" "+loginAttempts+" "+date+" "+timeStamps+" "+success+"\n");
                printWriter.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileIOManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            writeToCurrentFile(user, loginAttempts, date, timeStamps, success);
        }


    }

    /**
     * Appends user login activity to the existing "login_activity.txt" file.
     *
     * @param user          The username associated with the login activity.
     * @param loginAttempts The number of login attempts.
     * @param date          The date of the login activity.
     * @param timeStamps    The timestamp of the login activity.
     * @param success       The success status of the login activity.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public static void writeToCurrentFile(String user, int loginAttempts, String date, Timestamp timeStamps, String success ) throws IOException {
        File file = new File("login_activity.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(user+" "+loginAttempts+" "+date+" "+timeStamps+" "+success+"\n");

        bufferedWriter.close();
        fileWriter.close();

    }

    /**
     * Deletes the "login_activity.txt" file if it exists.
     *
     * @throws IOException If an I/O error occurs during file deletion.
     */
    public static void deleteCurrentFile() throws IOException {
        File file = new File("login_activity.txt");
        if(file.exists()){
            file.delete();
        }

    }

    /**
     * Reads the contents of the "login_activity.txt" file and returns the last username.
     *
     * @return The last username from the file.
     * @throws FileNotFoundException If the "login_activity.txt" file is not found.
     */
    public static String readFile() throws FileNotFoundException {
        // pass the path to the file as a parameter
        File file = new File("login_activity.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> currentActivity = new ArrayList<>();
        while (sc.hasNextLine()){
           // currentActivity.add(sc.nextLine());
            currentActivity.add(sc.next());
            sc.nextLine();
        }



        String currentUserName = currentActivity.get(currentActivity.size()-1);

        return currentUserName;


    }

    /**
     * Reads the contents of the "login_activity.txt" file and returns all lines as an array of strings.
     *
     * @return An array of strings containing each line of the file.
     * @throws FileNotFoundException If the "login_activity.txt" file is not found.
     */
    public static String[] readFileAsArray() throws FileNotFoundException {
        File file = new File("login_activity.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> currentActivity = new ArrayList<>();
        while (sc.hasNextLine()) {
            currentActivity.add(sc.nextLine());
        }

        return currentActivity.toArray(new String[0]);
    }

    /**
     * Reads the contents of the "login_activity.txt" file and returns the last line as an array of strings.
     *
     * @return An array of strings containing the components of the last line in the file.
     * @throws FileNotFoundException If the "login_activity.txt" file is not found.
     */
    public static String[] readFileCurrentUserAsArray() throws FileNotFoundException {
        File file = new File("login_activity.txt");

        try (Scanner sc = new Scanner(file)) {
            String lastLine = null;

            // Iterate through lines and store the last line
            while (sc.hasNextLine()) {
                lastLine = sc.nextLine();
            }

            // Split the last line into an array of strings
            if (lastLine != null) {
                return lastLine.split("\\s+");
            } else {
                return new String[0]; // or handle the case when the file is empty
            }
        }
    }

    /**
     * Reads the last login date and timestamp from the "login_activity.txt" file and returns it as a formatted string.
     *
     * @return A formatted string containing the last login date and timestamp.
     * @throws FileNotFoundException If the "login_activity.txt" file is not found.
     */
    public static String readFileCurrentUserDateAndTimestamp() throws FileNotFoundException {
        // Retrieve the last line from the "login_activity.txt" file as an array
        String[] lastLine = readFileCurrentUserAsArray();
        // Combine date and timestamp components into a formatted string
        return lastLine[2]+" "+lastLine[3] + " " + lastLine[4];

    }

    public static LocalDateTime readFileCurrentUserDateTime() throws FileNotFoundException {
        // Retrieve the last line from the "login_activity.txt" file as an array
        String[] lastLine = readFileCurrentUserAsArray();

        // Parse date and timestamp components into LocalDate and LocalTime
        LocalDate date = LocalDate.parse(lastLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime time = LocalTime.parse(lastLine[4].substring(0,8),DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Combine LocalDate and LocalTime into LocalDateTime
        return LocalDateTime.of(date, time);

    }

    /**
     * Reads all lines from the "login_activity.txt" file and returns them as a list of arrays of strings.
     *
     * @return A list of arrays of strings representing each line of the file.
     * @throws FileNotFoundException If the "login_activity.txt" file is not found.
     */
    public static List<String[]> readLines() throws FileNotFoundException {
        File file = new File("login_activity.txt");
        List<String[]> lines = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] items = line.split("\\s+"); // Split the line into items
                lines.add(items);
            }
        }

        return lines;
    }

    /**
     * Reads the last line of the "login_activity.txt" file and returns it as a string.
     *
     * @return The last line of the file.
     * @throws FileNotFoundException If the "login_activity.txt" file is not found.
     */
    public static String readLastLine() throws FileNotFoundException {
        File file = new File("login_activity.txt");
        String lastLine = "";

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                lastLine = sc.nextLine();
            }
        }

        return lastLine;
    }
}
