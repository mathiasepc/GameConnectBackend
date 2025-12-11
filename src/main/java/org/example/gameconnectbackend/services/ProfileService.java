package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.gameDto.GameDto;
import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;
import org.example.gameconnectbackend.exceptions.UserNotFoundException;
import org.example.gameconnectbackend.mappers.GameMapper;
import org.example.gameconnectbackend.mappers.PostMapper;
import org.example.gameconnectbackend.mappers.ProfileMapper;
import org.example.gameconnectbackend.models.Game;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.FollowRepository;
import org.example.gameconnectbackend.repositories.GameRepository;
import org.example.gameconnectbackend.repositories.ProfileRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostMapper postMapper;
    private final GameMapper gameMapper;
    private final GameRepository gameRepository;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(UserRepository userRepository, PostMapper postMapper, FollowRepository followRepository, GameMapper gameMapper, GameRepository gameRepository, ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.userRepository = userRepository;
        this.postMapper = postMapper;
        this.followRepository = followRepository;
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    public ProfileDTO getProfileDTO(long id, long currentUserId) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        Profile profile = user.getProfile();

        List<TimelinePostDTO> posts = user.getPosts()
                .stream()
                .map(postMapper::toPostSummaryDTO)
                .toList();

        List<GameDto> games = profile.getFavouriteGames()
                .stream()
                .map(gameMapper::toGameDto)
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
                followed,
                games
        );
    }

    public ProfileDTO addGameToProfile(GameDto gameDto, Long currentUserId) {
        Profile profile = profileRepository.findById(currentUserId).orElse(null);

        Game game = gameRepository.findById(gameDto.getId()).orElse(null);
        if (game == null) {
            game = new Game();
            game.setId(gameDto.getId());
            game.setImg(gameDto.getImg());
            game.setName(gameDto.getName());
            gameRepository.save(game);
        }


        if(!profile.getFavouriteGames().contains(game)) {
            profile.getFavouriteGames().add(game);
            profileRepository.save(profile);
        }


        return profileMapper.toDto(profile);
    }
}
