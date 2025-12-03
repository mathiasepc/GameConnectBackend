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
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bio")
    private String bio;
    @Column(name = "img")
    private String img;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "user_favourite_games",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> favouriteGames;

    @OneToMany(mappedBy = "follower")
    private Set<Follower> followers;

    @OneToMany(mappedBy = "follower")
    private Set<Follower> following;

    @MapsId @OneToOne
    @JoinColumn(name = "id")
    private User user;


}
