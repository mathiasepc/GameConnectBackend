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
    @Column(name = "bio", length = 300)
    private String bio;
    @Column(name = "img", length = 300)
    private String img;

    @ManyToMany
    @JoinTable(
            name = "user_favourite_games",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> favouriteGames;

    @OneToMany(mappedBy = "following")
    private Set<Follower> followers;

    @OneToMany(mappedBy = "follower")
    private Set<Follower> following;

    @MapsId @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
}
