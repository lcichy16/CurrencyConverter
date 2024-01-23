package com.example.currencyconverter;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.io.IOException;

public class ApiRequest {

    public static String sendRequest() throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/rates/a/usd/"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                HttpHeaders headers = response.headers();
                headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
                return response.body();
            } else {
                System.out.println("Błąd: " + response.statusCode() + " - " + response.body());
                return "Błąd: " + response.statusCode() + " - " + response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Błąd podczas wysyłania żądania.";
        }
    }
}
