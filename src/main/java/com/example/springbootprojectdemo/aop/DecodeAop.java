package com.example.springbootprojectdemo.aop;

import com.example.springbootprojectdemo.dto.User;
import com.example.springbootprojectdemo.ioc.Base64Encoder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.springbootprojectdemo.controller..*.*(..))") //controller 하위 전부
    private void cut() {

    }

    @Pointcut("@annotation(com.example.springbootprojectdemo.annotation.Decode)")
    private void enableDecode() {

    }

    @Before("cut() & enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        //joinPoint를 통해서 arg을 가져온다
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof User) {
                User user = User.class.cast(arg);
                String base64Email = user.getAddress();
                String address = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                user.setAddress(address);
            }
        }
    }

    @AfterReturning(value = "cut() & enableDecode()", returning = "returnObj") //return하는 객체 파악 가능
    public void afterReturning(JoinPoint joinPoint, Object returnObj) {
        if (returnObj instanceof User) {
            User user = User.class.cast(returnObj);
            String address = user.getAddress();
            String base64Address = Base64.getEncoder().encodeToString(address.getBytes());
            user.setAddress(base64Address);
        }
    }
}
