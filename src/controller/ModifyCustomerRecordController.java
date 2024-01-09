package controller;


import dao.CustomersDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import model.Customers;

public class ModifyCustomerRecordController implements Initializable  {
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
       Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionAddBtn(ActionEvent event) {
// Customer records and appointments can be added, updated, and deleted.

        // When adding and updating a customer, text fields are used to collect the following data: customer name, address, postal code, and phone number.
//-  Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and country data are collected using separate combo boxes.
//Note: The address text field should not include first-level division and country data. Please use the following examples to format addresses:
// •  U.S. address: 123 ABC Street, White Plains
//•  Canadian address: 123 ABC Street, Newmarket
//•  UK address: 123 ABC Street, Greenwich, London
//-  When updating a customer, the customer data autopopulates in the form.
    }


    @FXML
    void onActionDeleteBtn(ActionEvent event) {
// Customer records and appointments can be added, updated, and deleted.
//-  When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.

// When a customer record is deleted, a custom message should display in the user interface.
    }

    @FXML
    void onActionUpdateBtn(ActionEvent event) {
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


    }


//Country and first-level division data is prepopulated in separate combo boxes or lists in the user interface for the user to choose.
// The first-level list should be filtered by the user’s selection of a country (e.g., when choosing U.S., filter so it only shows states).


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
