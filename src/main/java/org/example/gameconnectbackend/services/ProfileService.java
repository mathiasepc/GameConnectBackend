package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.postDtos.PostSummaryDTO;
import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;
import org.example.gameconnectbackend.exceptions.UserNotFoundException;
import org.example.gameconnectbackend.mappers.PostMapper;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public ProfileService(UserRepository userRepository, PostMapper postMapper) {
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    public ProfileDTO getProfileDTO(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        Profile profile = user.getProfile();

        List<PostSummaryDTO> posts = user.getPosts()
                .stream()
                .map(postMapper::toPostSummaryDTO)  // <--- use mapper here
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
