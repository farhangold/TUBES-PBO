module com.example.pbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;
    requires javafx.graphics;
    requires mysql.connector.j;


    opens com.example.pbo to javafx.fxml;
    exports com.example.pbo;
    exports com.example.pbo.controllers;
    opens com.example.pbo.controller to javafx.fxml;
}