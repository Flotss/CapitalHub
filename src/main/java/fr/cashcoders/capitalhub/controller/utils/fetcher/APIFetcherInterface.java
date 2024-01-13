package fr.cashcoders.capitalhub.controller.utils.fetcher;

import java.io.IOException;
import java.util.Map;

public interface APIFetcherInterface {
    Map<String, Double> fetch() throws IOException;
}
