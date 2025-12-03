package org.example.gameconnectbackend.config;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.Role;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.RoleRepository;
import org.example.gameconnectbackend.repositories.ProfileRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class initData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        var role = new Role();
        role.setName("USER");
        roleRepository.save(role);
        var profile = new Profile();

        var testUser = new User();
        testUser.setEmail("email@email.com");
        testUser.setUsername("username");
        testUser.setRole(role);
        testUser.setProfile(profile);
        profile.setUser(testUser);
        userRepository.save(testUser);

        // !!! Only for testing purposes when creating our database !!!
        var profile2 = new Profile();
        var testUser2 = new User();
        testUser2.setEmail("email2@email.com");
        testUser2.setUsername("username2");
        testUser2.setRole(role);
        testUser2.setProfile(profile2);
        profile2.setUser(testUser2);
        userRepository.save(testUser2);
        userRepository.delete(testUser2);
        // -----------------------------------------------------------



    }
}

