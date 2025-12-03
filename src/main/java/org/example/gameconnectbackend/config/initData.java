package org.example.gameconnectbackend.config;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.models.Tag;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.models.enums.Role;
import org.example.gameconnectbackend.repositories.PostRepository;
import org.example.gameconnectbackend.repositories.TagRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class initData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {

        var testUser = new User();
        testUser.setEmail("email@email");
        testUser.setBio("i'm just a test User, Hi!");
        testUser.setUsername("username");
        testUser.setRole(Role.USER);
        userRepository.save(testUser);

        var newTag = new Tag();
        var newTag2 = new Tag();


        newTag.setName("RPG");

        newTag2.setName("FPS");

        tagRepository.save(newTag);
        tagRepository.save(newTag2);

    }
}
