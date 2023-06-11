package com.example.pbo.controllers;

import javafx.fxml.Initializable;
import DatabaseConnection.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    private TextField tfUsername;
    private Button btnPilihFoto;
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
