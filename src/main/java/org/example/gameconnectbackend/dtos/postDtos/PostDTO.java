package org.example.gameconnectbackend.dtos.postDtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.example.gameconnectbackend.models.*;


import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    @NotNull
    private String content;
    @NotNull
    private Instant createdAt;
    @NotNull
    private Profile profile;
    private Set<Comment> comments;
    private Set<Like> likes;
    private List<Report> reports;
    private Set<Tag> tags;
    private Media media;
}
