package fr.cashcoders.capitalhub.controller.utils.fetcher;

import java.io.IOException;
import java.util.Map;

/**
 * The APIFetcherInterface is an interface that defines a method for fetching data from an API.
 * Implementing classes should provide the specific implementation of the 'fetch' method to retrieve data.
 */
public interface APIFetcherInterface {

    /**
     * Fetches data from an API and returns it as a map of string keys and double values.
     *
     * @return A map representing the fetched data, where keys are string identifiers and values are double values.
     * @throws IOException If an I/O error occurs during the API data fetching process.
     */
    Map<String, Double> fetch() throws IOException;
}
