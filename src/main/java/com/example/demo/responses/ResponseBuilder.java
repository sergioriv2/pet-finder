package com.example.demo.responses;

import org.springframework.http.HttpStatus;

public class ResponseBuilder {
    public static <T> ApiResponse<T> buildSuccessResponse() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Ok");
    }

    public static <T> ApiResponse<T> buildCreatedResponse() {
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Created");
    }
}
