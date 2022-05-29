package com.hackerrank.weather.service.mapper.impl;

import com.hackerrank.weather.controller.dto.WeatherRequest;
import com.hackerrank.weather.controller.dto.WeatherResponse;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.mapper.WeatherMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public WeatherResponse toResponse(Weather domain) {
        return WeatherResponse
                .builder()
                .id(domain.getId())
                .date(domain.getDate())
                .lat(domain.getLat())
                .lon(domain.getLon())
                .city(domain.getCity())
                .state(domain.getState())
                .temperatures(domain.getTemperatures())
                .build();
    }

    @Override
    public List<WeatherResponse> toResponseList(List<Weather> modelList) {
        return modelList.parallelStream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public Weather toModel(WeatherRequest request) {
        return Weather
                .builder()
                .date(request.getDate())
                .lat(request.getLat())
                .lon(request.getLon())
                .city(request.getCity())
                .state(request.getState())
                .temperatures(request.getTemperatures())
                .build();
    }
}
