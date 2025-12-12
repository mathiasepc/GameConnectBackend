package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.services.igdb.IgdbClient;
import org.example.gameconnectbackend.services.igdb.IgdbGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/igdb")
public class GamesController {

    private final IgdbClient igdbClient;

    public GamesController(IgdbClient igdbClient) {
        this.igdbClient = igdbClient;
    }

    @GetMapping("games")
    public ResponseEntity<List<IgdbGame>> searchGames(@RequestParam String search) {
        return ResponseEntity.ok(igdbClient.searchGames(search));
    }
}
