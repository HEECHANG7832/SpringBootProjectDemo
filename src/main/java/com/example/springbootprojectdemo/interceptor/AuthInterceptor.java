package com.example.springbootprojectdemo.interceptor;

import com.example.springbootprojectdemo.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        if (hasAnnotation)  { //권한 체크
            String query = uri.getQuery();
            log.info("query : {}", query);
            if (query.equals("name=steve")) {
                return true;
            }
            return false; //throw AuthException(); 예외를 던져도 된다 별도 handler에서 처리
        }

        return true; //true가 되어야 interceptor에서 다음으로 넘어간다
    }

    private boolean checkAnnotation(Object handler, Class clazz) {
        //resource
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            return false;
        }

        return false;
    }
}
