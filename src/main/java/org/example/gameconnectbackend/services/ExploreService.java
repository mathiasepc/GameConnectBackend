package org.example.gameconnectbackend.services;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.mappers.PostMapper;
import org.example.gameconnectbackend.models.Follower;
import org.example.gameconnectbackend.models.Post;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.FollowRepository;
import org.example.gameconnectbackend.repositories.PostRepository;
import org.example.gameconnectbackend.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ExploreService {

    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final PostMapper postMapper;
    private final ProfileRepository profileRepository;

    public List<TimelinePostDTO> getExploreFeed(long currentUserId) {
        Profile currentUserProfile = profileRepository
                .findById(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Follower> followingRelationships = followRepository.findAllByFollower(currentUserProfile);
        List<Profile> followedProfiles = followingRelationships.stream()
                .map(Follower::getFollowing)
                .toList();


        List<User> followedUsers = followedProfiles.stream()
                .map(Profile::getUser)
                .filter(Objects::nonNull)   // remove nulls
                .toList();

        List<Post> unfollowedPosts;
        if (followedUsers.isEmpty()) {
            unfollowedPosts = postRepository.findAll().stream()
                    .filter(post -> !post.getUser().getId().equals(currentUserId))
                    .filter(post -> post.getMedia() != null && post.getMedia().getPath() != null)
                    .toList();
        } else {
            unfollowedPosts = postRepository.findByUserNotIn(followedUsers).stream()
                    .filter(post -> post.getMedia() != null && post.getMedia().getPath() != null)
                    .toList();
        }

        return unfollowedPosts.stream()
                .map(TimelinePostDTO::new)
                .toList();
    }
}

