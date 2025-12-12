package org.example.gameconnectbackend.services.igdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cover {
    @JsonProperty("image_id")
    private String image_id;
}
