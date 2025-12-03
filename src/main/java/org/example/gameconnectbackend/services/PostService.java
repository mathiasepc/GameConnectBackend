package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.PostDTO;
import org.example.gameconnectbackend.interfaces.IPostService;
import org.example.gameconnectbackend.mappers.PostMapper;
import org.example.gameconnectbackend.models.Post;
import org.example.gameconnectbackend.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    public PostDTO createPost(PostDTO postDTO) {
        var post = postMapper.toPostModel(postDTO);
        var saved = postRepository.save(post);
        postRepository.save(post);
        postDTO.setId(saved.getId());
        return postDTO;

    }
}
