package org.example.gameconnectbackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.exceptions.EmailExistsException;
import org.example.gameconnectbackend.exceptions.UsernameExistsException;
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
        userService.checkUsername(request.getUsername());
        userService.checkEmail(request.getEmail());

        var userDto = userService.registerUser(request);

        return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<Map<String,String>> handleUsernameExistsException(UsernameExistsException ex){
        return ResponseEntity.badRequest()
                .body(Map.of("error", "Username already exists"));
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailExistsException(EmailExistsException ex){
        return ResponseEntity.badRequest()
                .body(Map.of("error", "Email already exists"));
    }
}
