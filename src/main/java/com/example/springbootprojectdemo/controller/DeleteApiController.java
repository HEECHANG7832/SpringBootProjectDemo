package com.example.springbootprojectdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {
    @DeleteMapping("/delete/{userId}")
    public void deleteRequest(@PathVariable String userId, @RequestParam String account){
        System.out.println(userId);
        System.out.println(account);
        //delete도 멱등
        //없는상태에서 삭제했는지 있는상태에서 삭제했는지
    }
}
