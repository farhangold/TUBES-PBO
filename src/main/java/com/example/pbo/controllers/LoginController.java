package com.example.pbo.controllers;

import DatabaseConnection.DatabaseConnection;
import com.example.pbo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField tfPassword,tfUsername;
    @FXML
    private Label ouput;
    private Window mywindow;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void btnLoginAction(ActionEvent actionEvent) {
        if(tfUsername.getText().equals("")){
            ouput.setText("Username harus di isi!");
        }else if(tfPassword.getText().equals("")){
            ouput.setText("Password harus di isi!");
        }else{
            validateLogin(actionEvent);
        }
    }
    private void validateLogin(ActionEvent event) {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String query = "select count(1) from kasir where Username='"+ tfUsername.getText()+"' and Password ='"+tfPassword.getText()+"'";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    mywindow = ouput.getScene().getWindow();
                    Stage stage = (Stage) mywindow;
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 700, 500);
                    stage.setTitle("Login Page");
                    stage.setScene(scene);
                    stage.show();

                }else{
                    showErrorMessage("Username/password salah");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    private void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
