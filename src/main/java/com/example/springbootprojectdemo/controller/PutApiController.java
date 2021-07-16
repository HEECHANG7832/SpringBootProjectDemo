package com.example.springbootprojectdemo.controller;

import com.example.springbootprojectdemo.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put/{userId}")
    public PutRequestDto putRequest(@RequestBody PutRequestDto putRequestDto, @PathVariable Long userId) {
        System.out.println(putRequestDto);

        return putRequestDto; //알아서 JSON으로 만들어줌
    }
}
