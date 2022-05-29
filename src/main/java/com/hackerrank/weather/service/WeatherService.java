package com.hackerrank.weather.service;

import com.hackerrank.weather.controller.dto.WeatherRequest;
import com.hackerrank.weather.controller.dto.WeatherResponse;

import java.util.Date;
import java.util.List;

public interface WeatherService {
    WeatherResponse save(WeatherRequest weatherRequest);

    List<WeatherResponse> findAllByDateOrCityAndSorted(Date date, List<String> city, String sort);

    WeatherResponse findById(Integer id);
}
