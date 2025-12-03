package org.example.gameconnectbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateReported;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile reporter;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
