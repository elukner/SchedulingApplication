package controller;
/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */


import dao.CountriesDaoImpl;
import dao.CustomersDaoImpl;
import dao.FirstLevelDivisionsDaoImpl;
import dao.UsersDaoImpl;
import helper.FileIOManager;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;
import model.Users;
import javafx.application.Application;

/**
 * Notes from Requirements:
 * The controller package will hold Controller
 * classes that contain Business Logic for your views.
 * <p>
 * Use lambda expressions for event handling within your JavaFX controllers.
 * For instance, if you're handling button clicks or other UI interactions,
 * you can utilize lambda expressions to define event handlers.
 * <p>
 * // Example in a JavaFX controller
 * button.setOnAction(event -> {
 * // Handle button click event here
 * });
 **/

/**
 * This FXML class is the Customer Record controller that contains business logic for the Customer Record view.
 */
public class CustomerRecordController extends Application implements Initializable {

    @FXML // fx:id="addressCol"
    private TableColumn<?, ?> addressCol; // Value injected by FXMLLoader

    @FXML // fx:id="createDateCol"
    private TableColumn<?, ?> createDateCol; // Value injected by FXMLLoader

    @FXML // fx:id="createdByCol"
    private TableColumn<?, ?> createdByCol; // Value injected by FXMLLoader

    @FXML // fx:id="customerIDCol"
    private TableColumn<?, ?> customerIDCol; // Value injected by FXMLLoader

    @FXML // fx:id="customerNameCol"
    private TableColumn<?, ?> customerNameCol; // Value injected by FXMLLoader

    @FXML // fx:id="customerRecordTbl"
    private TableView<Customers> customerRecordTbl; // Value injected by FXMLLoader

    @FXML // fx:id="divisionIDCol"
    private TableColumn<?, ?> divisionIDCol; // Value injected by FXMLLoader

    @FXML // fx:id="lastUpdateCol"
    private TableColumn<?, ?> lastUpdateCol; // Value injected by FXMLLoader

    @FXML // fx:id="lastUpdatedByCol"
    private TableColumn<?, ?> lastUpdatedByCol; // Value injected by FXMLLoader

    @FXML // fx:id="phoneCol"
    private TableColumn<?, ?> phoneCol; // Value injected by FXMLLoader

    @FXML // fx:id="postalCodeCol"
    private TableColumn<?, ?> postalCodeCol; // Value injected by FXMLLoader

    @FXML // fx:id="addItem"
    private MenuItem addItem; // Value injected by FXMLLoader

    @FXML // fx:id="customerIDLbl"
    private Label customerIDLbl;// Value injected by FXMLLoader

    @FXML // fx:id="customerNameLbl"
    private Label customerNameLbl; // Value injected by FXMLLoader

    @FXML // fx:id="addressLbl"
    private Label addressLbl; // Value injected by FXMLLoader

    @FXML // fx:id="postalCodeLbl"
    private Label postalCodeLbl; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNumberLbl"
    private Label phoneNumberLbl; // Value injected by FXMLLoader

    @FXML // fx:id="countryLbl"
    private Label countryLbl; // Value injected by FXMLLoader

    @FXML // fx:id="firstLevelDivisionLbl"
    private Label firstLevelDivisionLbl; // Value injected by FXMLLoader

    @FXML
    private TextField customerIDTxt;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private TextField phoneNumberTxt;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> firstLevelDivisionBox;

    @FXML
    private GridPane modifyGrid;

    @FXML
    private Button modifyBtn;

    @FXML
    private Button cancelBtn;

    private Stage stage;
    private Parent scene;

    private Customers customerModel;
    private FirstLevelDivisions divisionModel;
    private Users currentUser;



    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        FileIOManager.deleteCurrentFile();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }




    /**
     * Customer data is displayed using a TableView, including first-level division data. A list of all the customers
     * and their information may be viewed in a TableView, and updates of the data can be performed
     * in text fields on the form.
     */
    @FXML
    void showCustomerRecordTableView() {

        ObservableList<Customers> customersList = FXCollections.observableArrayList();


        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        try {
            customersList.addAll(CustomersDaoImpl.getAllCustomers());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customerRecordTbl.setItems(customersList);

    }

    /**
     * Customer data is displayed using a TableView, including first-level division data. A list of all the customers
     * and their information may be viewed in a TableView, and updates of the data can be performed
     * in text fields on the form.
     */
    @FXML
    void updateCustomerRecordTableView() {



        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        try {
            customersList.addAll(CustomersDaoImpl.getAllCustomers());

        } catch (Exception ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customerRecordTbl.setItems(customersList);

    }



    /**
     * Customer records and appointments can be added, updated, and deleted.
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    public void onActionAdd(ActionEvent actionEvent) throws SQLException {

        showModify("Add");

    }
    @FXML
    public void onActionUpdate(ActionEvent actionEvent) throws IOException {
        // Customer records and appointments can be added, updated, and deleted.

        // When adding and updating a customer, text fields are used to collect the following data: customer name, address, postal code, and phone number.
//-  Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and country data are collected using separate combo boxes.
//Note: The address text field should not include first-level division and country data. Please use the following examples to format addresses:
// •  U.S. address: 123 ABC Street, White Plains
//•  Canadian address: 123 ABC Street, Newmarket
//•  UK address: 123 ABC Street, Greenwich, London
//-  When updating a customer, the customer data autopopulates in the form.

        // All of the original customer information is displayed on the update form.
//-  Customer_ID must be disabled.


//All of the fields can be updated except for Customer_ID.
        showModify("Update");
        modifyBtn.setText("Update");

    }
    @FXML
    public void onActionDelete(ActionEvent actionEvent) throws IOException {
        // Customer records and appointments can be added, updated, and deleted.
//-  When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.

// When a customer record is deleted, a custom message should display in the user interface.
        showModify("Delete");
        modifyBtn.setText("Delete");
    }

    private void showModify(String modifyType) {

        // Customer IDs are auto-generated
//        TextField customerID = new TextField();
        makeTxtBtnsVisible(true);

        switch (modifyType){
            case "Add":
                modifyBtn.setText("Add");
                customerIDLbl.setVisible(false);
                customerIDTxt.setVisible(false);
                break;
            case "Update":
                customerIDTxt.setDisable(false);
                customerIDTxt.setText("");
                break;
            case "Delete":
                customerIDTxt.setText(Integer.toString(CustomersDaoImpl.getAllCustomers().size()+1));
                break;

        }

//        Country and first-level division data is prepopulated in separate combo boxes or
//        lists in the user interface for the user to choose.

        for(Countries country : CountriesDaoImpl.getAllCountries()){

            countryBox.getItems().add(country.getCountry());
        }

        for(FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisions()){

            firstLevelDivisionBox.getItems().add(firstLevelDivision.getDivision());
        }


    }



    /**
     * The first-level list
     * should be filtered by the user’s
     * selection of a country (e.g., when choosing U.S., filter so it only shows states).
     * @param actionEvent
     */
    @FXML
    public void onSelectCountry(ActionEvent actionEvent) {

        Countries countriesModel = CountriesDaoImpl.getAllCountries(countryBox.getValue()).get(0);

        firstLevelDivisionBox.getItems().clear();

        for(FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisionsFilteredCountry(countriesModel.getCountryID())){

            firstLevelDivisionBox.getItems().add(firstLevelDivision.getDivision());
        }

    }

    /**
     * @param actionEvent
     */
    @FXML
    public void onSelectDivision(ActionEvent actionEvent) {

        divisionModel = FirstLevelDivisionsDaoImpl.getFirstLevelDivision(firstLevelDivisionBox.getValue()).get(0);


    }

    private void makeTxtBtnsVisible(boolean b) {
        modifyGrid.setVisible(b);
        modifyBtn.setVisible(b);
        cancelBtn.setVisible(b);
    }


    @FXML
    public void onActionModify(ActionEvent actionEvent) throws SQLException, FileNotFoundException {

       switch (modifyBtn.getText()){
           case "Add":
               addCustomer();
               addCustomerDatabase();
               modifyBtn.setText("Modify");
              //TODO updateCustomerRecordTableView();
               break;
           case "Update":
               updateCustomerDatabase();
               break;
           case "Delete":
               deleteCustomerDatabase();
               break;
       }


        makeTxtBtnsVisible(false);

    }

    /**
     *  When adding and updating a customer, text fields are used to collect the following data: customer name,
     *  address, postal code, and phone number.
     *  Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and
     *  country data are collected using separate combo boxes.
     *  The address text field should not include first-level division and country data.
     *  Please use the following examples to format addresses: U.S. address: 123 ABC Street, White Plains
     * @throws FileNotFoundException
     */
    private void addCustomer() throws FileNotFoundException {
        String formatedAddress = addressTxt.getText()+", "+cityTxt.getText();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        customerModel = new Customers(CustomersDaoImpl.getAllCustomers().size()+1,customerNameTxt.getText(),
                formatedAddress,postalCodeTxt.getText(),phoneNumberTxt.getText(), dateTimeFormatter.format(LocalDateTime.now()),FileIOManager.readFile(),
                dateTimeFormatter.format(LocalDateTime.now()), FileIOManager.readFile(), divisionModel.getDivisionID());
    }


    //    @FXML
//    void handleAdd(ActionEvent event) {
//        isAdd = true;
//        handleSceneChange();
//    }
    private void addCustomerDatabase() throws SQLException, FileNotFoundException {

        
        JDBC.openConnection();
        CustomersDaoImpl.insertCustomers(customerModel.getCustomerID(),customerModel.getCustomerName(),
                customerModel.getAddress(),customerModel.getPostalCode(),customerModel.getPhone(),
                customerModel.getCreateDate(),customerModel.getCreatedBy(),
                customerModel.getLastUpdate(),
                customerModel.getLastUpdatedBy(), divisionModel.getDivisionID());
        JDBC.closeConnection();

    }

    private void updateCustomerDatabase() throws SQLException {
        JDBC.openConnection();
        CustomersDaoImpl.updateCustomers(CustomersDaoImpl.getAllCustomers().size(),postalCodeTxt.getText());
        JDBC.closeConnection();
    }

    private void deleteCustomerDatabase() throws SQLException {
        JDBC.openConnection();
        CustomersDaoImpl.deleteCustomers(CustomersDaoImpl.getAllCustomers().size());
        JDBC.closeConnection();
    }

    @FXML
    public void onActionCancel(ActionEvent actionEvent) {
        makeTxtBtnsVisible(false);
    }



    /**
     * This method initializes this Customer Record controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            currentUser= UsersDaoImpl.getUser(FileIOManager.readFile(),FileIOManager.readFile()).get(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        makeTxtBtnsVisible(false);
        showCustomerRecordTableView();

    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/customerRecord.fxml"));
        //   primaryStage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void stop() throws Exception{
        FileIOManager.deleteCurrentFile();

    }

}
