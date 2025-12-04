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
    private List<Post> posts;
    private String bio;
    private String img;
    private Set<Follower> followers;
    private Set<Follower> followings;

}
