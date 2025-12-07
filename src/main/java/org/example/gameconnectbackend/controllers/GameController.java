package org.example.gameconnectbackend.controllers;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.services.IgdbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@AllArgsConstructor

@RestController
@RequestMapping("/game")
public class GameController {
    private final IgdbService igdbService;

    @GetMapping
    public ResponseEntity<?> checkHttpStatus() {
            igdbService.getGames();

            return ResponseEntity.ok("");

    }
}
