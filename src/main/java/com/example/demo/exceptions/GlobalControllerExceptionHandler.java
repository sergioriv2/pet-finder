package com.example.demo.exceptions;

import com.example.demo.exceptions.auth.AuthException;
import com.example.demo.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad Request");

        Map<String, Object> emptyPayload = new HashMap<>();
        errorResponse.setPayload(emptyPayload);

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

 /*
 *         for (FieldError fieldError : fieldErrors) {
            errorResponse.getErrors().add(
                    fieldError.getField() + " " +
                    fieldError.getDefaultMessage());
        }
 * */

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthException(AuthException ex) {
        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad Request");

        Map<String, Object> emptyPayload = new HashMap<>();
        Map<String, Object> errorsMap = new HashMap<String, Object>();
        Map<String, String> constraintsInnerMap = new HashMap<String, String>();
        errorResponse.setPayload(emptyPayload);

        switch (ex.getMessage()) {
            case "UserAlreadyExist":
                // Status Code
                errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
                // Message
                errorResponse.setMessage("Conflict");
                // Errors
                errorsMap.put("property", "email");
                constraintsInnerMap.put("EMAIL_ALREADY_USED", "This email was already used.");
                errorsMap.put("constraints", constraintsInnerMap);
                errorResponse.getErrors().add(errorsMap);
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
            case "InvalidLogin":
                // Status Code
                errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                // Message
                errorResponse.setMessage("Bad Request");
                // Errors
                errorsMap.put("property", "email | password");
                constraintsInnerMap.put("INVALID_CREDENTIALS", "Check your email or password and try again.");
                errorsMap.put("constraints", constraintsInnerMap);
                errorResponse.getErrors().add(errorsMap);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            default:
                // Status Code
                errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                // Message
                errorResponse.setMessage("Internal Server Error");
                // Errors
                constraintsInnerMap.put("DETAILS", ex.toString());
                errorsMap.put("constraints", constraintsInnerMap);
                errorResponse.getErrors().add(errorsMap);
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
