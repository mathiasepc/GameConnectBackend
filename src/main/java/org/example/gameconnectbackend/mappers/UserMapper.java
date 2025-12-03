package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.userDTOs.AdminUserDto;
import org.example.gameconnectbackend.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserModel(AdminUserDto adminUserDto);

    AdminUserDto toAdminUserDto(User user);

}
