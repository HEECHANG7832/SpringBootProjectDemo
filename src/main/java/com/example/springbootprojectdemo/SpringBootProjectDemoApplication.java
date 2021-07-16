package com.example.springbootprojectdemo;

import com.example.springbootprojectdemo.ioc.ApplicationContextProvider;
import com.example.springbootprojectdemo.ioc.Base64Encoder;
import com.example.springbootprojectdemo.ioc.Encoder;
import com.example.springbootprojectdemo.ioc.UrlEncoder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringBootProjectDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootProjectDemoApplication.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();
        //Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);

        Encoder encoder = context.getBean("base64Encoder", Encoder.class);
        String url = "www.naver.com/books/it?page=10&size=20&name=spring";

        String result = encoder.encode(url);
    }

}

@Configuration //내부에 Component를 가지고 있다
class AppConfig{

    @Bean("base64Encoder")
    public Encoder encoder(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }
    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }

}