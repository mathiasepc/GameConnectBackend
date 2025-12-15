package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.dtos.searchDtos.SearchResultDTO;

import java.util.List;

public interface ISearchService {
    List<SearchResultDTO> searchForProfiles(String searchWord);
}
