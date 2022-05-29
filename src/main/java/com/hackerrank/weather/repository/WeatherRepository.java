package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer>, JpaSpecificationExecutor<Weather> {
    List<Weather> findAll(Specification<Weather> weatherSpecification, Sort sort);
}
