package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {
    PostDTO createPost(PostDTO request);
    List<TimelinePostDTO> getTimelinePosts(Long userId);
}
