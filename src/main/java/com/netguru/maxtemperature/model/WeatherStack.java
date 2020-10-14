package com.netguru.maxtemperature.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherStack {

    @JsonProperty("weatherStackMain")
    @JsonAlias("main")
    private WeatherStackMain weatherStackMain;

    public WeatherStackMain getWeatherStackMain() {
        return weatherStackMain;
    }

    public void setWeatherStackMain(WeatherStackMain weatherStackMain) {
        this.weatherStackMain = weatherStackMain;
    }
}
