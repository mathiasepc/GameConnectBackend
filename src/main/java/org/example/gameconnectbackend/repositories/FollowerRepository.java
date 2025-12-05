package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
