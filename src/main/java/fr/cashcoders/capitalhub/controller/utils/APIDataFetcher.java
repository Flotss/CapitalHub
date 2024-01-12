package fr.cashcoders.capitalhub.controller.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APIDataFetcher {

    private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=demo";

    public static Map<String, Double> fetch() throws IOException {
        // Create a neat value object to hold the URL
        URL url = new URL(API_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();

        Gson gson = new Gson();
        Map<String, Double> data = gson.fromJson(new String(responseStream.readAllBytes()), Map.class);
        System.out.println(data);

        return data;
    }
}
