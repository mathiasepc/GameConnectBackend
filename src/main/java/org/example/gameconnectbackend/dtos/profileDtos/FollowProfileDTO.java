package org.example.gameconnectbackend.dtos.profileDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowProfileDTO {
    private Long id;
    private String username;
    private String img;
}
