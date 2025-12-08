package org.example.gameconnectbackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.JwtResponse;
import org.example.gameconnectbackend.dtos.userDtos.LoginRequest;
import org.example.gameconnectbackend.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var token = jwtService.generateJwtToken(request.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    // Handles bad credentials when trying to login
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handeBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
