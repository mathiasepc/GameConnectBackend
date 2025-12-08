package org.example.gameconnectbackend.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value( "${jwt.secret}")
    private String secret;
    // 1 day
    final long EXPIRATION_TIME = 86_400;

    // lookup here:
    // https://www.jwt.io/
    public String generateJwtToken(String email) {
        return Jwts.builder()
                // is  "sub": "1234567890" in a jwt token
                .subject(email)
                .issuedAt(new Date())
                // We multiply by 1000 because the expiration time is in seconds
                .expiration(new Date(System.currentTimeMillis() + 1000 * EXPIRATION_TIME))
                // we use a secret key to sign the token
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                // returns the token as a string
                .compact();

    }
}
