package com.example.pbo.controllers;

import DatabaseConnection.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
public class DashboardController implements Initializable{
    @FXML
    private TextField Platnomer;
    @FXML
    private Button btnLoad,btnDashboard,btnSetting,btnExit,btnKendaraan;
    @FXML
    private TableView tabelKendaraan;
    @FXML
    private TableColumn colum1,colum2,colum3,colum4;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
