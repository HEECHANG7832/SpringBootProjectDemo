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

    //굳이 page를 내리는 컨트롤러에서 Body를 내리지 말자
    //ResponseEntity
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();
        user.setAddress("1234");
        user.setName("steve");
        return user;
    }
}
