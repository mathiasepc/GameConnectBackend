package org.example.gameconnectbackend.services;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.exceptions.EmailExistsException;
import org.example.gameconnectbackend.exceptions.UsernameExistsException;
import org.example.gameconnectbackend.mappers.UserMapper;
import org.example.gameconnectbackend.repositories.RoleRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String roleUser = "USER";


    public void checkUsername(String username){
        var exist = userRepository.existsByUsername(username);
        if(exist) throw new UsernameExistsException();
    }

    public void checkEmail(String email){
        var exist = userRepository.existsByEmail(email);
        if(exist) throw new EmailExistsException();
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
