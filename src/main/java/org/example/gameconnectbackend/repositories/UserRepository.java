package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface UserRepository extends JpaRepository<User,Integer> {
}
