package com.example.springbootprojectdemo.controller;

import com.example.springbootprojectdemo.annotation.Decode;
import com.example.springbootprojectdemo.annotation.Timer;
import com.example.springbootprojectdemo.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //REST API를 처리하는 Controller
@RequestMapping("/api") //RequestMapping URI를 지정
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!";
    }

    //Text
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    //Json
    //req -> object mapper -> object -> method -> object -> object mapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user; //200
    }

    //ResponseEntity
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user); //응답에 대한 내용을 추가해 줄 수 있음
    }

    //AOP
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        System.out.println(user.toString());
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Decode
    @PutMapping("/put")
    public User putDecode(@RequestBody User user) {
        System.out.println(user.toString());
        return user;
    }
}
