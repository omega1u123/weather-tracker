package com.example.weathertracker.controller;

import com.example.weathertracker.service.UserService;
import com.example.weathertracker.service.WeatherService;
import com.example.weathertracker.store.CurrentWeather;
import com.example.weathertracker.store.HourWeather;
import com.example.weathertracker.store.entity.LocationEntity;
import com.example.weathertracker.store.model.api.ForecastApiResponse;
import com.example.weathertracker.store.model.api.WeatherApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final WeatherService weatherService;

    private final UserService userService;

    public MainController(WeatherService weatherService, UserService userService) {
        this.weatherService = weatherService;
        this.userService = userService;
    }

    @GetMapping("/sign-in")
    public String signIn(){
        return "sign-in";
    }

    @GetMapping("/page")
    public String getPage(){
        return "page";
    }

    /*@GetMapping("/weather")
    public String getWeather(Model model) throws IOException {
        model.addAttribute("weather", weatherService.getWeather());
        return "home";
    }*/

    @GetMapping("/weather")
    public CurrentWeather getWeather() throws IOException {
        return weatherService.getCurrentWeather();
    }


    @GetMapping("/hourWeather")
    public HourWeather getHourWeather() throws IOException {

        return  weatherService.getHourWeather();
    }


    @GetMapping("/getForecast")
    public ForecastApiResponse getForecast() throws IOException, InterruptedException {
        LocationEntity loc = new LocationEntity("minsk");
        return weatherService.getForecastForLocation(loc);
    }

    @GetMapping("/getCurrentWeather")
    public WeatherApiResponse getWeatherForLoc() throws IOException, InterruptedException {
        LocationEntity loc = new LocationEntity("minsk");
        System.out.println(weatherService.getWeatherForLocation(loc).getMain());
        return weatherService.getWeatherForLocation(loc);
    }

    @GetMapping("/getHourlyWeather")
    public List<ForecastApiResponse.HourlyForecast> getHourly() throws IOException, InterruptedException {
        LocationEntity loc = new LocationEntity("minsk");
        return weatherService.getHourlyWeather(weatherService.getForecastForLocation(loc).getForecasts(), loc);
    }

    @GetMapping("/getDailyWeather")
    public Map<LocalDate, ForecastApiResponse.HourlyForecast> getTempForWeek() throws IOException, InterruptedException {
        LocationEntity loc = new LocationEntity("minsk");
        System.out.println(weatherService.getDailyWeather(weatherService.getForecastForLocation(loc).getForecasts()));
        return weatherService.getDailyWeather(weatherService.getForecastForLocation(loc).getForecasts());
    }


}
