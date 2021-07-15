package com.example.springbootprojectdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //REST API를 처리하는 Controller
@RequestMapping("/api") //RequestMapping URI를 지정
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!";
    }
}
