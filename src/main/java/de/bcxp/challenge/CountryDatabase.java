package de.bcxp.challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class CountryDatabase {

    private Map<String, Pair<Float, Float>> data = new HashMap<>();
    private List<String> relevantKeys = new ArrayList<>();

    public CountryDatabase(Map<String, Pair<Float, Float>> countryData) {
        this.data = countryData;
    }

    public List<String> calculate() {
        float highestDensity = calculateHighestDensity();
        loadRelevantKeys(highestDensity);
        return this.relevantKeys;
    }

    private float calculateHighestDensity() {
        float highestDensity = Float.MIN_VALUE;

        for (Map.Entry<String, Pair<Float, Float>> entry : data.entrySet()) {
            float density = Calculator.calculate(entry.getValue().first, '/', entry.getValue().second);
            if (density > highestDensity) {
                highestDensity = density;
            }
        }
        return highestDensity;
    }

    private void loadRelevantKeys(float highestDensity) {
        for (Map.Entry<String, Pair<Float, Float>> entry : data.entrySet()) {
            float density = Calculator.calculate(entry.getValue().first, '/', entry.getValue().second);
            if (density == highestDensity) {
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
