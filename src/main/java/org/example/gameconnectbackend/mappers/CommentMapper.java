package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.commentDtos.CommentDTO;
import org.example.gameconnectbackend.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "username", target ="username")
    CommentDTO commentToCommentDTO(Comment comment);
    @Mapping(source = "postId", target = "post.id")
    Comment commentDTOToComment(CommentDTO commentDTO);



}
