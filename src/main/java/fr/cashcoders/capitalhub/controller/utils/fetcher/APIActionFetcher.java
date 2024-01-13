package fr.cashcoders.capitalhub.controller.utils.fetcher;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton.dotenv;

public class APIActionFetcher implements APIFetcherInterface {

    // 30 per HOUR
    private static final String API_ALPHAVANTAGE_URL = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=INSERTSEARCH&apikey=INSERTAPI";
    private static final String API_ALPHAVANTAGE_KEY = dotenv.get("ALPHAVANTAGE_API_KEY");

    public static void main(String[] args) throws IOException {
        new APIActionFetcher().fetch();
    }

    public Map<String, Double> fetch() throws IOException {
        // Create a neat value object to hold the URL
        String urlString = API_ALPHAVANTAGE_URL.replaceFirst("INSERTSEARCH", "");

        URL url = new URL(API_ALPHAVANTAGE_URL + API_ALPHAVANTAGE_KEY);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();

        Gson gson = new Gson();
        Map<String, Double> data = gson.fromJson(new String(responseStream.readAllBytes()), Map.class);
        System.out.println(data);

        return data;
    }

}
