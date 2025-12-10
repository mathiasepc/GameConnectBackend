package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // @Mapping = we map the password from the registerUserRequest to the user
    // It didn't work without the password field
    @Mapping(target="password", source="password")
    User toEntity(RegisterUserRequest registerUserRequest);

    UserDto toDto(User user);

}
