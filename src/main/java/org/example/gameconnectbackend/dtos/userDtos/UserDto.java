package org.example.gameconnectbackend.dtos.userDtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
}
