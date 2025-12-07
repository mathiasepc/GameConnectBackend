package org.example.gameconnectbackend.services.igdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IgdbGame {
    @JsonProperty("name")
    private String name;
    @JsonProperty("genres")
    private List<IgdbGameCategory> gameCategories;
}
