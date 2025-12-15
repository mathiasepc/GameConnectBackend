package org.example.gameconnectbackend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.gameconnectbackend.interfaces.IJwtService;
import org.example.gameconnectbackend.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

// This class is responsible for generating, validating JWT tokens
// and get information from the token payload

@Service
public class JwtService implements IJwtService {
    @Value("${jwt.secret}")
    private String secret;
    // 1 day
    final long EXPIRATION_TIME = 86_400;

    // !!! Lookup here about JWT Tokens !!!
    // lookup here:
    // https://www.jwt.io/

    // We generate our token here
    @Override
    public String generateJwtToken(User user) {
        return Jwts.builder()
                // subject "sub": "" in a jwt token
                // "sub" is the subject of the token. what token does this belong to.
                .subject(user.getId().toString())
                .claim("role", user.getRole().getName())
                .issuedAt(new Date())
                // We multiply by 1000 because the expiration time is in seconds
                .expiration(new Date(System.currentTimeMillis() + 1000 * EXPIRATION_TIME))
                // we use a secret key to sign the token
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                // returns the token as a string
                .compact();

    }

    // We validate our token here
    @Override
    public boolean validateJwtToken(String authToken) {
        try {
            var claims = getClaims(authToken);

            // If the expiration time is after now, the token is valid
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    // It's what is stored in the tokens payload
    private Claims getClaims(String authToken) {
        // Uses Jwts library to verify the token
        return Jwts.parser()
                // What encryption key we use
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                // Here we parse the token
                .parseSignedClaims(authToken)
                // Payload is the content of the token
                .getPayload();
    }

    @Override
    public Long getUserIdFromToken(String token){
        return  Long.valueOf(getClaims(token).getSubject());

    }

    @Override
    public String getRoleFromToken(String token){
        return getClaims(token).get("role", String.class);
    }
}
