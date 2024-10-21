package com.catspot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/test")
    public String test() {
        return "hello catspot!";
    }

    @GetMapping("/test2")
    public String test2() {
        return appName;
    }
}
