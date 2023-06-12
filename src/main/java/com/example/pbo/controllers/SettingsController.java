package com.example.pbo.controllers;

import com.mysql.cj.Query;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import DatabaseConnection.DatabaseConnection;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private TextField tfUsername;
    @FXML
    private Button btnPilihFoto;
    @FXML
    private Button btnSimpan;
    private Label ouput;
    @FXML
    private Label labelFoto;

    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void setBtnSimpan(ActionEvent actionEvent) {
        updateData();
        ouput.setText("Pergantian Username berhasil");
    }

    public void updateData() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String username = tfUsername.getText();
        String query = "SELECT Username FROM kasir";
        String updateQuery = "UPDATE kasir SET Username = ?";
        try {
            // Mengambil data dari database
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String currentUsername = resultSet.getString("Username");
                tfUsername.setText(currentUsername);

                // Update data di database
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, username);
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data berhasil diupdate.");
                } else {
                    System.out.println("Tidak ada data yang diupdate.");
                }

                updateStatement.close();
            } else {
                tfUsername.setText("Data tidak ditemukan");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void setBtnPilihFoto(ActionEvent actionEvent){
        try{
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(f.toString());
            Image image = icon.getImage().getScaledInstance((int) labelFoto.getWidth(), (int) labelFoto.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon ic = new ImageIcon(image);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "error Upload"+ e);
        }
    }
}


