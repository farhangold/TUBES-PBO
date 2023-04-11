package com.example.pbo.controllers;

import DatabaseConnection.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        String query = "select count(1) from Kasir where username='"+ tfUsername.getText()+"' and password ='"+tfPassword.getText()+"'";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    mywindow = ouput.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
                    Stage stage = (Stage) mywindow;
                    stage.setTitle("Dashboard");
                    stage.setScene(new Scene(root,700,500));
                    //stage.setResizable(false);
                    stage.show();
                }else{
                    ouput.setText("Invalid login, please try again");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
