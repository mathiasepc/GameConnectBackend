package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.igdb.IgdbClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class TestController {
    private final IgdbClient igdbClient;

    public TestController(IgdbClient igdbClient) {
        this.igdbClient = igdbClient;
    }

    @GetMapping
    public ResponseEntity<?> getGames(){
        var result =  igdbClient.postToGamesEndpoint();

        return ResponseEntity.ok().build();
    }
}
