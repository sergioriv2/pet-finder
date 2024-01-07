package com.example.demo.services;

import com.example.demo.controllers.responses.LoginResponse;
import com.example.demo.dtos.user.LoginDto;
import com.example.demo.dtos.user.SignupDto;
import com.example.demo.exceptions.auth.AuthException;
import com.example.demo.models.User;
import com.example.demo.projections.users.UserProjection;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public User registerUser(SignupDto payload) {
        User userEmailExist = userRepository.findOneByEmailOrUsername(payload.getEmail(), payload.getUsername());

        if (userEmailExist != null) {
            throw new AuthException("UserAlreadyExist");
        }

        User newUser = new User();
        String hashedPassword = passwordEncoder.encode(payload.getPassword());
        newUser.setEmail(payload.getEmail());
        newUser.setUsername(payload.getUsername());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

        // Set fields to null for response
        newUser.setPassword(null);
        newUser.set_id(null);

        return newUser;
    }

    public LoginResponse generateKeys(LoginDto payload) {
        LoginResponse response = new LoginResponse();

        User user = userRepository.findOneByEmail(payload.getEmail());

        if (user == null) {
            throw new AuthException("InvalidLogin");
        }

        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new AuthException("InvalidLogin");
        }

        Map<String, String> tokensListMap = jwtService.generateTokens(user);

        response.setAccessToken(tokensListMap.get("accessToken"));
        response.setRefreshToken(tokensListMap.get("refreshToken"));

        return response;
    }

    public List<UserProjection> getUsers(){
        return userRepository.findAllBy();
    }
}
