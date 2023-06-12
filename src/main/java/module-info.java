module com.example.pbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;
    requires javafx.graphics;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.example.pbo to javafx.fxml;
    opens com.example.pbo.model to javafx.base;
    exports com.example.pbo;
    exports com.example.pbo.controllers;
    opens com.example.pbo.controllers to javafx.fxml;
    exports com.example.pbo.interfaces;
    opens com.example.pbo.interfaces to javafx.fxml;
}