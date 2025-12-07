package org.example.gameconnectbackend.services.igdb;

import org.example.gameconnectbackend.dtos.gameDto.GameDto;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class IgdbClient {
    private final IgdbConfig config;
    private final HttpClient httpClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public IgdbClient() {
        this.config = new IgdbConfig();
        this.httpClient = HttpClient.newHttpClient();
    }

    public TokenResponse fetchAppToken() {
        String form = "client_id=" + config.getClientId()
                + "&client_secret=" + config.getClientSecret()
                + "&grant_type=client_credentials";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(config.getAuthUri())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), TokenResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while getting token", e);
        }
    }

    public List<IgdbGame> postToGamesEndpoint(String queryBody, TokenResponse token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(config.getGamesUri())
                .header("Client-ID", config.getClientId())
                .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(queryBody))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            var raw = mapper.readValue(response.body(), IgdbGame[].class);

            return Arrays.asList(raw);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error calling IGDB games endpoint", e);
        }
    }
}

