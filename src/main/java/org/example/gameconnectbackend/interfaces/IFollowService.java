package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.dtos.profileDtos.FollowProfileDTO;

import java.util.List;

public interface IFollowService {
    void follow(Long followerId, Long followingId);
    void unfollow(Long followerId, Long followingId);
    List<FollowProfileDTO> getFollowers(Long profileId);
    List<FollowProfileDTO> getFollowing(Long profileId);
}
