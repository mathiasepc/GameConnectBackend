package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;
import org.example.gameconnectbackend.mappers.PostMapper;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.FollowRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostMapper postMapper;

    public ProfileService(UserRepository userRepository, PostMapper postMapper, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.postMapper = postMapper;
        this.followRepository = followRepository;
    }

    public ProfileDTO getProfileDTO(long id, long currentUserId) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        Profile profile = user.getProfile();

        List<TimelinePostDTO> posts = user.getPosts()
                .stream()
                .map(postMapper::toPostSummaryDTO)  // <--- use mapper here
                .toList();

        boolean followed = followRepository.existsByFollower_IdAndFollowing_Id(currentUserId, profile.getId());

        return new ProfileDTO(
                user.getUsername(),
                posts,
                profile.getId(),
                profile.getBio(),
                profile.getImg(),
                profile.getFollowers() == null ? 0 : profile.getFollowers().size(),
                profile.getFollowing() == null ? 0 : profile.getFollowing().size(),
                followed

        );
    }
}
