package com.example.springbootprojectdemo.controller;

import com.example.springbootprojectdemo.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
//    @GetMapping("/post")
//    public void post(@RequestBody Map<String, Object> requestData) {
//        requestData.forEach((key, value) -> {
//            System.out.println(key);
//            System.out.println(value);
//        });
//    }

    @PostMapping("/post")
    public void postRequest(@RequestBody PostRequestDto postRequestDto) {
        System.out.println(postRequestDto.toString());
    }
}
