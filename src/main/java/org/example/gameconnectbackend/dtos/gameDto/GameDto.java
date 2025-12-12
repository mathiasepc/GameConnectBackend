package org.example.gameconnectbackend.dtos.gameDto;

import lombok.Data;

import java.util.List;

@Data
public class GameDto {
    private Long id;
    private String name;
    private String img;
    private List<GameCategoryDto> games;
}
