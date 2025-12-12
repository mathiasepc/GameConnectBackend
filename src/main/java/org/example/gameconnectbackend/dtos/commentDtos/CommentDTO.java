package org.example.gameconnectbackend.dtos.commentDtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class CommentDTO {

    private Long id;
    @NotNull
    private String content;
    @NotNull
    private Instant createdAt;

    private Long postId;
    @NotNull
    private String username;
}
