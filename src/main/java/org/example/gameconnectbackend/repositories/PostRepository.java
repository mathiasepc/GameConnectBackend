package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Post;
import org.example.gameconnectbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserIn(List<User> users);
    List<Post> findByUserNotIn(List<User> users);
}
