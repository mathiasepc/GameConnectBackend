package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// we uses RoleMapper because we need to map the role from the registerUserRequest to the user
@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    // @Mapping = we map the password from the registerUserRequest to the user
    // It didn't work without the password field
    @Mapping(target="password", source="password")
    User toEntity(RegisterUserRequest registerUserRequest);

    // @Mapping = we map the role from the user to the userDto
    @Mapping(target = "role", source = "role")
    UserDto toDto(User user);

}
