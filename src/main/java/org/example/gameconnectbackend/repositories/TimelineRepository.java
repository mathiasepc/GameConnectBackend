package org.example.gameconnectbackend.repositories;


import org.example.gameconnectbackend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface TimelineRepository extends JpaRepository<Post,Integer> {
}
