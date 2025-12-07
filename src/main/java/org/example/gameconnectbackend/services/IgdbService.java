package org.example.gameconnectbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


@AllArgsConstructor
@Service

public class IgdbService {
    public HttpResponse<String> getJwtToken() {

        String client_id = "kig0sa98mzm9ztzizen6ocq27zab4l";
        String client_secret = "a1tsuvllzfnzbga4go4z9qlfvlzbzt";
        String grant_type = "client_credentials";

        String form = "client_id=" + client_id +
                "&client_secret=" + client_secret +
                "&grant_type=" + grant_type;

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://id.twitch.tv/oauth2/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(form))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getGames(HttpResponse<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(response.body(), Map.class);

        String form2 =
                "fields name, genres.name;" +
                        "sort total_rating desc;" +
                        "where total_rating != null;" +
                        "limit 100;";


        try (HttpClient client = HttpClient.newHttpClient()) {

            String acces_token = (String) data.get("access_token");
            String token_type = (String) data.get("token_type");
            HttpRequest json = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.igdb.com/v4/games"))
                    .header("Client-ID", "kig0sa98mzm9ztzizen6ocq27zab4l")
                    .header("Authorization", token_type + " " + acces_token)
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(form2))
                    .build();



            response = client.send(json, HttpResponse.BodyHandlers.ofString());

            return Map.of(String.valueOf(response.statusCode()), response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
