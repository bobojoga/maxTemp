package com.netguru.maxtemperature.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class OpenWeatherMap {

    @JsonProperty("id")
    @JsonAlias("id")
    private Long id;

    @JsonProperty("maxTemp")
    @JsonAlias("max_temp")
    private Float maxTemp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Float maxTemp) {
        this.maxTemp = maxTemp;
    }
}
