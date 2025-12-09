package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.postDtos.PostDTO;
import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPostModel(PostDTO postDTO);
    PostDTO toPostDTO(Post post);


    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.profile.img", target = "img")
    TimelinePostDTO toPostSummaryDTO(Post post);

}
