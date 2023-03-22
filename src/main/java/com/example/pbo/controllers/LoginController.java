package com.example.pbo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField tfPassword,tfUsername;
    @FXML
    private Label ouput;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnLoginAction(ActionEvent actionEvent) {
        if(tfUsername.getText().equals("")){
            ouput.setText("Username harus di isi!");
        }else if(tfPassword.getText().equals("")){
            ouput.setText("Password harus di isi!");
        }
    }
}
