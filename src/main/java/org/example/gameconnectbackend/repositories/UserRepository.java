package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
