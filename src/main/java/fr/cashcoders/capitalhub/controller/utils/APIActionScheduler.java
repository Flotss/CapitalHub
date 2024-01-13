package fr.cashcoders.capitalhub.controller.utils;

import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.controller.utils.fetcher.APIActionFetcher;
import fr.cashcoders.capitalhub.controller.utils.fetcher.APICoinGeckoFetcher;
import fr.cashcoders.capitalhub.controller.utils.fetcher.APIFetcherInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class APIActionScheduler {
    private final Model model;
    private ScheduledThreadPoolExecutor executor;
    private List<APIFetcherInterface> fetchers;


    public APIActionScheduler(Model model) {
        this.model = model;
        this.executor = new ScheduledThreadPoolExecutor(1);
        this.fetchers = List.of(
                new APIActionFetcher(),
                new APICoinGeckoFetcher()
        );

        this.setSettings();
    }

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

    private void setSettings() {
        this.executor.setRemoveOnCancelPolicy(true);
    }
}
