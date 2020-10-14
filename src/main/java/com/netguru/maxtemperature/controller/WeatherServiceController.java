package com.netguru.maxtemperature.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netguru.maxtemperature.service.TemperatureCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherServiceController {

    final TemperatureCalculationService temperatureCalculationService;

    @GetMapping("/temperature")
    public ResponseEntity<String> readTemperature() {
        Float result = temperatureCalculationService.calculateMaxTempFromWeatherUrls();
        if (result != null) {
            return new ResponseEntity<>(String.valueOf(result), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There has been a problem with calculation.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
