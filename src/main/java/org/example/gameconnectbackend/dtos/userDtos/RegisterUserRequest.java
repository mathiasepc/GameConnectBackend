package org.example.gameconnectbackend.dtos.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotNull
    @Size(max = 50, message = "Username must less than 50 characters")
    private String username;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters")
    private String password;
}
