package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.models.User;

public interface IJwtService {
    String generateJwtToken(User user);
    boolean validateJwtToken(String authToken);
    Long getUserIdFromToken(String token);
    String getRoleFromToken(String token);
}
