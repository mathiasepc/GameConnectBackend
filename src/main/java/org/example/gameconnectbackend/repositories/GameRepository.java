package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
