package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.dtos.gameDto.GameDto;
import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;

public interface IProfileService {
    ProfileDTO getProfileDTO(Long id, Long currentUserId);
    ProfileDTO addGameToProfile(GameDto gameDto, Long currentUserId);
    ProfileDTO updateBio(Long currentUserId, String updatedBio);
    ProfileDTO updateImg(Long currentUserId, String updatedImg);
}
