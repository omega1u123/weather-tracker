package com.example.weathertracker.controller;

import com.example.weathertracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
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
}
