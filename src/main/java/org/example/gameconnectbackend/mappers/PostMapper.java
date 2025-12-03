package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.PostDTO;
import org.example.gameconnectbackend.models.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPostModel(PostDTO postDTO);
    PostDTO toPostDTO(Post post);

}
