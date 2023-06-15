package com.example.pbo.controllers;

import com.example.pbo.HelloApplication;
import com.example.pbo.model.Kendaraan;
import com.mysql.cj.Query;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import DatabaseConnection.DatabaseConnection;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnPilihFoto;
    @FXML
    private Button btnSimpan;
    private Label ouput;
    @FXML
    private Label labelFoto;

    @FXML
    private Window mywindow;
    public void initialize(URL location, ResourceBundle resources) {
        setUsername();
    }

    public void ActionClickbtnDasboard(ActionEvent event){
        mywindow = tfUsername.getScene().getWindow();
        Stage stage = (Stage) mywindow;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }
    public void btnclicklogout(ActionEvent event){
        mywindow = tfUsername.getScene().getWindow();
        Stage stage = (Stage) mywindow;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Setting");
        stage.setScene(scene);
        stage.show();
    }
    public void actionClickBtnTambahKendaraan(ActionEvent event){
        mywindow = tfUsername.getScene().getWindow();
        Stage stage = (Stage) mywindow;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TambahKendaraan.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Tambah Kendaraan");
        stage.setScene(scene);
        stage.show();
    }
    public void setUsername(){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String username = "";
        String password = "";
        String query = "SELECT * FROM Kasir";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                username = resultSet.getString("username");
                password = resultSet.getString("password");
            }
            tfUsername.setText(username);
            tfPassword.setText(password);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Status");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void updateData() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        try {
            String query = "UPDATE Kasir SET username=? , password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.executeUpdate();
            tfUsername.setText(username);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Status");
            alert.setContentText("Update Username dan password berhasil");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Status");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}


