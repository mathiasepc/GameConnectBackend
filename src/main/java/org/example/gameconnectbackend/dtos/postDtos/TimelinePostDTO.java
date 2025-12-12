package org.example.gameconnectbackend.dtos.postDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gameconnectbackend.models.Media;
import org.example.gameconnectbackend.models.Post;
import org.example.gameconnectbackend.models.Tag;
import org.springframework.core.metrics.StartupStep;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelinePostDTO {
    private Long id;
    private String content;
    private Instant createdAt;
    private Media media;
    private String username;
    private String img;
    private Set<Tag> tags;
    private Long commentsCount;
    private Long userId;

    public TimelinePostDTO(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.media = post.getMedia();
        this.username = post.getUser().getUsername();
        this.img = post.getUser().getProfile().getImg();
        this.tags = post.getTags();
        this.userId = post.getUser().getId();
    }

    public void setCommentCount(long commentCount) {
        this.commentsCount = commentCount;
    }
}

