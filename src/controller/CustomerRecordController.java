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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.util.Pair;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;

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
public class CustomerRecordController implements Initializable {

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

    private TextField customerID = new TextField();
    private TextField customerName = new TextField();
    private TextField address = new TextField();
    private TextField postalCode = new TextField();
    private TextField phoneNumber = new TextField();
    private ComboBox<String> countryBox = new ComboBox<>();
    private ComboBox<String> firstLevelDivisionBox = new ComboBox<>();



    private Stage stage;
    private Parent scene;

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

    @FXML
    public void onActionAdd(ActionEvent actionEvent) throws IOException {
        // Customer records and appointments can be added, updated, and deleted.

        // When adding and updating a customer, text fields are used to collect the following data: customer name, address, postal code, and phone number.
//-  Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and country data are collected using separate combo boxes.
//Note: The address text field should not include first-level division and country data. Please use the following examples to format addresses:
// •  U.S. address: 123 ABC Street, White Plains
//•  Canadian address: 123 ABC Street, Newmarket
//•  UK address: 123 ABC Street, Greenwich, London
//-  When updating a customer, the customer data autopopulates in the form.
        showModifyWindow("Add","Add Customer","Please enter new customer information");

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
        showModifyWindow("Update","Update Customer","Please enter new customer information");
    }
    @FXML
    public void onActionDelete(ActionEvent actionEvent) throws IOException {
        // Customer records and appointments can be added, updated, and deleted.
//-  When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.

// When a customer record is deleted, a custom message should display in the user interface.
        showModifyWindow("Delete","Delete Customer","Please enter new customer information");
    }

    private void showModifyWindow(String modifyType, String title, String message) {

//Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);



// Set the button types.
        ButtonType loginButtonType = new ButtonType(modifyType, ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Customer IDs are auto-generated
//        TextField customerID = new TextField();
        switch (modifyType){
            case "Add":
                customerID.setEditable(false);
                customerID.setText(Integer.toString(CustomersDaoImpl.getAllCustomers().size()+1));
                break;
            case "Update":
                customerID.setText("");
                break;
            case "Delete":
                customerID.setText(Integer.toString(CustomersDaoImpl.getAllCustomers().size()+1));
                break;

        }

//        Country and first-level division data is prepopulated in separate combo boxes or
//        lists in the user interface for the user to choose. The first-level list
//        should be filtered by the user’s
//        selection of a country (e.g., when choosing U.S., filter so it only shows states).

        for(Countries country : CountriesDaoImpl.getAllCountries()){

            countryBox.getItems().add(country.getCountry());
        }


        for(FirstLevelDivisions firstLevelDivision : FirstLevelDivisionsDaoImpl.getAllFirstLevelDivisions()){

            firstLevelDivisionBox.getItems().add(firstLevelDivision.getDivision());
        }

        grid.add(new Label("Customer ID:"), 0, 0);
        grid.add(customerID, 1, 0);
        grid.add(new Label("Customer Name:"), 0, 1);
        grid.add(customerName, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(address, 1, 2);
        grid.add(new Label("Postal Code:"), 0, 3);
        grid.add(postalCode, 1, 3);
        grid.add(new Label("Phone Number:"), 0, 4);
        grid.add(phoneNumber, 1, 4);
        grid.add(new Label("Country:"), 0, 5);
        grid.add(countryBox, 1, 5);
        grid.add(new Label("First-level division:"), 0, 6);
        grid.add(firstLevelDivisionBox, 1, 6);
        dialog.getDialogPane().setContent(grid);



//// Convert the result to a username-password-pair when the login button is clicked.
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == loginButtonType) {
//                return new Pair<>(customerID.getText(), customerName.getText());
//            }
//            return null;
//        });


        dialog.show();
     //   Optional<Pair<String, String>> result = dialog.showAndWait();

//        result.ifPresent(usernamePassword -> {
//            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//        });

    }
    /**
     * This method initializes this Customer Record controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCustomerRecordTableView();

    }
}
