package com.hackerrank.weather.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WeatherResponse {
    private Integer id;
    private Date date;
    private Float lat;
    private Float lon;
    private String city;
    private String state;
    private List<Double> temperatures;
}
