package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.interfaces.IPostService;
import org.example.gameconnectbackend.interfaces.ITimelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    private final IPostService postService;
    private final ITimelineService timelineservice;

    public TimeLineController(IPostService postService, ITimelineService timelineservice) {
        this.postService = postService;
        this.timelineservice = timelineservice;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create-post")
    public ResponseEntity<PostDTO> createPost( @RequestBody PostDTO request) {
    var response = postService.createPost(request);
        return ResponseEntity.ok( response);
    }


}
