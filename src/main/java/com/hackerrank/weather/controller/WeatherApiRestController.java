package com.hackerrank.weather.controller;

import com.hackerrank.weather.controller.dto.WeatherRequest;
import com.hackerrank.weather.controller.dto.WeatherResponse;
import com.hackerrank.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherApiRestController {
    private final WeatherService weatherService;

    @PostMapping
    public ResponseEntity<WeatherResponse> create(@RequestBody WeatherRequest request) {
        return ResponseEntity.status(CREATED).body(weatherService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<WeatherResponse>> get(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam(name = "city", required = false) List<String> cities,
            @RequestParam(required = false) String sort) {
        return ResponseEntity.ok(weatherService.findAllByDateOrCityAndSorted(date, cities, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(weatherService.findById(id));
    }
}
