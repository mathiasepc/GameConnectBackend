package org.example.gameconnectbackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.authDtos.JwtResponse;
import org.example.gameconnectbackend.dtos.authDtos.LoginRequest;
import org.example.gameconnectbackend.interfaces.IJwtService;
import org.example.gameconnectbackend.repositories.UserRepository;
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
    private final IJwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request){
        // This checks both username and password.
        // Class AuthService: loadUserByUsername to collect the user from the database.
        authenticationManager.authenticate(
                // UsernamePasswordAuthenticationToken have 2 constructors.
                // We set the first constructor of UsernamePasswordAuthenticationToken
                // - the first constructor to pass email. We can fetch that later.
                // Object principal: set to the information of the user you choose.
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Errorhandling here is irrelevant. We already checked that the user exists above
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var token = jwtService.generateJwtToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    // Handles bad credentials when trying to login
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handeBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
