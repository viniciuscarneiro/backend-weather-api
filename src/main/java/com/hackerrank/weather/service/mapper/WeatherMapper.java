package com.hackerrank.weather.service.mapper;

import com.hackerrank.weather.controller.dto.WeatherRequest;
import com.hackerrank.weather.controller.dto.WeatherResponse;
import com.hackerrank.weather.model.Weather;

import java.util.List;

public interface WeatherMapper {
    WeatherResponse toResponse(Weather domain);

    List<WeatherResponse> toResponseList(List<Weather> modelList);

    Weather toModel(WeatherRequest request);
}
