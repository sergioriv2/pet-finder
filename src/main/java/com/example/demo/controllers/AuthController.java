package com.example.demo.controllers;

import com.example.demo.dtos.user.SignupDto;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto){
       try {
           String hashedPassword = passwordEncoder.encode(signupDto.getPassword());
           signupDto.setPassword(hashedPassword);
           userService.registerUser(signupDto);
           return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.CREATED);
       } catch (Exception exception) {
           System.out.println(exception.getMessage());
           return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
