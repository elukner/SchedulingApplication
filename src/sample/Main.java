package sample;

import helper.JDBC;
import dao.CountriesDAO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("view/scheduling.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
//    public static void main(String[] args) throws SQLException {
//        JDBC.openConnection();
//        //launch(args);
//
//            int rowsUpdated = CountriesDAO.insertCountries(4,"Finland", "Test User", "Test User" );
//
//            if(rowsUpdated>0){
//                System.out.println("Insert Successful");
//            }else{
//                System.out.println("Insert Failed");
//            }
//
//
//        JDBC.closeConnection();
//    }
}
