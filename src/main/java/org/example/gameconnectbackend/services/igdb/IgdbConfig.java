package org.example.gameconnectbackend.services.igdb;

import lombok.Getter;

import java.net.URI;


@Getter
public class IgdbConfig {
    private final String clientId;
    private final String clientSecret;
    private final URI authUri;
    private final URI gamesUri;

    public IgdbConfig() {
        this.clientId = "kig0sa98mzm9ztzizen6ocq27zab4l";
        this.clientSecret = "a1tsuvllzfnzbga4go4z9qlfvlzbzt";
        this.authUri = URI.create("https://id.twitch.tv/oauth2/token");
        this.gamesUri = URI.create("https://api.igdb.com/v4/games");
    }
}

