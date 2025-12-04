package org.example.gameconnectbackend.config;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.Role;
import org.example.gameconnectbackend.models.Tag;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.RoleRepository;
import org.example.gameconnectbackend.repositories.PostRepository;
import org.example.gameconnectbackend.repositories.TagRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class initData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Configure Roles
        var roleUser = new Role();
        roleUser.setName("USER");
        roleRepository.save(roleUser);
        var roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        roleRepository.save(roleAdmin);
        //------------------------------

        var profile = new Profile();
        profile.setBio("When I am not giving out toys to all the good little girls and boys, I love to fuck shit up on ARC Raiders");
        profile.setImg("https://images.pexels.com/photos/716658/pexels-photo-716658.jpeg");

        var testUser = new User();
        testUser.setEmail("email@email.com");
        testUser.setUsername("Santa Claus");
        testUser.setUsername("username");
        testUser.setRole(roleUser);
        testUser.setProfile(profile);
        testUser.setPassword(passwordEncoder.encode("123456"));
        profile.setUser(testUser);
        userRepository.save(testUser);

        //Test User 2
        var profile1 = new Profile();
        profile1.setBio("That's what it's all about, isn't it? Games, games... games, games, games, games, games!");
        profile1.setImg("https://icon2.cleanpng.com/20240303/utg/transparent-grinch-the-grinchs-angry-facial-expression-in-1710854420578.webp");


        var testUser1 = new User();
        testUser1.setEmail("email@email.com");
        testUser1.setUsername("The Grinch");
        testUser1.setRole(roleUser);
        testUser1.setProfile(profile1);
        profile1.setUser(testUser1);
        userRepository.save(testUser1);



        var newTag = new Tag();
        var newTag2 = new Tag();


        newTag.setName("RPG");

        newTag2.setName("FPS");

        tagRepository.save(newTag);
        tagRepository.save(newTag2);


        // !!! Only for testing purposes when creating our database !!!
        var profile2 = new Profile();
        var testUser2 = new User();
        testUser2.setEmail("email2@email.com");
        testUser2.setUsername("username2");
        testUser2.setRole(roleUser);
        testUser2.setProfile(profile2);
        testUser2.setPassword(passwordEncoder.encode("123456"));
        profile2.setUser(testUser2);
        userRepository.save(testUser2);
        userRepository.delete(testUser2);
        // -----------------------------------------------------------



    }
}
