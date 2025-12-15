package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.profileDtos.FollowProfileDTO;
import org.example.gameconnectbackend.interfaces.IFollowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
public class FollowController {

    private final IFollowService followService;

    public FollowController(IFollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{followerId}/follow/{followingId}")
    public void follow(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.follow(followerId, followingId);
    }

    @DeleteMapping("/{followerId}/unfollow/{followingId}")
    public void unfollow(@PathVariable Long followerId, @PathVariable Long followingId) {
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
