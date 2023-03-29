module com.example.pbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;
    requires javafx.graphics;
    requires mysql.connector.j;


    opens com.example.jafafxlearn to javafx.fxml;
    exports com.example.jafafxlearn;
    exports com.example.jafafxlearn.controller;
    opens com.example.jafafxlearn.controller to javafx.fxml;
}