package com.example.annotationtest.controller;

import com.example.annotationtest.annotation.OnceRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiutao.Tan
 * @date 2022-05-29
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @OnceRequest
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
