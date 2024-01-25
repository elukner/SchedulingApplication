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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * File I/O: Reading from and writing to files,
 * managing file-related operations.
 *
 * Write code that provides the ability to track user activity by recording all user log-in attempts, dates, and
 * time stamps and whether each attempt was successful in a file named login_activity.txt.
 * Append each new record to the existing file, and save to the root folder of the application.
 */
public class FileIOManager {
    //Write code that provides the ability to track user activity by recording all user log-in attempts, dates, and
    // time stamps and whether each attempt was successful in a file named login_activity.txt.
    // and save to the root folder of the application.
    //Append each new record to the existing file
    public static void writeToFile(String user, int loginAttempts, String date, Timestamp timeStamps, String success ) throws IOException {
        File file = new File ("login_activity.txt");
        if(!file.exists()){
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter("login_activity.txt"),true);
                BufferedWriter br = new BufferedWriter(printWriter);
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

    public static void writeToCurrentFile(String user, int loginAttempts, String date, Timestamp timeStamps, String success ) throws IOException {
        File file = new File("login_activity.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(user+" "+loginAttempts+" "+date+" "+timeStamps+" "+success+"\n");

        bufferedWriter.close();
        fileWriter.close();

    }

    public static void deleteCurrentFile() throws IOException {
        File file = new File("login_activity.txt");
        if(file.exists()){
            file.delete();
        }

    }
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
