package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.dtos.PostDTO;
import org.springframework.stereotype.Service;

@Service
public interface IPostService {
    PostDTO createPost(PostDTO request);
}
