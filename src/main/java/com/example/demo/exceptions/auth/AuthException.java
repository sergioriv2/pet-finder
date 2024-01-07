package com.example.demo.exceptions.auth;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
