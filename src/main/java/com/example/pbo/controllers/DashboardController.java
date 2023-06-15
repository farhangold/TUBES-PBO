package com.example.pbo.controllers;

import DatabaseConnection.DatabaseConnection;
import com.example.pbo.HelloApplication;
import com.example.pbo.interfaces.Transaksi;
import com.example.pbo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardController implements Initializable, Transaksi {
    float harga = 0;
    Motor motor = new Motor();
    Mobil mobil = new Mobil();
    Truck truck = new Truck();
    Bus bus = new Bus();
    @FXML
    private TextField Platnomer;
    @FXML
    private Button btnLoad,btnDashboard,btnSetting,btnExit,btnKendaraan,btnDelete;
    @FXML
    private TableView<Kendaraan> tabelKendaraan;
    @FXML
    private TableColumn<Kendaraan, String> col1;
    @FXML
    private TableColumn<Kendaraan, String> col2;
    @FXML
    private TableColumn<Kendaraan, String> col3;
    @FXML
    private TableColumn<Kendaraan, String> col4;
    @FXML
    private TableColumn<Kendaraan, Integer> col5;

    @FXML
    private Window mywindow;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        read();
    }

    public void actionClickBtnTambahKendaraan(ActionEvent event){
        mywindow = btnKendaraan.getScene().getWindow();
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
    public void actionClickBtnSetting(ActionEvent event){
        mywindow = Platnomer.getScene().getWindow();
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
    public void btnclicklogout(ActionEvent event){
        mywindow = Platnomer.getScene().getWindow();
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
    public void actionClickBtnCheckout(ActionEvent event){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String myend = LocalDate.now()+" "+ LocalTime.now();
        try{
            String query1 = "Select * FROM Kendaraan WHERE plat='"+Platnomer.getText()+"'";
            Statement statement1 =  connection.createStatement();
            ResultSet hasil = statement1.executeQuery(query1);

            String jenis = "";
            String start = "";
            while (hasil.next()){
                jenis = hasil.getString("jenis");
                start = hasil.getString("start");
            }
            harga = total_bayar(jenis, start, myend);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status");
            alert.setContentText("Pembayaran "+Platnomer.getText()+" totalnya "+ harga);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText( e.getMessage());
            alert.showAndWait();
        }
        try {
            String query = "UPDATE Kendaraan SET end=?,harga= ? WHERE plat=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,myend);
            statement.setFloat(2, harga);
            statement.setString(3, Platnomer.getText());
            statement.executeUpdate();
            read();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Status");
            alert.setContentText("Terjadi kesalahan saat mengupdate data di database: "+e.getMessage());
            alert.showAndWait();
        }
    }

    public void delete (){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String plat = Platnomer.getText();

        if(!plat.isEmpty()){
            try{
                String sql = "DELETE FROM kendaraan WHERE plat = '"+plat+"'";
                PreparedStatement delete = connection.prepareStatement(sql);
                int rd = delete.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Status");
                alert.setContentText("Data plat kendaraan "+Platnomer.getText()+" berhasil dihapus.");
                alert.showAndWait();
                delete.close();
                connection.close();
                read();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText("Status");
            alert.setContentText("Data plat kendaraan "+Platnomer.getText()+" tidak ditemukan.");
            alert.showAndWait();
        }


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
            e.printStackTrace();
        }
    }

    public void actionClickBtnLoad(ActionEvent event) {

        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        try {
            String query1 = "Select * FROM Kendaraan WHERE plat='" + Platnomer.getText() + "'";
            Statement statement1 = connection.createStatement();
            ResultSet hasil = statement1.executeQuery(query1);
            String jenis = "";
            String start = "";
            while (hasil.next()) {
                jenis = hasil.getString("jenis");
                start = hasil.getString("start");
            }
            if (jenis!="") {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(Platnomer.getText()+" Ditemukan");
                alert.setContentText( "Mulai Parkir: " + start.substring(0, 19));
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(Platnomer.getText()+" Tidak Ditemuakan");
                alert.setContentText("Plat Nomor Yang dicari tidak terdata di parkiran ini!!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public float total_bayar(String jenis, String start, String myend) {
        String regexDay = "\\d{4}-\\d{2}-(\\d{2})";
        String regex = "\\b\\d{2}(?=\\:\\d{2}\\:\\d{2}\\.\\d{9})";
        Pattern pattern = Pattern.compile(regex);
        Pattern patternday = Pattern.compile(regexDay);
        Matcher matcherDay = patternday.matcher(start);
        Matcher matcherDay2 = patternday.matcher(myend);
        int day = 0;
        if (matcherDay2.find()) {
            String day1 = matcherDay2.group(1);
            day += Integer.parseInt(day1);
        }
        if (matcherDay.find()) {
            String day1 = matcherDay.group(1);
            day -= Integer.parseInt(day1);
        }

        int hour = 0;

        Matcher matcher2 = pattern.matcher(myend);
        if (matcher2.find()) {
            String hour2 = matcher2.group();
            hour += Integer.parseInt(hour2);
        }
        Matcher matcher1 = pattern.matcher(start);
        if (matcher1.find()) {
            String hour1 = matcher1.group();
            hour -= Integer.parseInt(hour1);
        }
        if(jenis.equals("Motor")){
            harga = motor.getHargasat()*(hour+(day*24));
            if(harga == 0){
                harga = motor.getHargasat();
            }
        } else if (jenis.equals("Mobil")) {
            harga = mobil.getHargasat()*(hour+(day*24));
            if(harga == 0){
                harga = mobil.getHargasat();
            }
        }else if(jenis.equals("Truck")){
            harga = truck.getHargasat()*(hour+(day*24));
            if(harga == 0){
                harga = truck.getHargasat();
            }
        }else{
            harga = bus.getHargasat()*(hour+(day*24));
            if(harga == 0){
                harga = bus.getHargasat();
            }
        }
        return harga;
    }

}
