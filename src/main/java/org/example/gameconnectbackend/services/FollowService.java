package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.models.Follower;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.repositories.FollowerRepository;
import org.example.gameconnectbackend.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowerRepository followerRepository;
    private final ProfileRepository profileRepository;

    public FollowService(FollowerRepository followRepository, ProfileRepository profileRepository) {
        this.followerRepository = followRepository;
        this.profileRepository = profileRepository;
    }

    public void follow(long followerId, long followingId) {
        if(followerId == followingId) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        Profile follower = profileRepository.findById(followerId).orElse(null);
        Profile following = profileRepository.findById(followingId).orElse(null);

        boolean alreadyFollowing = followerRepository.existsByFollower_IdAndFollowing_Id(follower.getId(), following.getId());
        if(!alreadyFollowing) {
            Follower follow = new Follower();
            follow.setFollower(follower);
            follow.setFollowing(following);
            followerRepository.save(follow);
        }
    }

    public void unfollow(Long followerId, Long followingId) {
        Profile follower = profileRepository.findById(followerId).orElse(null);
        Profile following = profileRepository.findById(followingId).orElse(null);

        Follower follow = followerRepository.findByFollowerAndFollowing(follower, following);

        followerRepository.delete(follow);
    }
}
