package com.example.annotationtest.intercepter;

import com.example.annotationtest.annotation.OnceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiutao.Tan
 * @date 2022-06-22
 */
@Component
public class MyInterceptor1 implements HandlerInterceptor {
    int i = 1;
    Map<String, LocalDateTime> map = new HashMap<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isOnceRequest(handler, request)){
            return true;
        }
        response.sendError(HttpStatus.BAD_REQUEST.value(), "您的请求太快了");
        return false;
    }

    private boolean isOnceRequest(Object handler, HttpServletRequest request) {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            OnceRequest annotation = handlerMethod.getMethod().getAnnotation(OnceRequest.class);
            if (annotation == null){
                 annotation = handlerMethod.getMethod().getDeclaredAnnotation(OnceRequest.class);
            }
            if (annotation != null){

/*                i++;
                if (i <= 2){
                    return true;
                }*/
                String key = request.getRemoteHost();
                if (!map.containsKey(key) || map.get(key).getSecond() != LocalDateTime.now().getSecond()){
                    // 请求速度太快了
                    map.put(key, LocalDateTime.now());
                    return true;
                }
                System.out.println("重复");
                return false;
            }
            // 等于null
            return true;
        }
        return true;
    }
}
