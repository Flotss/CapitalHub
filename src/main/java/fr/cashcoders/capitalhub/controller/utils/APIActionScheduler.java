package fr.cashcoders.capitalhub.controller.utils;

import fr.cashcoders.capitalhub.controller.Model;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static fr.cashcoders.capitalhub.controller.utils.APIDataFetcher.fetch;

public class APIActionScheduler {
    private final Model model;
    private ScheduledThreadPoolExecutor executor;


    public APIActionScheduler(Model model) {
        this.model = model;
        this.executor = new ScheduledThreadPoolExecutor(1);

        this.setSettings();
        this.run();
    }

    public void run() {
        executor.schedule(() -> {
            Map<String, Double> actions = null;
            try {
                actions = fetch();
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
