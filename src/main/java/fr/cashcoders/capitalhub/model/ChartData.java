package fr.cashcoders.capitalhub.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartData {
    private final List<String> labels;
    private final List<BigDecimal> data;

    public ChartData(List<String> labels, List<BigDecimal> data) {
        if (labels.size() != data.size()) {
            throw new IllegalArgumentException("Labels and data must have the same size");
        }

        this.labels = labels;
        this.data = data;
    }

    public ChartData() {
        this.labels = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    public void addLabel(String label) {
        this.labels.add(label);
    }

    public boolean addData(BigDecimal data) {
        return this.data.add(data);
    }

    public boolean removeLabel(String label) {
        return this.labels.remove(label);
    }

    public boolean removeData(BigDecimal data) {
        return this.data.remove(data);
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<BigDecimal> getData() {
        return data;
    }
}
