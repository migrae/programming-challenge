package de.bcxp.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {

    private String file;
    private char delimiter;
    private String valueOne;
    private String valueTwo;
    private int columnValueOne = -1;
    private int columnValueTwo = -1;
    private Map<String, Pair<Float, Float>> data = new HashMap<>();

    public CsvReader(String file, char delimiter, String valueOne, String valueTwo) {
        this.file = file;
        this.delimiter = delimiter;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public Map<String, Pair<Float, Float>> read() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line = br.readLine();
            columnValueOne = getColumnForValue(line, valueOne);
            columnValueTwo = getColumnForValue(line, valueTwo);

            if (columnValueOne == -1 || columnValueTwo == -1) {
                System.err.println("Invalid Column Names");
                return null;
            }

            while ((line = br.readLine()) != null) {
                if (loadData(line) == -1) {
                    System.err.println("Could not transform Values in line: " + line);
                    return null;
                }
            }
            if (data.size()	== 0) {
                 System.err.println("Empty csv file");
            }
        } catch (IOException e) {
            System.err.println("Can't open file");
            return null;
        }
        return data;
    }

    public Map<String, Pair<Float, Float>> getData() {
        return data;
    }

    private String parseEuFormat(String str) {
        try {
            float floatValue = Float.parseFloat(str);
        } catch (NumberFormatException e) {
            if (e.getMessage() == "multiple points"){
                return str.replace(".", "").replace(",", ".");
            }
        }
        return str;
    }

    private int loadData(String line) {
        String[] fields = line.split(String.valueOf(delimiter));
        String key = fields[0];
        String strValueOne = fields[columnValueOne];
        String strValueTwo = fields[columnValueTwo];
        float valueOne;
        float valueTwo;

        strValueOne = parseEuFormat(strValueOne);
        strValueTwo = parseEuFormat(strValueTwo);
        try {
            valueOne = Float.parseFloat(strValueOne);
            valueTwo = Float.parseFloat(strValueTwo);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return -1;
        }
        data.put(key, new Pair<>(valueOne, valueTwo));
        return 0;
    }

    private int getColumnForValue(String line, String value) {
        String[] fields = line.split(String.valueOf(delimiter));
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
