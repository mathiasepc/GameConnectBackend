package org.example.gameconnectbackend.dtos.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Image cannot be empty")
    @Size(max = 300, message = "Image must less than 300 characters")
    private String img;
    @NotNull(message = "Username cannot be empty")
    @Size(max = 50, message = "Username must less than 50 characters")
    private String username;
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "Password cannot be empty")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters")
    private String password;
}
