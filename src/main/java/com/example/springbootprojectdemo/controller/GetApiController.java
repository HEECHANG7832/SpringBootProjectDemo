package com.example.springbootprojectdemo.controller;

import com.example.springbootprojectdemo.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @GetMapping(path = "/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();
        //queryParam.get('name');
        queryParam.entrySet().forEach(entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            sb.append(entry.getKey() + " = " + entry.getValue());
        });

        return sb.toString();
    }

    @GetMapping("/query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        return name + email + age;
    }


    //가장 추천
    @GetMapping("/query-param03")
    //여기 객체가 들어오면 ? 뒤에 오는 값을 판단해서 객체 변수 이름 매칭을 해준다
    public String queryParam03(UserRequest userRequest){
        return userRequest.toString();
    }
}
