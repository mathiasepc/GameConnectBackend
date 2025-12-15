package org.example.gameconnectbackend.dtos.postDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {
    boolean liked;
    Long likeCount;

}
