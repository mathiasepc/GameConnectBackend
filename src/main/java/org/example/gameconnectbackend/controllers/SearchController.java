package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.searchDtos.SearchResultDTO;
import org.example.gameconnectbackend.services.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/searchProfiles/{searchWord}")
    public ResponseEntity<List<SearchResultDTO>> searchProfiles(@PathVariable String searchWord) {
        List<SearchResultDTO> searchResultDTOS = searchService.searchForProfiles(searchWord);
        return ResponseEntity.ok(searchResultDTOS);
    }




}
