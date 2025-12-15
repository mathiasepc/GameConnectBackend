package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.profileDtos.FollowProfileDTO;
import org.example.gameconnectbackend.interfaces.IFollowService;
import org.example.gameconnectbackend.models.Follower;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.repositories.FollowRepository;
import org.example.gameconnectbackend.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FollowService implements IFollowService {

    private final FollowRepository followRepository;
    private final ProfileRepository profileRepository;

    public FollowService(FollowRepository followRepository, ProfileRepository profileRepository) {
        this.followRepository = followRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public void follow(long followerId, long followingId) {
        if(followerId == followingId) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        Profile follower = profileRepository.findById(followerId).orElse(null);
        Profile following = profileRepository.findById(followingId).orElse(null);

        boolean alreadyFollowing = followRepository.existsByFollower_IdAndFollowing_Id(follower.getId(), following.getId());
        if(!alreadyFollowing) {
            Follower follow = new Follower();
            follow.setFollower(follower);
            follow.setFollowing(following);
            followRepository.save(follow);
        }
    }

    @Override
    public void unfollow(Long followerId, Long followingId) {
        Profile follower = profileRepository.findById(followerId).orElse(null);
        Profile following = profileRepository.findById(followingId).orElse(null);

        Follower follow = followRepository.findByFollowerAndFollowing(follower, following);

        followRepository.delete(follow);
    }

    @Override
    public List<FollowProfileDTO> getFollowers(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElse(null);
        if (profile == null) return Collections.emptyList();

        return followRepository.findAllByFollowing(profile)
                .stream()
                .map(Follower::getFollower) // gets the Profile of the follower
                .map(followerProfile -> new FollowProfileDTO(
                        followerProfile.getId(),
                        followerProfile.getUser().getUsername(),
                        followerProfile.getImg()
                ))
                .toList();
    }

    @Override
    public List<FollowProfileDTO> getFollowing(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElse(null);
        if (profile == null) return Collections.emptyList();

        return followRepository.findAllByFollower(profile)
                .stream()
                .map(Follower::getFollowing)
                .map(followerProfile -> new FollowProfileDTO(
                        followerProfile.getId(),
                        followerProfile.getUser().getUsername(),
                        followerProfile.getImg()
                ))
                .toList();
    }

}
