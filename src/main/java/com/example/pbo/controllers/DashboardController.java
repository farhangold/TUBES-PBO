package com.example.pbo.controllers;

import DatabaseConnection.DatabaseConnection;
import com.example.pbo.HelloApplication;
import com.example.pbo.model.Pengemudi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class DashboardController implements Initializable{
    @FXML
    private TextField Platnomer;
    @FXML
    private Button btnLoad,btnDashboard,btnSetting,btnExit,btnKendaraan;
    @FXML
    private TableView<Pengemudi> tabelKendaraan;
    @FXML
    private TableColumn<Pengemudi, String> col1;
    @FXML
    private TableColumn<Pengemudi, String> col2;
    @FXML
    private TableColumn<Pengemudi, String> col3;
    @FXML
    private TableColumn<Pengemudi, String> col4;
    @FXML
    private TableColumn<Pengemudi, String> col5;
    @FXML
    private Window mywindow;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void actionClickBtnTambahKendaraan(ActionEvent event){
        mywindow = Platnomer.getScene().getWindow();
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
}
