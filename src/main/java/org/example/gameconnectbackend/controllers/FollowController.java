package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.services.FollowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{followerId}/follow/{followingId}")
    public void follow(@PathVariable long followerId, @PathVariable long followingId) {
        followService.follow(followerId, followingId);
    }

    @DeleteMapping("/{followerId}/unfollow/{followingId}")
    public void unfollow(@PathVariable long followerId, @PathVariable long followingId) {
        followService.unfollow(followerId, followingId);
    }

}
