module com.example.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.currencyconverter to javafx.fxml;

    exports com.example.currencyconverter;
}
