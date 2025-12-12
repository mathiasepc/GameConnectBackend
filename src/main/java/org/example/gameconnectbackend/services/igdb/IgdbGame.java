package org.example.gameconnectbackend.services.igdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IgdbGame {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cover")
    private Cover cover;

    // Computed field – no need to store a separate one
    @JsonProperty("coverUrl")
    public String getCoverUrl() {
        if (cover == null || cover.getImage_id()== null) {
            return "/images/default-game.png";
        }
        return "https://images.igdb.com/igdb/image/upload/t_cover_big/"
                + cover.getImage_id() + ".jpg";
    }
}
