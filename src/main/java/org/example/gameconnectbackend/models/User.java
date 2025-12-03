package org.example.gameconnectbackend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, name = "username")
    private String username;
    @Column(name = "password")
    private String passwordHash;
    @Column(name = "email")
    private String email;
    @Column(name = "bio")
    private String bio;
    @Column(name = "img")
    private String img;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "user_favourite_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> favouriteGames;

    @OneToMany(mappedBy = "follower")
    private Set<Follower> followers;

    @OneToMany(mappedBy = "follower")
    private Set<Follower> following;


}
