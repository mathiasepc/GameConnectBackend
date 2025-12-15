package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.commentDtos.CommentDTO;
import org.example.gameconnectbackend.dtos.postDtos.LikeResponseDTO;
import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.interfaces.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @CrossOrigin(origins = "*")
    @PostMapping ("/posts/{postId}/comment")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long postId, @RequestBody CommentDTO request) {
        request.setPostId(postId);
        var response = postService.createComment(request);
        return ResponseEntity.ok( response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getComments(@PathVariable Long postId) {
        var response = postService.getComments(postId);
        return response;
    }

    @PostMapping("/timeline/posts/{postId}/like")
    public LikeResponseDTO toggleLike(@PathVariable Long postId) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "You must be logged in"
            );
        }

        Long userId = Long.parseLong(auth.getName());

        return postService.toggleLike(postId, userId);
    }


}
