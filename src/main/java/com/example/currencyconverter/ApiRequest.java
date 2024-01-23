package com.example.currencyconverter;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiRequest {

    public static String sendRequest() throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.nbp.pl/api/exchangerates/rates/a/usd/"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonParser parser = new JsonParser();
                JsonObject jsonResponse = parser.parse(response.body()).getAsJsonObject();

                if (jsonResponse.has("rates") && jsonResponse.getAsJsonArray("rates").size() > 0) {
                    JsonObject ratesObject = jsonResponse.getAsJsonArray("rates").get(0).getAsJsonObject();
                    if (ratesObject.has("mid")) {
                        String exchangeRate = ratesObject.getAsJsonPrimitive("mid").getAsString();
                        return exchangeRate;
                    }
                }

                System.out.println("Błąd: Brak klucza 'mid' w odpowiedzi JSON");
                return "Błąd: Brak klucza 'mid' w odpowiedzi JSON";
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
