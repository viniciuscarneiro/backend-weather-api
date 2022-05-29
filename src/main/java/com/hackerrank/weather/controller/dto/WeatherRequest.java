package com.hackerrank.weather.controller.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WeatherRequest {
    private Date date;
    private Float lat;
    private Float lon;
    private String city;
    private String state;
    private List<Double> temperatures;
}
