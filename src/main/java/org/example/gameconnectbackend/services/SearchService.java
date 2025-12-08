package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.dtos.searchDtos.SearchResultDTO;

import org.example.gameconnectbackend.mappers.UserMapper;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.ProfileRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public SearchService(ProfileRepository profileRepository, UserRepository userRepository,  UserMapper userMapper) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<SearchResultDTO> searchForProfiles(String searchWord) {
        List<User> foundUsers =userRepository.findByUsername(searchWord);
        List<SearchResultDTO> searchResultDTOS = new ArrayList<>();

        for (User user: foundUsers){
            Profile profile = user.getProfile();
            SearchResultDTO searchResultDTO = new SearchResultDTO();
            searchResultDTO.setProfileId(profile.getId());
            searchResultDTO.setUserId(user.getId());
            searchResultDTO.setUsername(user.getUsername());
            searchResultDTO.setProfileImage(profile.getImg());

            searchResultDTOS.add(searchResultDTO);
        }


        return searchResultDTOS;

    }
}
