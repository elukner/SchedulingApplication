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
        File file = new File("C:\\Users\\elukn\\IdeaProjects\\SchedulingApplication\\login_activity.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(user+" "+loginAttempts+" "+date+" "+timeStamps+" "+success+"\n");

        bufferedWriter.close();
        fileWriter.close();

    }

    public static void deleteCurrentFile() throws IOException {
        File file = new File("C:\\Users\\elukn\\IdeaProjects\\SchedulingApplication\\login_activity.txt");
        if(file.exists()){
            file.delete();
        }

    }
    public static String readFile() throws FileNotFoundException {
        // pass the path to the file as a parameter
        File file = new File("C:\\Users\\elukn\\IdeaProjects\\SchedulingApplication\\login_activity.txt");
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
}
