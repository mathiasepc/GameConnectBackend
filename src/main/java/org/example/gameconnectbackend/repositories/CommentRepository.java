package org.example.gameconnectbackend.repositories;


import org.example.gameconnectbackend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
