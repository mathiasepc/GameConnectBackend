package org.example.gameconnectbackend.services.igdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IgdbGameCategory {
    @JsonProperty("name")
    private String name;
}
