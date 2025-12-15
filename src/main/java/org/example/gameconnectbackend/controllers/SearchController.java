package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.searchDtos.SearchResultDTO;
import org.example.gameconnectbackend.interfaces.ISearchService;
import org.example.gameconnectbackend.services.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ISearchService searchService;

    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/searchProfiles/{searchWord}")
    public ResponseEntity<List<SearchResultDTO>> searchProfiles(@PathVariable String searchWord) {
        List<SearchResultDTO> searchResultDTOS = searchService.searchForProfiles(searchWord);
        return ResponseEntity.ok(searchResultDTOS);
    }




}
