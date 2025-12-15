package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;
import org.example.gameconnectbackend.interfaces.IExploreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/explore")
public class ExploreController {

    private final IExploreService exploreService;

    public ExploreController(IExploreService exploreService) {
        this.exploreService = exploreService;
    }

    @GetMapping
    public List<TimelinePostDTO> getExploreFeed(@RequestParam long currentUserId) {
        return exploreService.getExploreFeed(currentUserId);
    }
}
