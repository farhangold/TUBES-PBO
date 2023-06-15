package com.example.pbo.controllers;

import DatabaseConnection.DatabaseConnection;
import com.example.pbo.HelloApplication;
import com.example.pbo.interfaces.Kasir;
import com.example.pbo.model.Kendaraan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TambahKendaraan implements Initializable, Kasir {
    @FXML
    public TextField DateTime;
    @FXML
    public TextField TambahPlatNomer;
    @FXML
    public DatePicker DateTimePicker;
    @FXML
    private TableView<Kendaraan> tabelKendaraan;
    @FXML
    private TableColumn<Kendaraan, String> col1;
    @FXML
    private TableColumn<Kendaraan, String> col2;
    @FXML
    private TableColumn<Kendaraan, String> col3;
    @FXML
    private TableColumn<Kendaraan, Integer> col4;
    @FXML
    private TableColumn<Kendaraan, Integer> col5;

    @FXML
    private ComboBox listJenis;
    @FXML
    public Button btnTambah;

    private Window mywindow;
    private ActionEvent event;

    public void ActionClickbtnDasboard(ActionEvent event){
        mywindow = btnTambah.getScene().getWindow();
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
    public void actionClickbtnSetting(ActionEvent event){
        mywindow = TambahPlatNomer.getScene().getWindow();
        Stage stage = (Stage) mywindow;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Settings.fxml"));
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
    @Override
    public ResultSet getPengemudiByPlatNomor() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        try {
            String query1 = "Select * FROM Kendaraan WHERE plat='" + TambahPlatNomer.getText() + "'";
            Statement statement1 = connection.createStatement();
            ResultSet hasil = statement1.executeQuery(query1);
            String jenis = "";
            String start = "";
            while (hasil.next()) {
                jenis = hasil.getString("jenis");
                start = hasil.getString("start");
            }
            return hasil;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void tambah_data() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        try{
            String sql = "INSERT INTO kendaraan (plat, start, jenis, end, harga) VALUES (?, ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            String plat = TambahPlatNomer.getText();
            statement.setString(1,plat);
            statement.setString(2, DateTimePicker.getValue().toString()+" "+LocalTime.now().toString());
            statement.setString(3,listJenis.getValue().toString());
            statement.setString(4, "-");
            statement.setString(5, "0");
            statement.executeUpdate();
            TambahPlatNomer.setText("");
            DateTimePicker.setValue(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setContentText("Data Berhasil Diinput!!");
            alert.showAndWait();
            read();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            updatekendaraan();
        }
    }
    public void ActionClickbtnTambah(ActionEvent event){
        tambah_data();
    }
    public void read() {
        ObservableList<Kendaraan> data = FXCollections.observableArrayList();
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String query = "select * from kendaraan";
        try {
            Statement statement =  connection.createStatement();
            ResultSet output = statement.executeQuery(query);
            while (output.next()){
                data.add(new Kendaraan(output.getString("plat"),output.getString("jenis"),output.getString("start"),output.getInt("harga"),output.getString("end")));
            }
            col1.setCellValueFactory(new PropertyValueFactory<>("plat"));
            col2.setCellValueFactory(new PropertyValueFactory<>("jenis"));
            col3.setCellValueFactory(new  PropertyValueFactory<>("start"));
            col4.setCellValueFactory(new PropertyValueFactory<>("end"));
            col5.setCellValueFactory(new PropertyValueFactory<>("harga"));

            tabelKendaraan.setItems(data);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText( e.getMessage());
            alert.showAndWait();
        }
    }
    @Override
    public void updatekendaraan(){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        try{
            String query = "UPDATE Kendaraan SET start=?,end=?,harga= ? WHERE plat=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,DateTimePicker.getValue().toString()+" "+LocalTime.now().toString());
            statement.setString(2,"-");
            statement.setFloat(3, 0);
            statement.setString(4, TambahPlatNomer.getText());
            statement.executeUpdate();
            read();
            TambahPlatNomer.setText("");
            DateTimePicker.setValue(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setContentText("Data Berhasil Diupdate!!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText( e.getMessage());
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        read();
        listJenis.setItems(FXCollections.observableArrayList("Motor","Mobil","Bus","Truck"));
    }

    public void btnclicklogout(ActionEvent event){
        mywindow = TambahPlatNomer.getScene().getWindow();
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
}
