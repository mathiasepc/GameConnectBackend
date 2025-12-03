package org.example.gameconnectbackend.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.gameconnectbackend.models.enums.Role;

import java.util.List;
import java.util.Set;

@Getter
@Setter



@Entity
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
    private Role role;
    private List<Post> posts;
    private Set<Game> favouriteGames;
    private Set<Follower> followers;
    private Set<Follower> following;


}
