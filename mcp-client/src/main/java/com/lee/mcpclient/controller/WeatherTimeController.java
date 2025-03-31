package com.lee.mcpclient.controller;

import com.lee.mcpclient.service.WeatherTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherTimeController {

    private final WeatherTimeService weatherTimeService;

    @Autowired
    public WeatherTimeController(WeatherTimeService weatherTimeService) {
        this.weatherTimeService = weatherTimeService;
    }

    @GetMapping("/weather-time")
    public String getWeatherAndTime(@RequestParam String city) {
        return weatherTimeService.getWeatherAndTime(city);
    }
}