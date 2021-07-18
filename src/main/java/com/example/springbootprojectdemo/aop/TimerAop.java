package com.example.springbootprojectdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component //@Bean은 클래스를 붙일 수 없다
public class TimerAop {
    @Pointcut("execution(* com.example.springbootprojectdemo.controller..*.*(..))") //controller 하위 전부
    private void cut() {

    }

    @Pointcut("@annotation(com.example.springbootprojectdemo.annotation.Timer)")
    private void enableTimer() {

    }

    @Around("cut() & enableTimer()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = proceedingJoinPoint.proceed();
        System.out.println(result);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());

        return result;

    }
}
