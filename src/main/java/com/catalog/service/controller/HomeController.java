package com.catalog.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${polar.greeting}")
    private String polarGreeting;

    @GetMapping("/")
    public String getGreeting() {
        return polarGreeting;
    }
}
