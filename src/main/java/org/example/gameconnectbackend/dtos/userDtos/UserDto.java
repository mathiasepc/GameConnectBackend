package org.example.gameconnectbackend.dtos.userDtos;

import lombok.Data;
import org.example.gameconnectbackend.dtos.RoleDto;

@Data
public class UserDto {
    private Long id;
    private RoleDto role;
}
