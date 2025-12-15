package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.gameDto.GameDto;
import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;
import org.example.gameconnectbackend.dtos.profileDtos.UpdateBiodto;
import org.example.gameconnectbackend.dtos.profileDtos.UpdateImgDto;
import org.example.gameconnectbackend.interfaces.IProfileService;
import org.example.gameconnectbackend.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final IProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getUserById(@PathVariable Long id, Long currentUserId) {
        ProfileDTO dto = profileService.getProfileDTO(id, currentUserId);

        if(dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/games")
    public ResponseEntity<ProfileDTO> addGameToProfile(@RequestBody GameDto gameDto, @PathVariable Long id) {
        ProfileDTO dto = profileService.addGameToProfile(gameDto, id);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}/bio")
    public ResponseEntity<ProfileDTO> updateBio(@PathVariable Long id, @RequestBody UpdateBiodto dto) {
        ProfileDTO updated = profileService.updateBio(id, dto.getBio());
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/img")
    public ResponseEntity<ProfileDTO> updatePic(@PathVariable Long id, @RequestBody UpdateImgDto dto) {
        ProfileDTO updated = profileService.updateImg(id, dto.getImg());
        return ResponseEntity.ok(updated);
    }

}
