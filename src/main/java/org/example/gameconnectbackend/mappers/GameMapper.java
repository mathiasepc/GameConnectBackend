package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.gameDto.GameDto;
import org.example.gameconnectbackend.models.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "img", target = "img")
    GameDto toGameDto(Game game);
}
