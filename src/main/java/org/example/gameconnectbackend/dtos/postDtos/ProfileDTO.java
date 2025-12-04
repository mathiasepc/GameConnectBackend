package org.example.gameconnectbackend.dtos.postDtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private String username;
    private String bio;
    private String img;

}
