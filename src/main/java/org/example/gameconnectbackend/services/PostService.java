package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.commentDtos.CommentDTO;
import org.example.gameconnectbackend.dtos.postDtos.LikeResponseDTO;
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
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository, PostMapper postMapper,
                       TagRepository tagRepository, ProfileRepository profileRepository,
                       UserRepository userRepository, FollowRepository followRepository,
                       CommentRepository commentRepository, CommentMapper commentMapper,
                       LikeRepository likeRepository
    ) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.tagRepository = tagRepository;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.likeRepository = likeRepository;
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
        Profile userProfile = profileRepository.findByUserId(userId)
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
                .map(post -> {
                    TimelinePostDTO dto = new TimelinePostDTO(post);
                    dto.setCommentCount(
                            commentRepository.countByPostId(post.getId())
                    );
                    dto.setLikesCount(
                            likeRepository.countByPostId(post.getId())
                    );
                    dto.setLikedByMe(
                            likeRepository.existsByUserIdAndPostId(userId, post.getId())
                    );
                    return dto;
                })
                .toList();

    }

    @Override
    public CommentDTO createComment(CommentDTO request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(request.getContent());
        comment.setCreatedAt(request.getCreatedAt());
        comment.setUsername(user.getUsername());

        Comment saved = commentRepository.save(comment);

        CommentDTO dto = commentMapper.commentToCommentDTO(saved);
        dto.setUserId(user.getId());

        return dto;
    }

    @Override
    public List<CommentDTO> getComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOs.add(commentMapper.commentToCommentDTO(comment));
        }
        return commentDTOs;
    }

    @Override
    public LikeResponseDTO toggleLike(Long postId, Long userId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Optional<Like> existing =
                likeRepository.findByUserIdAndPostId(userId, postId);

        boolean liked;

        if (existing.isPresent()) {
            likeRepository.delete(existing.get());
            liked = false;
        } else {
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(like);
            liked = true;
        }

        long count = likeRepository.countByPostId(postId);

        return new LikeResponseDTO(liked, count);
    }




}
