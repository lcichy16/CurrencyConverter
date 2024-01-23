module com.example.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens com.example.currencyconverter to javafx.fxml;

    exports com.example.currencyconverter;
}
