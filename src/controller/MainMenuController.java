package controller;

import helper.FileIOManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Application implements Initializable {

    private Stage stage;
    private Parent scene;

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCustomerRecordBtn(ActionEvent event) throws IOException {
        String viewPath = "../view/customerRecord.fxml";
        loadPage(event,viewPath);
    }


    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionExitBtn(ActionEvent event) throws IOException  {
        FileIOManager.deleteCurrentFile();
       System.exit(0);

    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionLogOutBtn(ActionEvent event) throws IOException  {
        FileIOManager.deleteCurrentFile();
        String viewPath = "../view/login.fxml";
        loadPage(event,viewPath);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReportsBtn(ActionEvent event)throws IOException  {
        String viewPath = "../view/reports.fxml";
        loadPage(event,viewPath);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionSchedulingBtn(ActionEvent event) throws IOException {
        String viewPath = "../view/scheduling.fxml";
        loadPage(event,viewPath);

    }

    /**
     *
     * @param event
     * @param viewPath
     * @throws IOException
     */
    private void loadPage(ActionEvent event,String viewPath) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method initializes this main menu controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/reports.fxml"));
        //   primaryStage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void stop() throws Exception{
        FileIOManager.deleteCurrentFile();

    }
}
