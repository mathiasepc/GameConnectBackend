package org.example.gameconnectbackend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game_categories")
public class GameCategory {
    @Id
    private Long id;
    @Column(name= "name")
    private String name;

    @ManyToMany(mappedBy = "gameCategories")
    private List<Game> games;
}
