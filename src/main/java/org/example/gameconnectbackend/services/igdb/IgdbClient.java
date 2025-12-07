package org.example.gameconnectbackend.services.igdb;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.interfaces.IIgdbClient;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class IgdbClient implements IIgdbClient {
    private final HttpClient httpClient;
    private final ObjectMapper mapper;
    private final IgdbCredentials properties;

    // Collects our Bear token.
    public TokenResponse fetchAppToken() {
        // Reqiured in our http body to retrieve the token
        String form = "client_id=" + properties.getClientId()
                + "&client_secret=" + properties.getClientSecret()
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
            return mapper.readValue(response.body(), TokenResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while getting token", e);
        }
    }

    // Collect games with Categories
    public List<IgdbGame> postToGamesEndpoint(TokenResponse token) {
        // The data we want to collect.
        // Limit is 100, and we can request 8 times pr. sec.
        String form =
                "fields name, genres.name;" +
                        "sort total_rating desc;" +
                        "where total_rating != null;" +
                        "limit 100;";

        // Setup our http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.igdb.com/v4/games"))
                .header("Client-ID", properties.getClientId())
                .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            // Get the response
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Deserializes JSON to IgdbGame[]
            // new ArrayList<> = wraps it in a mutable List.
            return new ArrayList<>(Arrays.asList(mapper.readValue(response.body(), IgdbGame[].class)));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error calling IGDB games endpoint", e);
        }
    }
}

