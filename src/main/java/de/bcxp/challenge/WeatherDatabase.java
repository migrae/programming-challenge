package de.bcxp.challenge;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class WeatherDatabase {

    private Map<String, Pair<Float, Float>> data = new HashMap<>();
    private List<String> relevantKeys = new ArrayList<>();

    public WeatherDatabase(Map<String, Pair<Float, Float>> weatherData) {
        this.data = weatherData;  
    }

    public List<String> calculate() {
        float shortestSpan = calculateShortestSpan();
        loadRelevantKeys(shortestSpan);
        return this.relevantKeys;
    }

    private float calculateShortestSpan() {
        float shortestSpan = Float.MAX_VALUE;

        for (Map.Entry<String, Pair<Float, Float>> entry : data.entrySet()) {
            float span = Calculator.calculate(entry.getValue().first, '-', entry.getValue().second);
            if (span < shortestSpan) {
                shortestSpan = span;
            }
        }
        return shortestSpan;
    }

    private void loadRelevantKeys(float shortestSpan) {
        for (Map.Entry<String, Pair<Float, Float>> entry : data.entrySet()) {
            float span = Calculator.calculate(entry.getValue().first, '-', entry.getValue().second);
            if (span == shortestSpan) {
                relevantKeys.add(entry.getKey());
            }
        }
    }

    public void setData(Map<String, Pair<Float, Float>> readerData) {
        data = readerData;
    }

    public Map<String, Pair<Float, Float>> getData() {
        return this.data;
    }

    public List<String> getRelevantKeys() {
        return this.relevantKeys;
    }
}

