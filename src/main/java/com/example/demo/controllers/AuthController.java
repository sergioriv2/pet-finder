package com.example.demo.controllers;

import com.example.demo.dtos.user.SignupDto;
import com.example.demo.models.User;
import com.example.demo.projections.users.UserProjection;
import com.example.demo.repositories.UserRepository;
import com.example.demo.responses.ApiResponse;
import com.example.demo.responses.ResponseBuilder;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> getUsers(){
        ApiResponse<List<UserProjection>> response = ResponseBuilder.buildSuccessResponse();
        List<UserProjection> userList  = userService.getUsers();
        response.setPayload(userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signup(
            @RequestBody
            @Valid
            SignupDto signupDto
    ){
        ApiResponse<?> response = ResponseBuilder.buildCreatedResponse();
        System.out.println(signupDto.toString());
        // String hashedPassword = passwordEncoder.encode(signupDto.getPassword());
        // signupDto.setPassword(hashedPassword);
        // User registeredUser = userService.registerUser(signupDto);
        //response.setPayload(registeredUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
