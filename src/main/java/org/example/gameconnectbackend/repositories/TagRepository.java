package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface TagRepository extends JpaRepository<Tag,Integer> {
}
