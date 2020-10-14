package com.netguru.maxtemperature.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netguru.maxtemperature.exceptions.ReadURLsException;
import com.netguru.maxtemperature.model.OpenWeatherMap;
import com.netguru.maxtemperature.model.WeatherStack;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@AllArgsConstructor
public class TemperatureCalculationService {

    private static final String OPEN_WEATHER_URL = "https://run.mocky.io/v3/f79d05ce-a629-46cc-a0f9-9ed0aa53d74b";
    private static final String WEATHER_STACK_URL = "https://run.mocky.io/v3/c8238a21-90c2-4c73-9c25-1e095fd6e290";

    private final ObjectMapper objectMapper;

    public Float calculateMaxTempFromWeatherUrls() {
        OpenWeatherMap openWeatherMap = null;
        WeatherStack weatherStack = null;
        try {
            openWeatherMap = retrieveFromOpenWeather();
            weatherStack = retrieveFromWeatherStack();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        Float result = (openWeatherMap.getMaxTemp() + weatherStack.getWeatherStackMain().getTempMax()) / 2f;

        return result;
    }

    private OpenWeatherMap retrieveFromOpenWeather() throws JsonProcessingException {
        String response = "";

        try {
            response = readWeatherDataFromUrl(OPEN_WEATHER_URL);
        } catch (ReadURLsException e) {
            e.printStackTrace();
        }

        OpenWeatherMap openWeatherMap = objectMapper.readValue(response, OpenWeatherMap.class);
        return openWeatherMap;
    }

    private WeatherStack retrieveFromWeatherStack() throws JsonProcessingException {
        String response = "";

        try {
            response = readWeatherDataFromUrl(WEATHER_STACK_URL);
        } catch (ReadURLsException e) {
            e.printStackTrace();
        }

        WeatherStack weatherStack = objectMapper.readValue(response, WeatherStack.class);
        return weatherStack;
    }

    private String readWeatherDataFromUrl(String urlLink) throws ReadURLsException {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlLink);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                content.append(inputLine);
            }
            input.close();
            connection.disconnect();

        } catch (IOException e) {
            throw new ReadURLsException("Exception occured on reading urls");
        }

        return content.toString();
    }

}
