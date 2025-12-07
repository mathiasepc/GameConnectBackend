package org.example.gameconnectbackend.igdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IgdbGameCategory {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
