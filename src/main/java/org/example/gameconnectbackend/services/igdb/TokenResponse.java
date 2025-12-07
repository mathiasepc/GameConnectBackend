package org.example.gameconnectbackend.services.igdb;

import lombok.Getter;

@Getter
public class TokenResponse {
    private String access_token;
    private String token_type;
    private long expires_in;
}


