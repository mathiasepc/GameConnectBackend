package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.interfaces.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    private final IPostService postService;

    public TimeLineController(IPostService postService) {
        this.postService = postService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create-post")
    public ResponseEntity<PostDTO> createPost( @RequestBody PostDTO request) {
    var response = postService.createPost(request);
        return ResponseEntity.ok( response);
    }

    @GetMapping("/posts/{userId}")
    public List<TimelinePostDTO> getTimeline(@PathVariable Long userId) {
        return postService.getTimelinePosts(userId);
    }
}
