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
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "favouriteGames")
    private Set<Profile> profiles;

    private String img;

    @ManyToMany
    @JoinTable(
            name = "game_categories",
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            joinColumns = @JoinColumn(name = "game_id")

    )
    private List<GameCategory> gameCategories;
}
