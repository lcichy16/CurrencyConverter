package com.example.currencyconverter;

import com.example.currencyconverter.ApiRequest;
import com.example.currencyconverter.HelloApplication;
import javafx.fxml.FXML;

public class HelloController {
    private HelloApplication mainApplication;

    public void setMainApplication(HelloApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    protected void onHelloButtonClick() {
        try {
            String apiResponse = ApiRequest.sendRequest();
            System.out.println("Odpowiedź z API: " + apiResponse);
        } catch (Exception e) {
            System.err.println("Błąd podczas wykonywania zapytania API: " + e.getMessage());
        }
    }
}
