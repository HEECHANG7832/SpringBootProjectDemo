package com.example.springbootprojectdemo.service;

import com.example.springbootprojectdemo.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    public  UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost")
                .path("api/server/hello")
                .queryParam("name", "steve")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();

        System.out.println((uri.toString()));

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class); //서버 요청을 받아옴
        //ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //JSON은?
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }
}
