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
import java.util.logging.Logger;

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

        Map<String, Double> dataMap = new HashMap<>();
        for (Currency data : currencies) {
            dataMap.put(data.symbol.toUpperCase(), data.current_price * 1.09);
        }

        // PROPERLY PRINT DATA
        Logger logger = Logger.getLogger("APICoinGeckoFetcher");
        logger.info("APICoinGeckoFetcher: " + dataMap.size() + " actions fetched");
        return dataMap;
    }

    class Currency {
        public String id;
        public String symbol;
        public String name;
        public double current_price;
        public long market_cap;
        public long total_volume;
        public double high_24h;
        public double low_24h;
        public double price_change_24h;
        public double price_change_percentage_24h;
        public double market_cap_change_24h;
        public double market_cap_change_percentage_24h;
        public Double total_supply;
        public Double max_supply;
        public String last_updated;
    }


}
