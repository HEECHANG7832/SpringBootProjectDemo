package com.example.springbootprojectdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")
    public String Hello(){
        return "get Hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi() {
        return "hi";
    }

    //http://localhost:8080/api/get/path-variable/{name}
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String pathName) {

        System.out.println("PathVariable : " + pathName);

        return pathName;
    }

    //?key=value&key=value
    //http://localhost:8080/api/get/query-param?user=steve&email=steve@gmail.com&age=30



}
