package org.example.gameconnectbackend.services;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.services.igdb.IgdbClient;
import org.example.gameconnectbackend.services.igdb.IgdbGame;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class IgdbService {
    private final IgdbClient igdbClient = new IgdbClient();

    public void getGames() {
        String form =
                "fields name, genres.name;" +
                        "sort total_rating desc;" +
                        "where total_rating != null;" +
                        "limit 100;";

        var response = igdbClient.fetchAppToken();

        // LinkedHashMap
        List<IgdbGame> games = igdbClient.postToGamesEndpoint(form, response);

        System.out.println(games);
    }
}
