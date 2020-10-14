package com.netguru.maxtemperature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netguru.maxtemperature.model.OpenWeatherMap;
import com.netguru.maxtemperature.model.WeatherStack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MaxTemperatureApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_If_Open_Weather_Mapping_Is_Good() {
        String jsonResponse = "{\"id\": 6266510626521088," +
                "\"weather_state_name\": \"Light Rain\"," +
                "\"weather_state_abbr\": \"lr\"," +
                "\"wind_direction_compass\": \"WNW\"," +
                "\"created\": \"2020-07-13T10:22:58.484305Z\"," +
                "\"applicable_date\": \"2020-07-13\"," +
                "\"min_temp\": 13.165000000000001," +
                "\"max_temp\": 20.369999999999997," +
                "\"the_temp\": 18.445," +
                "\"wind_speed\": 6.54980578194809," +
                "\"wind_direction\": 299.50115904496226," +
                "\"air_pressure\": 1022," +
                "\"humidity\": 68," +
                "\"visibility\": 11.221653046210132," +
                "\"predictability\": 75}";

        try {
            OpenWeatherMap openWeatherMap = objectMapper.readValue(jsonResponse, OpenWeatherMap.class);

            assertThat(openWeatherMap.getId()).isEqualTo(6266510626521088l);
            assertThat(openWeatherMap.getMaxTemp()).isEqualTo(20.369999999999997f);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_If_Weather_Stack_Mapping_Is_Good(){
        String jsonResponse = "{\"coord\": {\"lon\": 139.01,\"lat\": 35.02},\"weather\": [{\"id\": 800,\"main\": \"Clear\",\"description\": \"clear sky\",\"icon\": \"01n\"}],\"base\": \"stations\",\"main\": {\"temp\": 285.514,\"pressure\": 1013.75,\"humidity\": 45,\"temp_min\": 20,\"temp_max\": 30},\"wind\": {\"speed\": 5.52,\"deg\": 311},\"clouds\": {\"all\": 0},\"dt\": 1485792967,\"sys\": {\"message\": 0.0025,\"country\": \"JP\",\"sunrise\": 1485726240,\"sunset\": 1485763863},\"id\": 1907296,\"name\": \"Warszawa\",\"cod\": 200}";

        try {
            WeatherStack weatherStack = objectMapper.readValue(jsonResponse, WeatherStack.class);

            assertThat(weatherStack.getWeatherStackMain().getTempMax()).isEqualTo(30f);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
