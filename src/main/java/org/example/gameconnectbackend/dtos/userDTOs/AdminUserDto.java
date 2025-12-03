package org.example.gameconnectbackend.dtos.userDTOs;

import lombok.Data;
import org.example.gameconnectbackend.models.enums.Role;

@Data
public class AdminUserDto {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String img;
    private Role role;
}
