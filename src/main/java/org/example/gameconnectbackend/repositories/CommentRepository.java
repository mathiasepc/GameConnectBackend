package org.example.gameconnectbackend.repositories;


import org.example.gameconnectbackend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    long countByPostId(Long postId);
}
