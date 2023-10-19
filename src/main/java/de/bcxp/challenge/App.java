package de.bcxp.challenge;

import java.util.List;
import java.util.Map;


/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …

        //set CSV Paths
        String weatherFilePath = "src/main/resources/de/bcxp/challenge/weather.csv";
        String countryFilePath = "src/main/resources/de/bcxp/challenge/countries.csv";

        //load reader instances with parameters
        CsvReader csvWeatherReader = new CsvReader(weatherFilePath, ',', "MxT", "MnT");
        CsvReader csvCountryReader = new CsvReader(countryFilePath, ';', "Population", "Area (km²)");

        //get data from readers
        Map<String, Pair<Float, Float>> weatherData = csvWeatherReader.read();
        Map<String, Pair<Float, Float>> countryData = csvCountryReader.read();

        //initiate databases
        WeatherDatabase weather = new WeatherDatabase(weatherData);
        CountryDatabase country = new CountryDatabase(countryData);

        //calculate (changed to List<String> because of the possibility of multiple solutions)
        List<String> weatherDays = weather.calculate();
        List<String> denseCountries = country.calculate();

        System.out.printf("Day(s) with smallest temperature spread: %s%n", weatherDays);
        System.out.printf("Country/Countries with highest population density: %s%n", denseCountries);
    }
}
