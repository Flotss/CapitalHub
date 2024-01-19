package fr.cashcoders.capitalhub.controller.utils;

import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.controller.utils.fetcher.APICoinGeckoFetcher;
import fr.cashcoders.capitalhub.controller.utils.fetcher.APIFetcherInterface;
import fr.cashcoders.capitalhub.controller.utils.fetcher.APIFinnhubFetcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * The APIActionScheduler class schedules and runs periodic API data fetching tasks to update the Model with
 * the latest actions information. It uses a thread pool executor to perform scheduled fetch operations.
 */
public class APIActionScheduler {
    private final Model model;
    private final ScheduledThreadPoolExecutor executor;
    private final List<APIFetcherInterface> fetchers;


    /**
     * Initializes a new instance of the APIActionScheduler class with the specified Model.
     *
     * @param model The Model instance to be updated with fetched API data.
     */
    public APIActionScheduler(Model model) {
        this.model = model;
        this.executor = new ScheduledThreadPoolExecutor(1);
        this.fetchers = List.of(
                new APIFinnhubFetcher(),
                new APICoinGeckoFetcher()
        );

        this.setSettings();
    }


    /**
     * Schedules and runs periodic API data fetching tasks to update the Model with the latest actions information.
     * It runs the fetch tasks every minute.
     */
    public void run() {
        // Run every minute
        executor.schedule(() -> {
            Map<String, Double> actions = new HashMap<>();
            try {
                for (APIFetcherInterface fetcher : fetchers) {
                    actions.putAll(fetcher.fetch());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            model.updateActions(actions);

        }, 1, TimeUnit.MINUTES);
    }

    /**
     * Sets the executor's settings, including removing canceled tasks from the executor's queue.
     */
    private void setSettings() {
        this.executor.setRemoveOnCancelPolicy(true);
    }
}
