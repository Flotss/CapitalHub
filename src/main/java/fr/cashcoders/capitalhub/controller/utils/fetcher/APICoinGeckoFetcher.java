package fr.cashcoders.capitalhub.controller.utils.fetcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APICoinGeckoFetcher implements APIFetcherInterface {

    // 30 calls/minute
    private static final String API_COINGECKO = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&order=market_cap_desc&per_page=20&page=1&sparkline=false&locale=en";


    public Map<String, Double> fetch() throws IOException {
        URL url = new URL(API_COINGECKO);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();

        Gson gson = new Gson();
        Type currencyListType = new TypeToken<ArrayList<Currency>>() {
        }.getType();
        List<Currency> currencies = gson.fromJson(new String(responseStream.readAllBytes()), currencyListType);

        // Convert List<Currency> to Map<String, Double>
        Map<String, Double> dataMap = new HashMap<>();
        for (Currency data : currencies) {
            dataMap.put(data.name, data.current_price);
        }


        return dataMap;
    }

    class Currency {
        String id;
        String symbol;
        String name;
        double current_price;
        long market_cap;
        long total_volume;
        double high_24h;
        double low_24h;
        double price_change_24h;
        double price_change_percentage_24h;
        double market_cap_change_24h;
        double market_cap_change_percentage_24h;
        Double total_supply;
        Double max_supply;
        String last_updated;
    }


}
