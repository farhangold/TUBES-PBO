package com.example.pbo.controllers;

import DatabaseConnection.DatabaseConnection;
import com.example.pbo.model.Pengemudi;
import com.example.pbo.model.Transaksi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TambahKendaraan implements Initializable {
    @FXML
    public TextField DateTime;
    @FXML
    public TextField TambahPlatNomer;
    @FXML
    public DatePicker DateTimePicker;
    @FXML
    public TextField JenisKendaraan;
    @FXML
    private TableView<Pengemudi> tabelKendaraan;
    @FXML
    private TableColumn<Pengemudi, String> col1;
    @FXML
    private TableColumn<Pengemudi, String> col2;
    @FXML
    private TableColumn<Pengemudi, String> col3;
    @FXML
    private TableColumn<Transaksi, Integer> col4;

    @FXML
    public Button btnTambah;


    public void ActionClickbtnTambah(ActionEvent event){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String sql = "INSERT INTO pengemudi (Plat, Start, Jenis, End) VALUES (?, ?, ?,?)";
        DateTimePicker = new DatePicker();
        LocalDate tanggal = DateTimePicker.getValue();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,TambahPlatNomer.getText());
            statement.setString(2, tanggal.toString());
            statement.setString(3,JenisKendaraan.getText());
            statement.setString(4, "-");
            statement.executeUpdate();
            TambahPlatNomer.setText("");
            DateTimePicker.setValue(null);
            JenisKendaraan.setText("");
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        col1.setCellValueFactory(data -> data.getValue().idProperty().asObject());
//        col2.setCellValueFactory(data -> data.getValue().nameProperty());
//        col3.setCellValueFactory(data -> data.getValue().ageProperty().asObject());
//        col4.setCellValueFactory(data -> data.getValue().nameProperty());
//        col5.setCellValueFactory(data -> data.getValue().ageProperty().asObject());

    }
}
