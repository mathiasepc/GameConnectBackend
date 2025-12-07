package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.dtos.postDtos.PostSummaryDTO;
import org.example.gameconnectbackend.interfaces.IPostService;
import org.example.gameconnectbackend.interfaces.ITimelineService;
import org.example.gameconnectbackend.repositories.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    private final IPostService postService;
    private final ITimelineService timelineservice;
    private final PostRepository postRepository;

    public TimeLineController(IPostService postService, ITimelineService timelineservice, PostRepository postRepository) {
        this.postService = postService;
        this.timelineservice = timelineservice;
        this.postRepository = postRepository;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create-post")
    public ResponseEntity<PostDTO> createPost( @RequestBody PostDTO request) {
    var response = postService.createPost(request);
        return ResponseEntity.ok( response);
    }

    @GetMapping("/posts")
    public List<PostSummaryDTO> getPosts() {
        return postService.getAllPosts();
    }
}
