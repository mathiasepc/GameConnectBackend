package org.example.gameconnectbackend.services;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.exceptions.SameCredentialsException;
import org.example.gameconnectbackend.mappers.UserMapper;
import org.example.gameconnectbackend.repositories.RoleRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final String roleUser = "USER";
    private final PasswordEncoder passwordEncoder;

    public void checkUniqueCredentials(String username, String email){
        Map<String,String> errors = new HashMap<>();

        var usernameExist = userRepository.existsByUsername(username);
        if(usernameExist) errors.put("username", "Username already exist");

        var emailExist = userRepository.existsByEmail(email);
        if(emailExist) errors.put("email", "Email already exist");

        if(!errors.isEmpty()) throw new SameCredentialsException(errors);
    }

    public UserDto registerUser(RegisterUserRequest request){
        var user = userMapper.toEntity(request);
        var role = roleRepository.findByName(roleUser);

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
