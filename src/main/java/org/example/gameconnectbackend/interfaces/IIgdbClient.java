package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.services.igdb.IgdbGame;
import org.example.gameconnectbackend.services.igdb.TokenResponse;

import java.util.List;

public interface IIgdbClient {
    TokenResponse fetchAppToken();
    List<IgdbGame> postToGamesEndpoint(TokenResponse token);
}
