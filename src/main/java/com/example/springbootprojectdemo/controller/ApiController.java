package com.example.springbootprojectdemo.controller;

import com.example.springbootprojectdemo.annotation.Decode;
import com.example.springbootprojectdemo.annotation.Timer;
import com.example.springbootprojectdemo.dto.User;
import com.example.springbootprojectdemo.dto.UserResponse;
import com.example.springbootprojectdemo.service.RestTemplateService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Api(tags = {"Api 정보를 제공하는 Controller"})     //swagger
@Slf4j
@RestController //REST API를 처리하는 Controller
@RequestMapping("/api") //RequestMapping URI를 지정
@Validated
public class ApiController {

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!";
    }

    //Text
    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때")    //swagger
    @ApiOperation(value = "사용자의 이름과 나이를 echo하는 메소드")
    @GetMapping("/text")
    public String text(
            @ApiParam(value = "계정", defaultValue = "20")
            @RequestParam String account){
        return account;
    }

    //Json
    //req -> object mapper -> object -> method -> object -> object mapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user; //200
    }

    //ResponseEntity
//    @PutMapping("/put")
//    public ResponseEntity<User> put(@RequestBody User user) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(user); //응답에 대한 내용을 추가해 줄 수 있음
//    }

    /*
    AOP
     */
    @ApiImplicitParams(    //swagger
        {
            @ApiImplicitParam(value = "x 값"),
            @ApiImplicitParam(value = "y 값")
        }
    )
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        System.out.println(name);
        return name;
    }

//    @PostMapping("/post")
//    public User post(@RequestBody User user) {
//        System.out.println(user.toString());
//        return user;
//    }

//    @Timer
//    @DeleteMapping("/delete")
//    public void delete() throws InterruptedException {
//        Thread.sleep(2000);
//    }

//    @Decode
//    @PutMapping("/put")
//    public User putDecode(@RequestBody User user) {
//        System.out.println(user.toString());
//        return user;
//    }

    /*
    Validation
     */
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult) {
        System.out.println(user);

        //에러가난 필드의 메세지 가져오는 방법
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError  field = (FieldError)objectError;
                String message = objectError.getDefaultMessage();
                sb.append(message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        return ResponseEntity.ok(user);
    }

    /*
    Exception
     */
    @GetMapping("")
    public User getE(
            @Size(min = 1)
            @RequestParam String name,

            @NotNull
            @RequestParam Integer age) {
        User user = new User();

        int a = 10 + age; //NullPointerException!!!

        return user;
    }

//    @PostMapping("")
//    public User postE(@Valid @RequestBody User user) {
//        return user;
//    }

    //특정 api에서 직접 처리 가능
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    /*
    filter
     */
//    @PostMapping("")
//    public User user(@RequestBody User user) {
//        log.info("User : {}", user);
//        return user;
//    }

    /*
    RestTemplte
     */
    private final RestTemplateService restTemplateService;

    @GetMapping("/client")
    public UserResponse gethello(){
        return restTemplateService.hello();
    }

    //server side
    //@ReauestHeader("x-authorization") String authorization

    @GetMapping("/naver")
    public String naver(){

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com/")
                .path("v1/search/local.json")
                .queryParam("query", "갈비집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        //header setting
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","AOxdiEjk6M26fgXFclFn")
                .header("X-Naver-Client-Secret","h7Jrq7Cdn8")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }

}
