package org.example.gameconnectbackend.services.igdb;

import org.example.gameconnectbackend.services.igdb.helper.ITokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class IgdbClient {
    private final HttpClient httpClient;
    private final ObjectMapper mapper;
    private final ITokenProvider tokenProvider;

    @Value("${igdb.client-id}")
    private String clientId;

    public IgdbClient(HttpClient httpClient, ObjectMapper mapper, ITokenProvider tokenProvider) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.tokenProvider = tokenProvider;
    }

    // Collect games with Categories
    public List<IgdbGame> postToGamesEndpoint() {
        String token = tokenProvider.getAccessToken();
        // The data we want to collect.
        // Limit is 100, and we can have 8 open request pr. sec.
        String form =
                "fields name, genres.name;" +
                        "sort total_rating desc;" +
                        "where total_rating != null;" +
                        "limit 100;";
        // Setup our http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.igdb.com/v4/games"))
                .header("Client-ID", clientId)
                .header("Authorization", "bearer " + token)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            // Get the response
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Deserializes JSON to IgdbGame[]
            // Convert from [] -> ArrayList<IgdbGame>
            return Arrays.asList(mapper.readValue(response.body(), IgdbGame[].class));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error calling IGDB games endpoint", e);
        }
    }

    //Search for Game
    public List<IgdbGame> searchGames(String query) {
        String token = tokenProvider.getAccessToken();
        String form = "search \"" + query + "\"; fields id, name, cover.image_id; limit 20;";


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.igdb.com/v4/games"))
                .header("Client-ID", clientId)
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapper.readValue(response.body(), IgdbGame[].class));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error searching IGDB games", e);
        }
    }
}

