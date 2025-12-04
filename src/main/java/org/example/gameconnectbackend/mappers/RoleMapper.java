package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.RoleDto;
import org.example.gameconnectbackend.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);
}
