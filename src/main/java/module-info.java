module com.example.pbo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pbo to javafx.fxml;
    exports com.example.pbo;
    exports com.example.pbo.controllers;
    opens com.example.pbo.controllers to javafx.fxml;
}