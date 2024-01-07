package com.example.demo.controllers;

import com.example.demo.controllers.responses.LoginResponse;
import com.example.demo.dtos.user.LoginDto;
import com.example.demo.dtos.user.SignupDto;
import com.example.demo.models.User;
import com.example.demo.projections.users.UserProjection;
import com.example.demo.repositories.UserRepository;
import com.example.demo.responses.ApiResponse;
import com.example.demo.responses.ResponseBuilder;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> getUsers(){
        ApiResponse<List<UserProjection>> response = ResponseBuilder.buildSuccessResponse();
        List<UserProjection> userList  = userService.getUsers();
        response.setPayload(userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> signup(
            @Valid
            @RequestBody
            final SignupDto payload
    ){
        ApiResponse<User> response = ResponseBuilder.buildCreatedResponse();

        User registeredUser = userService.registerUser(payload);
        response.setPayload(registeredUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid
            @RequestBody
            final LoginDto payload
    ){
        ApiResponse<LoginResponse> response = ResponseBuilder.buildSuccessResponse();

        LoginResponse generatedKeys = userService.generateKeys(payload);
        response.setPayload(generatedKeys);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
