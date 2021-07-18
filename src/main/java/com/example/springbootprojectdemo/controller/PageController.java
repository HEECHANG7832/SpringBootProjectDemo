package com.example.springbootprojectdemo.controller;

import com.example.springbootprojectdemo.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main() { //스트링인 경우 페이지를 찾는다
        return "main.html";
    }

}
