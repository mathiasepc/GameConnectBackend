package org.example.gameconnectbackend.services.igdb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// Dependency injection is allowed
@Component
// We collect the value from application.properties
@ConfigurationProperties(prefix = "igdb")
public class IgdbCredentials {
    private String clientId;
    private String clientSecret;
}

