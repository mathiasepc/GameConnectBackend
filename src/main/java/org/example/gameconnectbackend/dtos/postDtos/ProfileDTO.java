package org.example.gameconnectbackend.dtos.postDtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gameconnectbackend.models.Follower;
import org.example.gameconnectbackend.models.Post;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private String username;
    private List<PostSummaryDTO> posts;
    private String bio;
    private String img;
    private int followers;
    private int followings;
}
