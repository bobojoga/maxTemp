package com.netguru.maxtemperature.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherStackMain {

    @JsonProperty("tempMax")
    @JsonAlias("temp_max")
    private Float tempMax;

    public Float getTempMax() {
        return tempMax;
    }

    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }
}
