module com.example.pbo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pbo to javafx.fxml;
    exports com.example.pbo;
}