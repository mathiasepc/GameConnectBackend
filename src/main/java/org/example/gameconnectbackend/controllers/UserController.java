package org.example.gameconnectbackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.exceptions.SameCredentialsException;
import org.example.gameconnectbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request
            ){
        userService.checkUniqueCredentials(request.getUsername(), request.getEmail());

        var userDto = userService.registerUser(request);

        return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(SameCredentialsException.class)
    public ResponseEntity<Map<String,String>> handleEmailExistsException(SameCredentialsException ex){
        Map<String,String> errors = ex.getCredentialsExist();

        return ResponseEntity.badRequest().body(errors);
    }
}
