package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.dtos.postDtos.PostSummaryDTO;
import org.example.gameconnectbackend.exceptions.ProfileNotFoundException;
import org.example.gameconnectbackend.exceptions.UserNotFoundException;
import org.example.gameconnectbackend.interfaces.IPostService;
import org.example.gameconnectbackend.mappers.PostMapper;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.Tag;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.PostRepository;
import org.example.gameconnectbackend.repositories.ProfileRepository;
import org.example.gameconnectbackend.repositories.TagRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final TagRepository tagRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, PostMapper postMapper, TagRepository tagRepository, ProfileRepository profileRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.tagRepository = tagRepository;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }


    @Override
    public PostDTO createPost(PostDTO postDTO) {
        var post = postMapper.toPostModel(postDTO);


        User user = userRepository.findById(postDTO.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException(postDTO.getUser().getId().toString()));
        post.setUser(user);



        Set<Tag> resolvedTags = new HashSet<>();

        if (postDTO.getTags() != null) {
            for (Tag incoming : postDTO.getTags()) {
                Tag existing = tagRepository.findByName(incoming.getName())
                        .orElseGet(() -> tagRepository.save(new Tag(null, incoming.getName(), null)));

                resolvedTags.add(existing);
            }
        }

        post.setTags(resolvedTags);

        var saved = postRepository.save(post);
        postRepository.save(post);
        postDTO.setId(saved.getId());
        return postDTO;
    }

    @Override
    public List<PostSummaryDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toPostSummaryDTO)
                .sorted(Comparator.comparing(PostSummaryDTO::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
