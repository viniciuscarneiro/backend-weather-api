package com.hackerrank.weather.service.impl;

import com.hackerrank.weather.controller.dto.WeatherRequest;
import com.hackerrank.weather.controller.dto.WeatherResponse;
import com.hackerrank.weather.error.exception.EntityNotFoundException;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import com.hackerrank.weather.service.WeatherService;
import com.hackerrank.weather.service.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

import static com.hackerrank.weather.model.Weather.*;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private static final Map<String, Sort.Order> sortFields = new HashMap<>();
    private static final String DATE_PROPERTY_SORT_ASC = "date";
    private static final String DATE_PROPERTY_SORT_DESC = "-date";
    private static final String WEATHER_NOT_FOUND_ERROR_MESSAGE = "Weather with id [%s] was not found.";

    static {
        sortFields.put(DATE_PROPERTY_SORT_ASC, new Sort.Order(ASC, DATE_PROPERTY));
        sortFields.put(DATE_PROPERTY_SORT_DESC, new Sort.Order(DESC, DATE_PROPERTY));
    }

    private final WeatherMapper weatherMapper;
    private final WeatherRepository weatherRepository;

    @Override
    public WeatherResponse save(WeatherRequest weatherRequest) {
        return weatherMapper.toResponse(weatherRepository.save(weatherMapper.toModel(weatherRequest)));
    }

    @Override
    public List<WeatherResponse> findAllByDateOrCityAndSorted(Date date, List<String> city, String sort) {
        return weatherMapper.toResponseList(weatherRepository.findAll(buildFindAllSpecification(Optional.ofNullable(date), Optional.ofNullable(city)), buildSort(sort)));
    }

    @Override
    public WeatherResponse findById(Integer id) {
        return weatherRepository.findById(id)
                .map(weatherMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format(WEATHER_NOT_FOUND_ERROR_MESSAGE, id)));
    }

    private Sort buildSort(String sort) {
        List<Sort.Order> sortOrders = new ArrayList<>();
        Optional
                .ofNullable(sort)
                .map(sortFields::get)
                .ifPresent(sortOrders::add);
        Sort.Order idSortOrder = new Sort.Order(ASC, ID_PROPERTY);
        sortOrders.add(idSortOrder);
        return Sort.by(sortOrders);
    }

    private Specification<Weather> buildFindAllSpecification(Optional<Date> optionalDate, Optional<List<String>> optionalCities) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            optionalDate.ifPresent(date -> predicates.add(criteriaBuilder.equal(root.get(DATE_PROPERTY), date)));

            optionalCities.ifPresent(cities -> {
                List<Predicate> citiesPredicates = cities
                        .parallelStream()
                        .map(city -> criteriaBuilder.equal(criteriaBuilder.lower(root.get(CITY_PROPERTY)), city.toLowerCase())).collect(Collectors.toList());
                predicates.add(criteriaBuilder.or(citiesPredicates.toArray(new Predicate[]{})));
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
