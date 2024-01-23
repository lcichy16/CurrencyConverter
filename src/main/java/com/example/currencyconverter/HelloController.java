package com.example.currencyconverter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    @FXML
    private Label resultLabel;

    @FXML
    private AnchorPane mainPane;

    private boolean isDarkTheme = false;

    @FXML
    protected void onConvertButtonClick() {
        try {
            String exchangeRate = ApiRequest.sendRequest();
            resultLabel.setText("Exchange Rate: " + exchangeRate);
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    protected void toggleTheme() {
        isDarkTheme = !isDarkTheme;
        updateTheme();
    }

    private void updateTheme() {
        String themeStylesheet = isDarkTheme ? "dark-theme.css" : "light-theme.css";
        mainPane.getStylesheets().clear();
        mainPane.getStylesheets().add(getClass().getResource(themeStylesheet).toExternalForm());
    }
}
