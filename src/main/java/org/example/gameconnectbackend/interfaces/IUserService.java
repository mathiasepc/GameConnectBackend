package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.dtos.userDtos.ChangePasswordRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;

public interface IUserService {
    void checkUniqueCredentials(String username, String email);
    UserDto changePassword(Long id, ChangePasswordRequest request);
}
