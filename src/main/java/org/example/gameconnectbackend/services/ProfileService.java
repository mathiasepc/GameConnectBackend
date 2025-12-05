package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.postDtos.PostSummaryDTO;
import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileDTO getProfileDTO(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        Profile profile = user.getProfile();

        List<PostSummaryDTO> posts = user.getPosts()
                .stream()
                .map(p -> new PostSummaryDTO(
                        p.getId(),
                        p.getContent(),
                        p.getCreatedAt(),
                        p.getMedia()
                ))
                .toList();

        return new ProfileDTO(
                user.getUsername(),
                posts,
                profile.getBio(),
                profile.getImg(),
                profile.getFollowers() == null ? 0 : profile.getFollowers().size(),
                profile.getFollowing() == null ? 0 : profile.getFollowing().size()
        );
    }
}
