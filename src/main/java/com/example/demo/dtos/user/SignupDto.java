package com.example.demo.dtos.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class SignupDto {
    @Email(message = "createdBy can not be null")
    @NotNull(message = "createdBy can not be null")
    private String email;

    @NotNull(message = "createdBy can not be null")
    private String password;

    public String getEmail() {
        return email;
    }

    public SignupDto() {

    }

    public SignupDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
