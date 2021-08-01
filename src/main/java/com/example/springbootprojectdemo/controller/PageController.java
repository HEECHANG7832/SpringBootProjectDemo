package com.example.springbootprojectdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main() { //스트링인 경우 페이지를 찾는다
        return "main.html";
    }

//    @RequestMapping("/index")
//    public String index() { //스트링인 경우 페이지를 찾는다
//        return "index";
//    }
//
//    @RequestMapping("/post")
//    public String post() { //스트링인 경우 페이지를 찾는다
//        return "post";
//    }
//
//    @RequestMapping("/tables")
//    public String tables() { //스트링인 경우 페이지를 찾는다
//        return "tables";
//    }
//
//    @RequestMapping("/timer")
//    public String timer() { //스트링인 경우 페이지를 찾는다
//        return "timer";
//    }


}
