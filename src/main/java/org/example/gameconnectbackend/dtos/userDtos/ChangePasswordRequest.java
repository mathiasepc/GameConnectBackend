package org.example.gameconnectbackend.dtos.userDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotNull(message = "Old Password cannot be empty")
    private String oldPassword;
    @NotNull(message = "new Password cannot be empty")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters")
    private String newPassword;
}
