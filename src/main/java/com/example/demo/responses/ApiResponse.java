package com.example.demo.responses;

import java.sql.Array;
import java.util.ArrayList;

public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T payload;
    private ArrayList<String> errors;

    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.errors = new ArrayList<String>();
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
