package org.example.gameconnectbackend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gameconnectbackend.models.enums.Role;

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
    private String username;
    private String passwordHash;
    private String email;
    private String bio;
    private String img;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
    @ManyToMany
    @JoinTable(name = "user_favourite_games",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> favouriteGames;
    @OneToMany(mappedBy = "follower")
    private Set<Follower> followers;
    @OneToMany(mappedBy = "follower")
    private Set<Follower> following;


}
