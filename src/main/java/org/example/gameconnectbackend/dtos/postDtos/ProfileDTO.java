package org.example.gameconnectbackend.dtos.postDtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gameconnectbackend.dtos.gameDto.GameDto;
import org.example.gameconnectbackend.models.Profile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private String username;
    private List<TimelinePostDTO> posts;
    private Long id;
    private String bio;
    private String img;
    private int followers;
    private int followings;
    boolean followed;
    private List<GameDto> games;

}
