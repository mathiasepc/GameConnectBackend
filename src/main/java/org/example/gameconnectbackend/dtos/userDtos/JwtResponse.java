package org.example.gameconnectbackend.dtos.userDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

@Getter
public class JwtResponse {
    private String token;
}
