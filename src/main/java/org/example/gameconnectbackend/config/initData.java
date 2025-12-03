package org.example.gameconnectbackend.config;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.models.Role;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class initData implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setName("USER");

        var testUser = new User();
        testUser.setEmail("email@email");
        testUser.setBio("i'm just a test User, Hi!");
        testUser.setUsername("username");
        testUser.setRole(role);
        userRepository.save(testUser);
    }
}
