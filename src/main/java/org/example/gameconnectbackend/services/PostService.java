package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.commentDtos.CommentDTO;
import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.exceptions.PostNotFoundException;
import org.example.gameconnectbackend.exceptions.UserNotFoundException;
import org.example.gameconnectbackend.interfaces.IPostService;
import org.example.gameconnectbackend.mappers.CommentMapper;
import org.example.gameconnectbackend.mappers.PostMapper;
import org.example.gameconnectbackend.models.*;
import org.example.gameconnectbackend.repositories.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final TagRepository tagRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper,
                       TagRepository tagRepository, ProfileRepository profileRepository,
                       UserRepository userRepository, FollowRepository followRepository,
                       CommentRepository commentRepository, CommentMapper commentMapper
    ) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.tagRepository = tagRepository;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
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
    public List<TimelinePostDTO> getTimelinePosts(Long userId) {
        Profile userProfile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Profile> following = followRepository.findAllByFollower(userProfile)
                .stream()
                .map(Follower::getFollowing)
                .toList();

        List<Profile> sources = new ArrayList<>(following);
        sources.add(userProfile);

        List<User> users = sources.stream()
                .map(Profile::getUser)
                .toList();

        return postRepository.findByUserIn(users)
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .map(TimelinePostDTO::new)
                .toList();
    }

    @Override
    public CommentDTO createComment(CommentDTO request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(()
                -> new PostNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(request.getContent());
        comment.setCreatedAt(post.getCreatedAt());
        comment.setUsername(request.getUsername());

        Comment saved = commentRepository.save(comment);

        CommentDTO dto = commentMapper.commentToCommentDTO(saved);
        return dto;
    }

}
