package org.example.gameconnectbackend.services;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.interfaces.IIgdbClient;
import org.example.gameconnectbackend.services.igdb.IgdbGame;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class IgdbService {
    private final IIgdbClient igdbClient;

    public void getGames() {

        var response = igdbClient.fetchAppToken();

        // LinkedHashMap
        List<IgdbGame> games = igdbClient.postToGamesEndpoint(response);

        System.out.println(games);
    }
}
