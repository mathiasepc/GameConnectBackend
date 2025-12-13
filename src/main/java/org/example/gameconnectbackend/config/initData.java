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
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

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
        Media media4 = new Media();
        media4.setPath("https://www.gameskinny.com/wp-content/uploads/2023/12/wow-classic-sod-hardcore-winter-veil-guide.jpg");
        post2.setMedia(media4);
        post2.setContent("It's about that time of year again, will see you all soon in Iron Forge!");
        post2.setCreatedAt(Instant.now().minus(5, ChronoUnit.HOURS));
        var post3 = new Post();
        Media media2 = new Media();
        media2.setPath("https://arkaden.dk/app/uploads/2025/11/ARC-Raiders-billede-1.jpg");
        post3.setMedia(media2);
        post3.setContent("WHO WANTS TO SHOOT SOME ROBOTS?????!?!??!?!");
        post3.setCreatedAt(Instant.now().minus(1, ChronoUnit.HOURS));
        var post4 = new Post();
        Media media3 = new Media();
        media3.setPath("https://www.gamereactor.dk/media/48/arcraiders_3664883_650x.jpg");
        post4.setMedia(media3);
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

        var testUser2 = new User();
        testUser2.setEmail("email@email3.com");
        testUser2.setUsername("The Grinch2");
        testUser2.setPassword(passwordEncoder.encode("123456"));
        testUser2.setRole(roleAdmin);
        userRepository.save(testUser2);

        var testUser12 = new User();
        testUser12.setEmail("missSanta@email.com");
        testUser12.setUsername("Miss Santa Claus");
        testUser12.setRole(roleUser);
        testUser12.setPassword(passwordEncoder.encode("123456"));
        var profile12 = new Profile();
        testUser12.setProfile(profile12);
        profile12.setUser( testUser12);
        profile12.setImg("https://thehill.com/wp-content/uploads/sites/2/2021/12/ca_mrsclaus_122321_getty.jpg");
        userRepository.save(testUser12);

        // !!! Only for testing purposes when creating our database !!!
        var profile3 = new Profile();
        var testUser3 = new User();
        testUser3.setEmail("email2@email.com");
        testUser3.setUsername("username2");
        testUser3.setRole(roleUser);
        testUser3.setProfile(profile3);
        testUser3.setPassword(passwordEncoder.encode("123456"));
        profile3.setUser(testUser3);
        userRepository.save(testUser3);
        userRepository.delete(testUser3);
        // -----------------------------------------------------------

        // --- Comment Initialization ---
        List<Comment> comments = new ArrayList<>();

// Santa’s Post 1 – "Catch me live!"
        comments.add(new Comment(
                null,
                Instant.now().minus(30, ChronoUnit.MINUTES),
                "Sweetie, maybe tell them what time? Not everyone lives in the North Pole timezone ❤️",
                post1,
                "Miss Santa Claus"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(25, ChronoUnit.MINUTES),
                "Live? So I can watch you miss all your shots in real time? Tempting.",
                post1,
                "The Grinch"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(20, ChronoUnit.MINUTES),
                "Streaming privileges are a gift, Santa. Try not to violate the TOS again.",
                post1,
                "The Grinch2"
        ));


// Santa’s Post 2 – "WHO ATE ALL MY COOKIES?"
        comments.add(new Comment(
                null,
                Instant.now().minus(50, ChronoUnit.MINUTES),
                "It was you. It’s always you. Check your beard crumbs.",
                post2,
                "Miss Santa Claus"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(45, ChronoUnit.MINUTES),
                "I would never steal your cookies. I prefer stealing hopes and dreams.",
                post2,
                "The Grinch"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(40, ChronoUnit.MINUTES),
                "Cookies confiscated for… ‘quality assurance’ purposes.",
                post2,
                "The Grinch2"
        ));


// Santa’s Post 3 – "WHO WANTS TO SHOOT SOME ROBOTS?"
        comments.add(new Comment(
                null,
                Instant.now().minus(15, ChronoUnit.MINUTES),
                "You can shoot robots after you finish the gift-wrapping you promised me.",
                post3,
                "Miss Santa Claus"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(12, ChronoUnit.MINUTES),
                "Robots? Finally, something I can shoot without paperwork.",
                post3,
                "The Grinch"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(10, ChronoUnit.MINUTES),
                "Loading in. If a robot kills you again, I'm recording it.",
                post3,
                "The Grinch2"
        ));


// Santa’s Post 4 – "Last arc marathon before XMAS!"
        comments.add(new Comment(
                null,
                Instant.now().minus(5, ChronoUnit.MINUTES),
                "Count me in 😘 But I'm not carrying you this time.",
                post4,
                "Miss Santa Claus"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(4, ChronoUnit.MINUTES),
                "Marathon? Buddy, you get winded opening a loot box.",
                post4,
                "The Grinch"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(3, ChronoUnit.MINUTES),
                "I'll join if you promise not to yell like a broken smoke alarm this time.",
                post4,
                "The Grinch2"
        ));


// Grinch’s Post 5 – "Grinch Pack now available!"
        comments.add(new Comment(
                null,
                Instant.now().minus(45, ChronoUnit.MINUTES),
                "Downloading immediately. If this doesn't turn my aim green, I'm suing.",
                post5,
                "Santa Claus"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(42, ChronoUnit.MINUTES),
                "I support your creativity, even if your personality is 90% spite.",
                post5,
                "Miss Santa Claus"
        ));
        comments.add(new Comment(
                null,
                Instant.now().minus(40, ChronoUnit.MINUTES),
                "Finally, a mod that reflects your inner ugliness. Approved.",
                post5,
                "The Grinch2"
        ));

// Save all comments
        commentRepository.saveAll(comments);



    }
}
