package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String name);
}
