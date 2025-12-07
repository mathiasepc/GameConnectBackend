package org.example.gameconnectbackend.igdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

@Component
public class TokenProvider implements ITokenProvider {
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    @Value("${igdb.client-id}")
    private String clientId;
    @Value("${igdb.client-secret}")
    private String clientSecret;

    private String accessToken;
    private Instant expiresAt;

    public TokenProvider(HttpClient httpClient,
                         ObjectMapper mapper) {
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    // Collects our Bear token.
    private void fetchToken() {
        // Reqiured in our http body to retrieve the token
        String form = "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&grant_type=client_credentials";

        // Set up the request.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://id.twitch.tv/oauth2/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            // Get the response
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Map from Json to TokenResponse.
            var token = mapper.readValue(response.body(), TokenResponse.class);
            this.accessToken = token.getAccessToken();
            this.expiresAt = Instant.now().plusSeconds(token.getExpiresIn());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while getting token", e);
        }
    }

    @Override
    public String getAccessToken() {
        if(expiresAt == null) fetchToken();

        return accessToken;
    }
}
