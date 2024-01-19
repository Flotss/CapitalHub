package fr.cashcoders.capitalhub.controller.utils.fetcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.model.Action;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton.dotenv;

public class APIFinnhubFetcher implements APIFetcherInterface {

    // 30 per HOUR
    private static final String API_FINNHUB_URL = "https://finnhub.io/api/v1/quote?symbol=INSERTSYMBOL&token=INSERTAPI";
    private static final String FINNHUB_API_KEY = dotenv.get("FINNHUB_API_KEY");

    public Map<String, Double> fetch() throws IOException {
        List<Action> actions = Model.actions;

        // Create a neat value object to hold the URL
        String urlString = API_FINNHUB_URL.replaceFirst("INSERTAPI", FINNHUB_API_KEY);

        Map<String, Double> data = new HashMap<>();
        Type dataFinnHub = new TypeToken<APIFinnhubFetcherData>() {
        }.getType();


        for (Action action : actions) {
            if (action.getSymbol().isEmpty()) {
                continue;
            }

            String apiURL = new String();
            apiURL = urlString.replaceFirst("INSERTSYMBOL", action.getSymbol());

            URL url = new URL(apiURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();

            Gson gson = new Gson();

            APIFinnhubFetcherData dataAPI = gson.fromJson(new String(responseStream.readAllBytes()), dataFinnHub);

            if (dataAPI.c != 0)
                data.put(action.getSymbol(), dataAPI.c);
        }


        // PROPERLY PRINT DATA
        Logger logger = Logger.getLogger("APIFinnhubFetcher");
        logger.info("APIFinnhubFetcher: " + data.size() + " actions fetched");
        data.forEach((key, value) -> System.out.println(key + " : " + value));

        return data;
    }

    // {"c":2.26,"d":0,"dp":0,"h":2.3393,"l":2.24,"o":2.27,"pc":2.26,"t":1705093202}

    class APIFinnhubFetcherData {
        public double c;
        public double d;
        public double dp;
        public double h;
        public double l;
        public double o;
        public double pc;
        public double t;

        @Override
        public String toString() {
            return "APIFinnhubFetcherData{" +
                    "c=" + c +
                    ", d=" + d +
                    ", dp=" + dp +
                    ", h=" + h +
                    ", l=" + l +
                    ", o=" + o +
                    ", pc=" + pc +
                    ", t=" + t +
                    '}';
        }
    }

}
