package org.example.gameconnectbackend.services;


import org.example.gameconnectbackend.dtos.userDTOs.AdminUserDto;
import org.example.gameconnectbackend.exceptions.UserNotFoundException;
import org.example.gameconnectbackend.mappers.UserMapper;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.models.User;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AdminService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<AdminUserDto> getPageOfUsers(Integer page, Integer usersPerPage){
        Pageable pageable = PageRequest.of(page,usersPerPage);
        Page<User> pageObject = userRepository.findAll(pageable);
        List<User> users = pageObject.getContent();
        List<AdminUserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            AdminUserDto userDto = userMapper.toAdminUserDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public AdminUserDto adminUpdateUser(AdminUserDto dto){

        User user = userRepository.findById(dto.getId()).orElse(null);
        if (user == null) throw new UserNotFoundException();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(user.getPassword());
        user.getRole().setName(dto.getRole().getName());

        Profile profile = user.getProfile();
        profile.setImg(dto.getImg());
        profile.setBio(dto.getBio());

        userRepository.save(user);

        AdminUserDto returnDto = userMapper.toAdminUserDto(user);

        return returnDto;
    }

}
