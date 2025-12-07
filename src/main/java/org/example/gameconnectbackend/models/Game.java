package org.example.gameconnectbackend.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "favouriteGames")
    private Set<Profile> profiles;

    @ManyToMany
    @JoinTable(
            name = "game_categories",
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            joinColumns = @JoinColumn(name = "game_id")

    )
    private List<GameCategory> gameCategories;
}
