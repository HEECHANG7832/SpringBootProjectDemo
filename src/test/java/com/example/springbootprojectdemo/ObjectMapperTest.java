package com.example.springbootprojectdemo;

import com.example.springbootprojectdemo.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObjectMapperTest {

    @Test
    void contextLoads(){
        System.out.println("hello");

        //ObjectMapper
        //text Json -> object
        //object -> text Json
        var objectMapper = new ObjectMapper();
        //object -> Json
        var user = new User("steve", 10, "1234", "1234");
        try{
            var text = objectMapper.writeValueAsString(user); //getter 필요 //커스텀 메서드에 get을 붙이면 안된다!!!
            System.out.println(text);
            var objectUser = objectMapper.readValue(text, User.class); //default 생성자 사용
            System.out.println(objectUser.toString());
        }catch(Exception e){
        }
    }
}
