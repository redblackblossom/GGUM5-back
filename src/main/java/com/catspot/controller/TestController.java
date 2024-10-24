package com.catspot.controller;

import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
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

    @GetMapping("/exception1")
    public String test3() {
        throw new RuntimeException("테스트 예외 발생");
    }
    @GetMapping("/exception2")
    public String test4() {
        throw new CustomException(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

}
