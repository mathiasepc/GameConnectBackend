package org.example.gameconnectbackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.ChangePasswordRequest;
import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.exceptions.AccesDeniedException;
import org.example.gameconnectbackend.exceptions.SameCredentialsException;
import org.example.gameconnectbackend.exceptions.UserNotFoundException;
import org.example.gameconnectbackend.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor

@RestController
@RequestMapping("users")
public class UserController {
    private final IUserService userService;


    @PostMapping
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request
    ) {
        userService.checkUniqueCredentials(request.getUsername(), request.getEmail());

        var userDto = userService.registerUser(request);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("email")
    public ResponseEntity<Long> checkEmail(
            @RequestParam(name = "email") String email) {
        var user = userService.findEmail(email);

        return ResponseEntity.ok(user.getId());
    }

    @PutMapping("{id}/change-password")
    public ResponseEntity<Boolean> changePassword(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody ChangePasswordRequest request) {
        var user = userService.changePassword(id, request);

        return ResponseEntity.ok(user != null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errors = Map.of("user", "User not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(AccesDeniedException.class)
    public ResponseEntity<Void> handleAccesDeniedException(AccesDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(SameCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleEmailExistsException(SameCredentialsException ex) {
        Map<String, String> errors = ex.getCredentialsExist();

        return ResponseEntity.badRequest().body(errors);
    }
}
