package org.example.gameconnectbackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.JwtResponse;
import org.example.gameconnectbackend.dtos.userDtos.LoginRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.mappers.UserMapper;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.example.gameconnectbackend.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request){
        authenticationManager.authenticate(
                // UsernamePasswordAuthenticationToken have 2 constructors.
                // We set the first constructor of UsernamePasswordAuthenticationToken
                // - the first constructor to pass email. We can fetch that later.
                // Object principal: set to the email of the user or what ever object we use to identify the user
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var token = jwtService.generateJwtToken(request.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader){
        // Removes Bearer from the token
        var token = authHeader.replace("Bearer ", "");

        return jwtService.validateJwtToken(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> logout(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = (String) authentication.getPrincipal();

        var user = userRepository.findByEmail(email).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    // Handles bad credentials when trying to login
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handeBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
