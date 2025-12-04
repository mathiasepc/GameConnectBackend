package org.example.gameconnectbackend.controllers;

import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@CrossOrigin("*")
public class testController {
    private final UserRepository userRepository;

    public testController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //for front-end profile creation until userend points are made
    @GetMapping("/api/users/{id}")
    public ResponseEntity<ProfileDTO> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        Profile profile = user.getProfile();

        ProfileDTO dto = new ProfileDTO(
                user.getUsername(),
                profile.getBio(),
                profile.getImg()

        );

        return ResponseEntity.ok(dto);
    }
}
