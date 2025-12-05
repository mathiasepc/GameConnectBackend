package org.example.gameconnectbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Profile follower;
    @ManyToOne
    @JoinColumn(name = "following_id")
    private Profile following;

}
