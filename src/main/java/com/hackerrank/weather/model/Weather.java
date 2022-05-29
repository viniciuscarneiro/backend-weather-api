package com.hackerrank.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Weather {

    public static final String ID_PROPERTY = "id";
    public static final String DATE_PROPERTY = "date";

    public static final String CITY_PROPERTY = "city";

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Date date;
    @Column
    private Float lat;
    @Column
    private Float lon;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    @ElementCollection
    private List<Double> temperatures;


    public Weather(Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures = temperatures;
    }
}
