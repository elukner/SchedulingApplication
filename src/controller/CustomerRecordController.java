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

import model.*;
import javafx.application.Application;

/**
 * This FXML class is the Customer Record controller that contains business logic for the Customer Record view.
 */
public class CustomerRecordController extends Application implements Initializable {

    /**
     * Represents a JavaFX TableColumn for the address field in the user interface.
     */
    @FXML // fx:id="addressCol"
    private TableColumn<?, ?> addressCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for the customer ID field in the user interface.
     */
    @FXML // fx:id="customerIDCol"
    private TableColumn<?, ?> customerIDCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for the customer name field in the user interface.
     */
    @FXML // fx:id="customerNameCol"
    private TableColumn<?, ?> customerNameCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableView for displaying customer records in the user interface.
     */
    @FXML // fx:id="customerRecordTbl"
    private TableView<Customers> customerRecordTbl; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for the country field in the user interface.
     */
    @FXML // fx:id="countryCol"
    private TableColumn<?, ?> countryCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for the division ID field in the user interface.
     */
    @FXML // fx:id="divisionIDCol"
    private TableColumn<?, ?> divisionIDCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for the phone number field in the user interface.
     */
    @FXML // fx:id="phoneCol"
    private TableColumn<?, ?> phoneCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TableColumn for the postal code field in the user interface.
     */
    @FXML // fx:id="postalCodeCol"
    private TableColumn<?, ?> postalCodeCol; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX MenuItem for adding an item in the user interface.
     */
    @FXML // fx:id="addItem"
    private MenuItem addItem; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX Label for the customer ID field in the user interface.
     */
    @FXML // fx:id="customerIDLbl"
    private Label customerIDLbl;// Value injected by FXMLLoader

    /**
     * Represents a JavaFX Label for the customer name field in the user interface.
     */
    @FXML // fx:id="customerNameLbl"
    private Label customerNameLbl; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX Label for the address field in the user interface.
     */
    @FXML // fx:id="addressLbl"
    private Label addressLbl; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX Label for the postal code field in the user interface.
     */
    @FXML // fx:id="postalCodeLbl"
    private Label postalCodeLbl; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX Label for the phone number field in the user interface.
     */
    @FXML // fx:id="phoneNumberLbl"
    private Label phoneNumberLbl; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX Label for the country field in the user interface.
     */
    @FXML // fx:id="countryLbl"
    private Label countryLbl; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX Label for the first-level division field in the user interface.
     */
    @FXML // fx:id="firstLevelDivisionLbl"
    private Label firstLevelDivisionLbl; // Value injected by FXMLLoader

    /**
     * Represents a JavaFX TextField for entering the customer ID information in the user interface.
     */
    @FXML
    private TextField customerIDTxt;

    /**
     * Represents a JavaFX TextField for entering the customer name information in the user interface.
     */
    @FXML
    private TextField customerNameTxt;

    /**
     * Represents a JavaFX TextField for entering the address information in the user interface.
     */
    @FXML
    private TextField addressTxt;

    /**
     * Represents a JavaFX TextField for entering the postal code information in the user interface.
     */
    @FXML
    private TextField postalCodeTxt;

    /**
     * Represents a JavaFX TextField for entering the phone number information in the user interface.
     */
    @FXML
    private TextField phoneNumberTxt;

    /**
     * Represents a JavaFX ComboBox for selecting countries.
     */
    @FXML
    private ComboBox<String> countryBox;

    /**
     * Represents a JavaFX ComboBox for selecting first-level divisions in the user interface.
     */
    @FXML
    private ComboBox<String> firstLevelDivisionBox;

    /**
     * Represents a JavaFX GridPane for modifying customer information in the user interface.
     */
    @FXML
    private GridPane modifyGrid;

    /**
     * Represents a JavaFX Button for modifying customer information in the user interface.
     */
    @FXML
    private Button modifyBtn;

    /**
     * Represents a JavaFX Button for canceling or closing an action in the user interface.
     */
    @FXML
    private Button cancelBtn;

    /**
     * Represents the primary stage of the application.
     */
    private Stage stage;

    /**
     * Represents the root scene of the application.
     */
    private Parent scene;

    /**
     * Represents the model class for managing data related to customers in the application.
     */
    private Customers customerModel;

    /**
     * Represents the model class for managing data related to first-level divisions in the application.
     */
    private FirstLevelDivisions divisionModel;

    /**
     * Represents the currently logged-in user.
     */
    private Users currentUser;
    /**
     * Represents a model class for managing data related to countries in the application.
     */
    private Countries countriesModel;

    /**
     * Represents the currently selected customer.
     */
    private static Customers selectedCustomer;

    /**
     * Handles the action event for the "Back" button.
     * <p>
     * This method is triggered when the "Back" button is clicked, and it navigates the user
     * back to the main menu view. It obtains the reference to the current stage and loads the
     * main menu scene, updating the stage with the new scene and displaying it.
     *
     * @param event The action event triggered by clicking the "Back" button.
     * @throws IOException If an error occurs while loading the main menu view.
     */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
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
        customerRecordTbl.refresh();

    }

    /**
     * Handles the action event for the "Add" button.
     * <p>
     * This method is triggered when the "Add" button is clicked. It clears all input fields,
     * prepares the user interface for adding a new record, and shows the modification screen.
     *
     * @param actionEvent The action event triggered by clicking the "Add" button.
     */
    @FXML
    public void onActionAdd(ActionEvent actionEvent) {

        clearAllFields();
        showModify("Add");
    }

    /**
     * Handles the action event for the "Update" button.
     * <p>
     * This method is triggered when the "Update" button is clicked. It clears all input fields,
     * prepares the user interface for updating an existing record, and shows the modification screen.
     * Additionally, it fills the fields with data from the selected record.
     *
     * @param actionEvent The action event triggered by clicking the "Update" button.
     */
    @FXML
    public void onActionUpdate(ActionEvent actionEvent) {


        clearAllFields();
        showModify("Update");
        fillFields();

    }

    /**
     * Handles the action event for the "Delete" button.
     * <p>
     * This method is triggered when the "Delete" button is clicked. It clears all input fields,
     * prepares the user interface for deleting an existing record, and shows the modification screen.
     * Additionally, it fills the fields with data from the selected record.
     * <p>
     * Note: When deleting a customer record, all of the customer’s appointments must be deleted first,
     * due to foreign key constraints. A custom message is displayed in the user interface when a
     * customer record is deleted.
     *
     * @param actionEvent The action event triggered by clicking the "Delete" button.
     */
    @FXML
    public void onActionDelete(ActionEvent actionEvent) {
        clearAllFields();
        showModify("Delete");
        fillFields();

    }

    /**
     * Displays the modification form for adding, updating, or deleting customer information.
     * The form includes relevant fields and buttons based on the specified modification type.
     * Customer IDs are auto-generated. Text fields and buttons are made visible as needed.
     * Country and first-level division data is prepopulated in separate combo boxes or lists in the user interface for the user to choose.
     *
     * @param modifyType The type of modification to be performed ("Add", "Update", or "Delete").
     */
    private void showModify(String modifyType) {
        makeTxtBtnsVisible(true);


        switch (modifyType) {
            case "Add":
                modifyBtn.setText("Add");
                customerIDLbl.setVisible(false);
                customerIDTxt.setVisible(false);
                break;
            case "Update":
                modifyBtn.setText("Update");
                break;
            case "Delete":
                modifyBtn.setText("Delete");
                break;

        }

        // Country and first-level division data is prepopulated in separate combo boxes or
        // lists in the user interface for the user to choose.
        populateComboBoxes();


    }

    /**
     * Populates the combo boxes for selecting country and first-level division in the modification form.
     * Retrieves data from the database for all countries and first-level divisions and adds them to the respective combo boxes.
     * This method is called to provide users with prepopulated options when adding or updating customer information.
     */
    private void populateComboBoxes() {
        // Populate the country combo box
        for (Countries country : CountriesDaoImpl.getAllCountries()) {

            countryBox.getItems().add(country.getCountry());
        }

        // Populate the first-level division combo box
        for (FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisions()) {

            firstLevelDivisionBox.getItems().add(firstLevelDivision.getDivision());
        }

    }

    /**
     * Handles the event when a country is selected from the country combo box.
     * Retrieves and populates the first-level division combo box with divisions specific to the selected country.
     * If the selected country has associated first-level divisions, clears the existing items and adds the divisions to the combo box.
     * This method is triggered when the user selects a country in the modification form to narrow down the available first-level divisions.
     *
     * @param actionEvent The ActionEvent triggered by selecting a country from the country combo box.
     */
    @FXML
    public void onSelectCountry(ActionEvent actionEvent) {

        // Check if the selected country has associated data in the database
        if (!CountriesDaoImpl.getAllCountries(countryBox.getValue()).isEmpty()) {
            countriesModel = CountriesDaoImpl.getAllCountries(countryBox.getValue()).get(0);

            // Clear existing items in the first-level division combo box
            firstLevelDivisionBox.getItems().clear();

            // Populate the first-level division combo box with divisions specific to the selected country
            for (FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisionsFilteredCountry(countriesModel.getCountryID())) {

                firstLevelDivisionBox.getItems().add(firstLevelDivision.getDivision());
            }
        }


    }

    /**
     * Handles the event when a first-level division is selected from the division combo box.
     * Retrieves and sets the division model based on the selected first-level division.
     * This method is triggered when the user selects a first-level division in the modification form.
     *
     * @param actionEvent The ActionEvent triggered by selecting a first-level division from the division combo box.
     */
    @FXML
    public void onSelectDivision(ActionEvent actionEvent) {
        // Check if the selected first-level division has associated data in the database
        if (!FirstLevelDivisionsDaoImpl.getFirstLevelDivision(firstLevelDivisionBox.getValue()).isEmpty())
            divisionModel = FirstLevelDivisionsDaoImpl.getFirstLevelDivision(firstLevelDivisionBox.getValue()).get(0);


    }

    /**
     * Sets the visibility of text fields, buttons, and the modify grid in the modification form.
     * Disables the customer ID text field based on the specified boolean value.
     * This method is used to control the visibility of elements when modifying customer information.
     *
     * @param b A boolean value indicating whether to make the elements visible or not.
     */
    private void makeTxtBtnsVisible(boolean b) {
        modifyGrid.setVisible(b);
        customerIDTxt.setDisable(b);
        modifyBtn.setVisible(b);
        cancelBtn.setVisible(b);
    }

    /**
     * Handles the event when the modify button is clicked.
     * Executes different actions based on the text displayed on the modify button ("Add", "Update", or "Delete").
     * Calls corresponding methods to add, update, or delete customer information in the database.
     * Makes text fields, buttons, and the modify grid invisible after the modification is completed.
     *
     * @param actionEvent The ActionEvent triggered by clicking the modify button.
     * @throws FileNotFoundException If the required file is not found.
     * @throws SQLException If a database access error occurs.
     */
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
     * Fills the modification form fields with data from the selected customer in the customer record table.
     * If a customer is selected in the table, displays and sets the corresponding customer information
     * in the text fields and combo boxes of the modification form. The customer ID field is disabled,
     * and relevant combo boxes are populated with data based on the selected customer's country and division.
     * This method is called when the user selects a customer from the customer record table for modification.
     */
    private void fillFields() {
        if (!customerRecordTbl.getSelectionModel().isEmpty()) {
            // Store the selected customer
            selectedCustomer = customerRecordTbl.getSelectionModel().getSelectedItem();

            customerIDLbl.setVisible(true);
            customerIDTxt.setVisible(true);

            // Disable and set Customer_ID
            customerIDTxt.setDisable(true);
            customerIDTxt.setText(String.valueOf(selectedCustomer.getCustomerID()));

            customerNameLbl.setVisible(true);
            customerNameTxt.setVisible(true);
            customerNameTxt.setText(selectedCustomer.getCustomerName());

            addressLbl.setVisible(true);
            addressTxt.setVisible(true);
            addressTxt.setText(selectedCustomer.getAddress());


            postalCodeLbl.setVisible(true);
            postalCodeTxt.setVisible(true);
            postalCodeTxt.setText(selectedCustomer.getPostalCode());

            phoneNumberLbl.setVisible(true);
            phoneNumberTxt.setVisible(true);
            phoneNumberTxt.setText(selectedCustomer.getPhone());

            countryLbl.setVisible(true);
            countryBox.setVisible(true);
            firstLevelDivisionLbl.setVisible(true);
            firstLevelDivisionBox.setVisible(true);

            // Retrieve country and division data for the selected customer
            countriesModel = CountriesDaoImpl.getAllCountries(selectedCustomer.getCountry()).get(0);
            divisionModel = FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisionsFilteredCountry(countriesModel.getCountryID()).get(0);

            // Clear and set values for country and division combo boxes
            countryBox.getItems().clear();
            countryBox.setValue(countriesModel.getCountry());
            firstLevelDivisionBox.getItems().clear();
            firstLevelDivisionBox.setValue(divisionModel.getDivision());

            // Populate combo boxes with fresh data
            populateComboBoxes();
        }


    }

    /**
     * Sets up a selection listener for the customer record table.
     * Monitors changes in the selected item and updates the modification form fields accordingly.
     * When a customer is selected in the table, this listener populates and displays the relevant information
     * in the text fields and combo boxes of the modification form based on the selected customer's data.
     * The customer ID field is disabled, and relevant combo boxes are populated with data based on the selected customer's country and division.
     * This method is called to handle changes in the selected item in the customer record table.
     */
    private void tbleViewSelectionListener() {

        customerRecordTbl.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null) {
                // Store the selected customer
                selectedCustomer = customerRecordTbl.getSelectionModel().getSelectedItem();


                // Disable and set Customer_ID
                customerIDTxt.setDisable(true);
                customerIDTxt.setText(String.valueOf(selectedCustomer.getCustomerID()));

                customerNameLbl.setVisible(true);
                customerNameTxt.setVisible(true);
                customerNameTxt.setText(selectedCustomer.getCustomerName());

                addressLbl.setVisible(true);
                addressTxt.setVisible(true);
                addressTxt.setText(selectedCustomer.getAddress());


                postalCodeLbl.setVisible(true);
                postalCodeTxt.setVisible(true);
                postalCodeTxt.setText(selectedCustomer.getPostalCode());

                phoneNumberLbl.setVisible(true);
                phoneNumberTxt.setVisible(true);
                phoneNumberTxt.setText(selectedCustomer.getPhone());

                countryLbl.setVisible(true);
                countryBox.setVisible(true);
                firstLevelDivisionLbl.setVisible(true);
                firstLevelDivisionBox.setVisible(true);

                // Retrieve country and division data for the selected customer
                countriesModel = CountriesDaoImpl.getAllCountries(selectedCustomer.getCountry()).get(0);
                divisionModel = FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisionsFilteredCountry(countriesModel.getCountryID()).get(0);

                // Clear and set values for country and division combo boxes
                countryBox.getItems().clear();
                countryBox.setValue(countriesModel.getCountry());
                firstLevelDivisionBox.getItems().clear();
                firstLevelDivisionBox.setValue(divisionModel.getDivision());

                // Populate combo boxes with fresh data
                populateComboBoxes();

            }
        });
    }

    /**
     * Creates a new customer model with the data entered in the modification form for adding a customer.
     * Uses the current date and time for the creation and last update timestamps.
     * Reads the current user from the login activity file for the creator and last updater information.
     * The customer ID is automatically generated based on the size of the existing customers list.
     * This method is called when adding a new customer record to the system.
     *
     * @throws FileNotFoundException If the required file is not found.
     */
    private void addCustomer() throws FileNotFoundException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        customerModel = new Customers(CustomersDaoImpl.getAllCustomers().size() + 1, customerNameTxt.getText(),
                addressTxt.getText(), postalCodeTxt.getText(), phoneNumberTxt.getText(), dateTimeFormatter.format(LocalDateTime.now()), FileIOManager.readFile(),
                dateTimeFormatter.format(LocalDateTime.now()), FileIOManager.readFile(), divisionModel.getDivisionID());

    }

    /**
     * Updates the customer model with the modified data entered in the modification form for updating a customer.
     * Uses the current date and time for the last update timestamp.
     * Reads the current user from the login activity file for the last updater information.
     * This method is called when updating an existing customer record in the system.
     *
     * @throws FileNotFoundException If the required file is not found.
     */
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
     * Deletes a customer record from the system and database based on the entered customer ID.
     * Checks if the customer has associated appointments and prevents deletion if foreign key constraints exist.
     * Displays appropriate confirmation or error alerts based on the deletion outcome.
     * This method is called when deleting an existing customer record from the system.
     *
     * @throws FileNotFoundException If the required file is not found.
     * @throws SQLException           If a database access error occurs.
     */
    private void deleteCustomer() throws FileNotFoundException, SQLException {
        if (!customerIDTxt.getText().trim().isEmpty()) {
            if (!CustomersDaoImpl.getAllCustomers().isEmpty()) {
                customerModel = CustomersDaoImpl.getAllCustomers().get(Integer.parseInt(customerIDTxt.getText()) - 1);
            }

            // When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.
            if (AppointmentsDaoImpl.getAllAppointmentsCustomerID(customerModel.getCustomerID()).isEmpty()) {

                deleteCustomerDatabase();
                //        When a customer record is deleted, a custom message should display in the user interface.
                showAlert(Alert.AlertType.CONFIRMATION, "Customer Record is Deleted", customerModel.getCustomerName() + "'s record has been successfully deleted");

            } else {
                showAlert(Alert.AlertType.ERROR, "Customer Record Cannot Be Deleted", customerModel.getCustomerName() +
                        "’s appointments must be deleted first, due to foreign key constraints");
            }

        }

    }

    /**
     * Adds a new customer record to the database using the data from the current customer model.
     * Invokes the corresponding method in the CustomersDaoImpl class to perform the database insertion.
     * After adding the customer to the database, the method refreshes the customer record table view.
     *
     * @throws SQLException If a database access error occurs.
     */
    private void addCustomerDatabase() throws SQLException {

        CustomersDaoImpl.insertCustomers(customerModel.getCustomerID(), customerModel.getCustomerName(),
                customerModel.getAddress(), customerModel.getPostalCode(), customerModel.getPhone(),
                customerModel.getCreateDate(), customerModel.getCreatedBy(),
                customerModel.getLastUpdate(),
                customerModel.getLastUpdatedBy(), divisionModel.getDivisionID());
        showCustomerRecordTableView();

    }

    /**
     * Updates an existing customer record in the database using the modified data from the current customer model.
     * Invokes the corresponding method in the CustomersDaoImpl class to perform the database update.
     * After updating the customer in the database, the method refreshes the customer record table view.
     *
     * @throws SQLException If a database access error occurs.
     */
    private void updateCustomerDatabase() throws SQLException {

        CustomersDaoImpl.updateCustomers(customerModel.getCustomerID(), customerModel.getCustomerName(),
                customerModel.getAddress(), customerModel.getPostalCode(), customerModel.getPhone(),
                customerModel.getLastUpdate(),
                customerModel.getLastUpdatedBy(), divisionModel.getDivisionID());
        showCustomerRecordTableView();

    }

    /**
     * Deletes an existing customer record from the database based on the entered customer ID.
     * Invokes the corresponding method in the CustomersDaoImpl class to perform the database deletion.
     * After deleting the customer from the database, the method refreshes the customer record table view.
     *
     * @throws SQLException If a database access error occurs.
     */
    private void deleteCustomerDatabase() throws SQLException {

        CustomersDaoImpl.deleteCustomers(Integer.parseInt(customerIDTxt.getText()));
        showCustomerRecordTableView();

    }


    /**
     * Handles the "Cancel" button action.
     * This method makes the modification form fields invisible and clears all input fields.
     *
     * @param actionEvent The ActionEvent triggered by the "Cancel" button.
     */
    @FXML
    public void onActionCancel(ActionEvent actionEvent) {
        makeTxtBtnsVisible(false);
        clearAllFields();
    }

    /**
     * Clears all input fields in the modification form.
     * Re-enables the customer ID text field and removes items from the country and division combo boxes.
     */
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
     * Displays an alert with the specified type, title, and message in the user interface.
     *
     * @param alertType The type of alert (e.g., ERROR, CONFIRMATION).
     * @param title     The title of the alert.
     * @param message   The message to be displayed in the alert.
     */
    private static void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Initializes the Customer Record controller class. Retrieves the current user from the user data file,
     * makes modification form fields invisible, shows the customer record table view, sets up a selection listener
     * for the table view, and selects the first row in the table view.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
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
        tbleViewSelectionListener();
        customerRecordTbl.getSelectionModel().select(0);

    }


    /**
     * Starts the Customer Record application by loading the FXML file and displaying the main stage.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception If an error occurs during the startup of the application.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/customerRecord.fxml"));
        //   primaryStage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Stops the Customer Record application. Deletes the current file used for data storage.
     *
     * @throws Exception If an error occurs during the application shutdown.
     */
    @Override
    public void stop() throws Exception {
        FileIOManager.deleteCurrentFile();

    }

}
