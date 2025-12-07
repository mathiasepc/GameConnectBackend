package org.example.gameconnectbackend.dtos.postDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gameconnectbackend.models.Media;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSummaryDTO {
    private Long id;
    private String content;
    private Instant createdAt;
    private Media media;
    private String username;
    private String img;

}

