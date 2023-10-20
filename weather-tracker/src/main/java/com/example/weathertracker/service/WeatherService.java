package com.example.weathertracker.service;

import com.example.weathertracker.store.CurrentWeather;
import com.example.weathertracker.store.HourWeather;
import com.example.weathertracker.store.entity.LocationEntity;
import com.example.weathertracker.store.model.api.ForecastApiResponse;
import com.example.weathertracker.store.model.api.WeatherApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherService {

    private static final String APP_ID = "0a1ca86b61569b765fbcba868a5c685d";
    private static final String BASE_API_URL = "https://api.openweathermap.org";
    private static final String WEATHER_API_URL_SUFFIX = "/data/2.5/weather";
    private static final String FORECAST_API_URL_SUFFIX = "/data/2.5/forecast";


    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();


    final RestTemplate restTemp;

    public WeatherService(RestTemplate restTemp) {
        this.restTemp = restTemp;
    }

    public CurrentWeather getCurrentWeather() throws IOException {

        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/weather")
                .path("")
                .query("q={keyword}&APPID={appid}")
                .buildAndExpand("Minsk","0a1ca86b61569b765fbcba868a5c685d");

        String uri = uriComponents.toUriString();
        System.out.println(uri);
        ResponseEntity<String> resp= restTemp.exchange(uri, HttpMethod.GET, null, String.class);

        ObjectMapper mapper = new ObjectMapper();
        CurrentWeather currentWeather = mapper.readValue(resp.getBody(), CurrentWeather.class);

        System.out.println(resp.getBody());

        System.out.println(currentWeather.toString());

        return currentWeather;

    }

    public HourWeather getHourWeather() throws IOException {

        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .path("")
                .query("q={keyword}&APPID={appid}&cnt=40")
                .buildAndExpand("Minsk","0a1ca86b61569b765fbcba868a5c685d");

        String uri = uriComponents.toUriString();
        System.out.println(uri);
        ResponseEntity<String> resp= restTemp.exchange(uri, HttpMethod.GET, null, String.class);

        ObjectMapper mapper = new ObjectMapper();
        HourWeather hourWeather = mapper.readValue(resp.getBody(), HourWeather.class);

        System.out.println("HOUR WEATHER");


        String response = resp.getBody();


        return hourWeather;

    }


    public ForecastApiResponse getForecastForLocation(LocationEntity location) throws IOException, InterruptedException {
            URI uri = buildUriForForecastRequest(location);
            HttpRequest request = buildRequest(uri);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), ForecastApiResponse.class);

    }


    public WeatherApiResponse getWeatherForLocation(LocationEntity location) throws IOException, InterruptedException {
        URI uri = buildUriForWeatherRequest(location);
        HttpRequest request = buildRequest(uri);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);

        return objectMapper.readValue(response.body(), WeatherApiResponse.class);
    }



    private static HttpRequest buildRequest(URI uri) {
        return HttpRequest.newBuilder(uri)
                .GET()
                .build();
    }

    private static URI buildUriForWeatherRequest(LocationEntity location) {
        return URI.create(BASE_API_URL + WEATHER_API_URL_SUFFIX
                + "?q=" + location.getName()
                + "&appid=" + APP_ID
                + "&units=" + "metric");
    }

    private static URI buildUriForForecastRequest(LocationEntity location) {
        return URI.create(BASE_API_URL + FORECAST_API_URL_SUFFIX
                + "?q=" + location.getName()
                + "&appid=" + APP_ID
                + "&units=" + "metric"
                + "&cnt=" + "40"
        );
    }





}

