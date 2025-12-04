package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
