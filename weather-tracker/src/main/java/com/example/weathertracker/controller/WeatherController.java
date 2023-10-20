/*package com.example.weathertracker.controller;

import com.example.weathertracker.store.model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
public class WeatherController {
    @Autowired
    RestTemplate restTemp;

    @GetMapping("/weather")
    public void getWeather() throws IOException {

        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/weather")
                .path("")
                .query("q={keyword}&APPID={appid}")
                .buildAndExpand("Moscow","0a1ca86b61569b765fbcba868a5c685d");

        String uri = uriComponents.toUriString();
        System.out.println(uri);
        ResponseEntity<String> resp= restTemp.exchange(uri, HttpMethod.GET, null, String.class);

        ObjectMapper mapper = new ObjectMapper();
        Weather weather = mapper.readValue(resp.getBody(), Weather.class);

        System.out.println(weather.getWeatherDescription()+ " " + weather.getTemp());
        System.out.println(resp.getBody());

    }
}
*/