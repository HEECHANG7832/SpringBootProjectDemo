package com.example.springbootprojectdemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.LogRecord;

@Slf4j
//@WebFilter(urlPatterns = "/api/user/*") //특정 url에만 Filter적용
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
        //포인터로 읽는거라서 forEach에서 다 읽어버리면 나중에 spring이 읽을때 무제가 생김
        //그래서 ContentCachingRequestWrapper로 변경
        //Caching에서도 생성하자마자 content를 담는게 아니기 때문에 Filter이후에 읽어야 한다

        chain.doFilter(request, response);

        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("url : {}, line : {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse(); //마찬가지로 한번 읽어버리기 때문에 body내용이 비는 문제를 해결

        log.info("response stauts : {}, responseBody : {}", httpStatus, resContent);

    }
}
