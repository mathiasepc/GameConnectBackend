package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
}
