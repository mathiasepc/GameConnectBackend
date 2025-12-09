package org.example.gameconnectbackend.dtos.searchDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDTO {
    private Long userId;
    private Long profileId;
    private String username;
    private String profileImage;
}