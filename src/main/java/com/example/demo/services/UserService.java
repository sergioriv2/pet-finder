package com.example.demo.services;

import com.example.demo.dtos.user.SignupDto;
import com.example.demo.models.User;
import com.example.demo.projections.users.UserProjection;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(SignupDto signupDto) {
        User newUser = new User();
        newUser.setEmail(signupDto.getEmail());
        newUser.setPassword(signupDto.getPassword());
        newUser.setUsername("Hello");
        userRepository.save(newUser);

        newUser.setPassword(null);
        newUser.set_id(null);

        System.out.println(newUser.toString());
        return newUser;
    }

    public List<UserProjection> getUsers(){
        return userRepository.findAllBy();
    }
}
