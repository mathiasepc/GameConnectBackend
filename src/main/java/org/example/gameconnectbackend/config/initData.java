package org.example.gameconnectbackend.config;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.models.*;
import org.example.gameconnectbackend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class initData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final FollowRepository followRepository;

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

        var newTag = new Tag();
        var newTag2 = new Tag();


        newTag.setName("RPG");

        newTag2.setName("FPS");

        tagRepository.save(newTag);
        tagRepository.save(newTag2);

        //Test User 1
        var profile = new Profile();
        profile.setBio("When I am not giving out toys to all the good little girls and boys, I love to fuck shit up on ARC Raiders.");
        profile.setImg("https://images.pexels.com/photos/716658/pexels-photo-716658.jpeg");

        List<Post> posts = new ArrayList<>();
        var post1 = new Post();
        post1.setContent("Catch me live!");
        post1.setCreatedAt(Instant.now().minus(3, ChronoUnit.HOURS));
        Set<Tag> tags = new HashSet<>();
        tags.add(newTag);
        tags.add(newTag2);
        post1.setTags(tags);
        Media media = new Media();
        media.setPath("https://cdn.mos.cms.futurecdn.net/X4ksjqW5jFwk9fqBQWEvrc-840-80.jpg.webp");
        post1.setMedia(media);
        var post2 = new Post();
        post2.setContent("WHO ATE ALL MY COOKIES?");
        post2.setCreatedAt(Instant.now().minus(5, ChronoUnit.HOURS));
        var post3 = new Post();
        post3.setContent("WHO WANTS TO SHOOT SOME ROBOTS?????!?!??!?!");
        post3.setCreatedAt(Instant.now().minus(1, ChronoUnit.HOURS));
        var post4 = new Post();
        post4.setContent("Last arc marathon before XMAS! WHO WANTS SOME?");
        post4.setCreatedAt(Instant.now().minus(2, ChronoUnit.HOURS));

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);

        var testUser = new User();
        testUser.setEmail("email@email.com");
        testUser.setUsername("Santa Claus");
        testUser.setRole(roleUser);
        testUser.setProfile(profile);
        post1.setUser(testUser);
        post2.setUser(testUser);
        post3.setUser(testUser);
        post4.setUser(testUser);
        testUser.setPassword(passwordEncoder.encode("123456"));
        profile.setUser(testUser);
        testUser.setPosts(posts);
        userRepository.save(testUser);



        //Test User 2
        var profile1 = new Profile();
        profile1.setBio("That's what it's all about, isn't it? Games, games... games, games, games, games, games!");
        profile1.setImg("https://icon2.cleanpng.com/20240303/utg/transparent-grinch-the-grinchs-angry-facial-expression-in-1710854420578.webp");

        List<Post> posts1 = new ArrayList<>();
        var post5 = new Post();
        post5.setContent("For all you counter strikers out there - Grinch Pack from Gamebanana now available!");
        post5.setCreatedAt(Instant.now().minus(2, ChronoUnit.HOURS));
        Media media1 = new Media();
        media1.setPath("https://images.gamebanana.com/img/ss/mods/530-90_584eb1a743852.jpg");
        post5.setMedia(media1);
        posts1.add(post5);

        var testUser1 = new User();
        testUser1.setEmail("email@email2.com");
        testUser1.setUsername("The Grinch");
        testUser1.setPassword(passwordEncoder.encode("123456"));
        testUser1.setRole(roleUser);
        testUser1.setProfile(profile1);
        post5.setUser(testUser1);
        testUser1.setPosts(posts1);
        profile1.setUser(testUser1);
        userRepository.save(testUser1);



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
