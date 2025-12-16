package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.userDTOs.AdminUserDto;
import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


// we uses RoleMapper because we need to map the role from the registerUserRequest to the user
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserModel(AdminUserDto adminUserDto);
    // @Mapping = we map the password from the registerUserRequest to the user
    // It didn't work without the password field
    @Mapping(target="password", source="password")
    User toEntity(RegisterUserRequest registerUserRequest);

    AdminUserDto toAdminUserDto(User user);
    // @Mapping = we map the role from the user to the userDto
    UserDto toDto(User user);

}
