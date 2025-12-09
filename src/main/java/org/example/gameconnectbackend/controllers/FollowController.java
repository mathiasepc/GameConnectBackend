package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.postDtos.FollowProfileDTO;
import org.example.gameconnectbackend.services.FollowService;
import org.example.gameconnectbackend.services.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;
    private final ProfileService profileService;

    public FollowController(FollowService followService, ProfileService profileService) {
        this.followService = followService;
        this.profileService = profileService;
    }

    @PostMapping("/{followerId}/follow/{followingId}")
    public void follow(@PathVariable long followerId, @PathVariable long followingId) {
        followService.follow(followerId, followingId);
    }

    @DeleteMapping("/{followerId}/unfollow/{followingId}")
    public void unfollow(@PathVariable long followerId, @PathVariable long followingId) {
        followService.unfollow(followerId, followingId);
    }

    @GetMapping("/{id}/followers")
    public List<FollowProfileDTO> getFollowers(@PathVariable Long id) {
        return followService.getFollowers(id);
    }

    @GetMapping("/{id}/following")
    public List<FollowProfileDTO> getFollowing(@PathVariable Long id) {
        return followService.getFollowing(id);
    }

}
