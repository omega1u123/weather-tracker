package com.example.weathertracker.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class HourWeather {

    private String time;

    private String description ;

    private int temp;

    private String windDirection;

    private Double windSpeed;


    @Bean
    public HourWeather hourWeather() {
        return new HourWeather();
    }

    public HourWeather() {
    }




}

