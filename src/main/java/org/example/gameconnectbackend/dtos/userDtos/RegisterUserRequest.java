package org.example.gameconnectbackend.dtos.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotNull(message = "Username cannot be empty")
    @Size(max = 50, message = "Username must less than 50 characters")
    private String username;
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "Password cannot be empty")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters")
    private String password;
    @NotNull(message = "Picture cannot be empty")
    private String img;
    private String gameImg;
    private String gameName;
    private Long gameId;
}
