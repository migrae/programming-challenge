package de.bcxp.challenge;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonReader {

    private String keyCategorie_;
    private String file_;
    private String strValueOne_;
    private String strValueTwo_;
    private Map<String, Pair<Float, Float>> data_ = new HashMap<>();

    public JsonReader(String keyCategorie, String file, String valueOne, String valueTwo) {
        this.keyCategorie_ = keyCategorie;
        this.file_ = file;
        this.strValueOne_ = valueOne;
        this.strValueTwo_ = valueTwo;
    }

    public Map<String, Pair<Float, Float>> read() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(this.file_)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String key = jsonObject.getString(this.keyCategorie_);
                float valueOne = (float) jsonObject.getDouble(this.strValueOne_);
                float valueTwo = (float) jsonObject.getDouble(this.strValueTwo_);
                data_.put(key, new Pair<>(valueOne, valueTwo));
            }
        } catch (IOException e) {
            System.err.println("Can't open file");
            return null;
        }
        return data_;
    }

    public Map<String, Pair<Float, Float>> getData() {
        return data_;
    }
}
