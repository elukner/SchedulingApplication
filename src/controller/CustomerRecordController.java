package controller;
/**
 * Project: SchedulingApplication
 * Package: controller
 * <p>
 * User: Elizabeth Thomas
 * Date: 1/2/2024
 * Time: 1:27 PM
 */


import dao.*;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.*;
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

    @FXML // fx:id="customerIDCol"
    private TableColumn<?, ?> customerIDCol; // Value injected by FXMLLoader

    @FXML // fx:id="customerNameCol"
    private TableColumn<?, ?> customerNameCol; // Value injected by FXMLLoader

    @FXML // fx:id="customerRecordTbl"
    private TableView<Customers> customerRecordTbl; // Value injected by FXMLLoader

    @FXML // fx:id="countryCol"
    private TableColumn<?, ?> countryCol; // Value injected by FXMLLoader

    @FXML // fx:id="divisionIDCol"
    private TableColumn<?, ?> divisionIDCol; // Value injected by FXMLLoader

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
    private Countries countriesModel;
    private Appointments appointmentsModel;


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
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));


        try {
            customersList.addAll(CustomersDaoImpl.getAllCustomersAndCountriesForTble());

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


        customerRecordTbl.getItems().clear();
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
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
     *
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    public void onActionAdd(ActionEvent actionEvent) {

        clearAllFields();
        showModify("Add");


    }

    @FXML
    public void onActionUpdate(ActionEvent actionEvent) {


        clearAllFields();
        showModify("Update");
    }

    @FXML
    public void onActionDelete(ActionEvent actionEvent) {
        // Customer records and appointments can be added, updated, and deleted.
//-  When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.

// When a customer record is deleted, a custom message should display in the user interface.

        clearAllFields();
        showModify("Delete");
    }

    private void showModify(String modifyType) {

        // Customer IDs are auto-generated
//        TextField customerID = new TextField();
        makeTxtBtnsVisible(true);

        switch (modifyType) {
            case "Add":
                modifyBtn.setText("Add");
                customerIDLbl.setVisible(false);
                customerIDTxt.setVisible(false);
                customerNameLbl.setVisible(true);
                customerNameTxt.setVisible(true);
                addressLbl.setVisible(true);
                addressTxt.setVisible(true);
                postalCodeLbl.setVisible(true);
                postalCodeTxt.setVisible(true);
                phoneNumberLbl.setVisible(true);
                phoneNumberTxt.setVisible(true);
                countryLbl.setVisible(true);
                countryBox.setVisible(true);
                firstLevelDivisionLbl.setVisible(true);
                firstLevelDivisionBox.setVisible(true);
                break;
            case "Update":
                customerIDLbl.setVisible(true);
                customerIDTxt.setVisible(true);
                customerNameLbl.setVisible(true);
                customerNameTxt.setVisible(true);
                addressLbl.setVisible(true);
                addressTxt.setVisible(true);
                postalCodeLbl.setVisible(true);
                postalCodeTxt.setVisible(true);
                phoneNumberLbl.setVisible(true);
                phoneNumberTxt.setVisible(true);
                countryLbl.setVisible(true);
                countryBox.setVisible(true);
                firstLevelDivisionLbl.setVisible(true);
                firstLevelDivisionBox.setVisible(true);
                customerIDTxt.setPromptText("Type Customer ID and press enter");
                modifyBtn.setText("Update");
                break;
            case "Delete":
                customerIDLbl.setVisible(true);
                customerIDTxt.setVisible(true);
                customerNameLbl.setVisible(true);
                customerNameTxt.setVisible(true);
                addressLbl.setVisible(false);
                addressTxt.setVisible(false);
                postalCodeLbl.setVisible(false);
                postalCodeTxt.setVisible(false);
                phoneNumberLbl.setVisible(false);
                phoneNumberTxt.setVisible(false);
                countryLbl.setVisible(false);
                countryBox.setVisible(false);
                firstLevelDivisionLbl.setVisible(false);
                firstLevelDivisionBox.setVisible(false);
                modifyBtn.setText("Delete");
                break;

        }

//        Country and first-level division data is prepopulated in separate combo boxes or
//        lists in the user interface for the user to choose.

        populateComboBoxes();


    }

    private void populateComboBoxes() {

        for (Countries country : CountriesDaoImpl.getAllCountries()) {

            countryBox.getItems().add(country.getCountry());
        }

        for (FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisions()) {

            firstLevelDivisionBox.getItems().add(firstLevelDivision.getDivision());
        }

    }

    @FXML
    void onKeyPressedCustomerIDEnter(KeyEvent event) {

        try {
            customerModel = CustomersDaoImpl.getAllCustomers().get(Integer.parseInt(customerIDTxt.getText()) - 1);

            customerNameTxt.setText(customerModel.getCustomerName());

            addressTxt.setText(customerModel.getAddress());

            postalCodeTxt.setText(customerModel.getPostalCode());
            phoneNumberTxt.setText(customerModel.getPhone());

            divisionModel = FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisions().get(customerModel.getDivisionID() - 1);
            countriesModel = CountriesDaoImpl.getAllCountries().get(divisionModel.getCountryID() - 1);
            countryBox.getItems().clear();
            countryBox.setValue(countriesModel.getCountry());
            firstLevelDivisionBox.getItems().clear();
            firstLevelDivisionBox.setValue(divisionModel.getDivision());

            populateComboBoxes();
            customerIDTxt.setDisable(true);
        } catch (NumberFormatException | IndexOutOfBoundsException n) {

        }

    }


    /**
     * The first-level list
     * should be filtered by the user’s
     * selection of a country (e.g., when choosing U.S., filter so it only shows states).
     *
     * @param actionEvent
     */
    @FXML
    public void onSelectCountry(ActionEvent actionEvent) {

        countriesModel = CountriesDaoImpl.getAllCountries(countryBox.getValue()).get(0);


        firstLevelDivisionBox.getItems().clear();

        for (FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisionsFilteredCountry(countriesModel.getCountryID())) {

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
    public void onActionModify(ActionEvent actionEvent) throws FileNotFoundException, SQLException {

        switch (modifyBtn.getText()) {
            case "Add":
                addCustomer();
                addCustomerDatabase();
                break;
            case "Update":
                updateCustomer();
                updateCustomerDatabase();
                break;
            case "Delete":
                deleteCustomer();
                break;
        }


        makeTxtBtnsVisible(false);


    }

    /**
     * When adding and updating a customer, text fields are used to collect the following data: customer name,
     * address, postal code, and phone number.
     * Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and
     * country data are collected using separate combo boxes.
     * The address text field should not include first-level division and country data.
     * Please use the following examples to format addresses: U.S. address: 123 ABC Street, White Plains
     *
     * @throws FileNotFoundException
     */
    private void addCustomer() throws FileNotFoundException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        customerModel = new Customers(CustomersDaoImpl.getAllCustomers().size() + 1, customerNameTxt.getText(),
                addressTxt.getText(), postalCodeTxt.getText(), phoneNumberTxt.getText(), dateTimeFormatter.format(LocalDateTime.now()), FileIOManager.readFile(),
                dateTimeFormatter.format(LocalDateTime.now()), FileIOManager.readFile(), divisionModel.getDivisionID());
    }

    private void updateCustomer() throws FileNotFoundException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        customerModel.setCustomerName(customerNameTxt.getText());
        customerModel.setAddress(addressTxt.getText());
        customerModel.setPostalCode(postalCodeTxt.getText());
        customerModel.setPhone(phoneNumberTxt.getText());
        customerModel.setLastUpdate(dateTimeFormatter.format(LocalDateTime.now()));
        customerModel.setLastUpdatedBy(FileIOManager.readFile());
        customerModel.setDivisionID(divisionModel.getDivisionID());

    }

    /**
     *Customer records and appointments can be added, updated, and deleted.
     * @throws FileNotFoundException
     */
    private void deleteCustomer() throws FileNotFoundException, SQLException {

        if(!CustomersDaoImpl.getAllCustomers().isEmpty()){
            customerModel = CustomersDaoImpl.getAllCustomers().get(Integer.parseInt(customerIDTxt.getText())-1);
        }

        // When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.
        if(AppointmentsDaoImpl.getAllAppointmentsCustomerID(customerModel.getCustomerID()).isEmpty()){

            deleteCustomerDatabase();
            //        When a customer record is deleted, a custom message should display in the user interface.
            showAlert(Alert.AlertType.CONFIRMATION, "Customer Record is Deleted", customerModel.getCustomerName()+  "'s record has been successfully deleted");

        }





    }

    //    @FXML
//    void handleAdd(ActionEvent event) {
//        isAdd = true;
//        handleSceneChange();
//    }
    private void addCustomerDatabase() throws SQLException {

        CustomersDaoImpl.insertCustomers(customerModel.getCustomerID(), customerModel.getCustomerName(),
                customerModel.getAddress(), customerModel.getPostalCode(), customerModel.getPhone(),
                customerModel.getCreateDate(), customerModel.getCreatedBy(),
                customerModel.getLastUpdate(),
                customerModel.getLastUpdatedBy(), divisionModel.getDivisionID());


    }

    private void updateCustomerDatabase() throws SQLException {

        CustomersDaoImpl.updateCustomers(customerModel.getCustomerID(), customerModel.getCustomerName(),
                customerModel.getAddress(), customerModel.getPostalCode(), customerModel.getPhone(),
                customerModel.getLastUpdate(),
                customerModel.getLastUpdatedBy(), divisionModel.getDivisionID());

    }

    private void deleteCustomerDatabase() throws SQLException {

        //customer id txt is filled out
        if((!customerIDTxt.getText().isEmpty()) && (customerNameTxt.getText().isEmpty())){
            CustomersDaoImpl.deleteCustomers(Integer.parseInt(customerIDTxt.getText()));
        }
        //customer name is the only one filled out
        if((!customerNameTxt.getText().isEmpty())&&(customerIDTxt.getText().isEmpty())){
            showAlert(Alert.AlertType.ERROR, "Customer ID Empty", "Please fill out customer ID text box");
        }
        //both text boxes are filled out
        if((!customerNameTxt.getText().isEmpty())&&(!customerIDTxt.getText().isEmpty())){
            CustomersDaoImpl.deleteCustomers(Integer.parseInt(customerIDTxt.getText()));
        }

    }

    @FXML
    public void onActionCancel(ActionEvent actionEvent) {
        makeTxtBtnsVisible(false);
        clearAllFields();
    }

    private void clearAllFields() {
        customerIDTxt.setDisable(false);
        customerIDTxt.clear();
        customerNameTxt.clear();
        phoneNumberTxt.clear();
        addressTxt.clear();
        postalCodeTxt.clear();
        phoneNumberTxt.clear();
        countryBox.getItems().clear();
        firstLevelDivisionBox.getItems().clear();
    }

    /**
     * When a customer record is deleted, a custom message should display in the user interface.
     * @param alertType
     * @param title
     * @param message
     */
    private static void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * This method initializes this Customer Record controller class
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            currentUser = UsersDaoImpl.getUser(FileIOManager.readFile(), FileIOManager.readFile()).get(0);
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
    public void stop() throws Exception {
        FileIOManager.deleteCurrentFile();

    }

}
