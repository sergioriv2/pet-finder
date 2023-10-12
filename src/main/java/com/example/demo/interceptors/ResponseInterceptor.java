package com.example.demo.interceptors;

import com.example.demo.responses.ApiResponse;
import com.example.demo.responses.ResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ResponseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/auth/")) {
            ApiResponse<?> apiResponse = ResponseBuilder.buildSuccessResponse();
            String jsonResponse = new ObjectMapper().writeValueAsString(apiResponse);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            return true;
        }

        return false;
    }
}
