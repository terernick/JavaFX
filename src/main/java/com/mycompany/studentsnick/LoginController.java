/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentsnick;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Nick
 */
public class LoginController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void register(ActionEvent event) throws SQLException, IOException {
        Window owner = loginButton.getScene().getWindow();
        
       if (name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your name");
         return;
        }
                     
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your password");
         return;
        }
        String nameId = name.getText();
        String passwordId = password.getText();
        JdbcDao2 jdbcDao = new JdbcDao2();
        boolean flag = jdbcDao.validate(nameId, passwordId);
        
        if (!flag) { 
            infoBox("Please enter correct Name and Password", null, "Failed");
        } else { 
            //infoBox("Login Successful!", null, "Failed"); 
            
            //This below line of code closes window ones you click the button
            ((Stage)name.getScene().getWindow()).close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/homepage.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Homepage");
            stage.setScene(new Scene(root));   
            stage.setMaximized(true);
            stage.setResizable(true);
            stage.show();
            
        }
        
    }
    public static void infoBox(String infoMessage, String headerText, String title) {        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage); 
        alert.setTitle(title); 
        alert.setHeaderText(headerText); 
        alert.showAndWait(); }

    @FXML
    private void cancel(ActionEvent event) {
        System.exit(0);
    }
    



private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
       
        Alert alert = new Alert(alertType); 
        alert.setTitle(title); 
        alert.setHeaderText(null); 
        alert.setContentText(message); 
        alert.initOwner(owner); 
        alert.show();
    }
}