package org.example.gameconnectbackend.services;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.dtos.userDtos.ChangePasswordRequest;
import org.example.gameconnectbackend.dtos.userDtos.RegisterUserRequest;
import org.example.gameconnectbackend.dtos.userDtos.UserDto;
import org.example.gameconnectbackend.exceptions.AccesDeniedException;
import org.example.gameconnectbackend.exceptions.SameCredentialsException;
import org.example.gameconnectbackend.exceptions.UserNotFoundException;
import org.example.gameconnectbackend.interfaces.IUserService;
import org.example.gameconnectbackend.mappers.UserMapper;
import org.example.gameconnectbackend.models.Game;
import org.example.gameconnectbackend.models.Profile;
import org.example.gameconnectbackend.repositories.GameRepository;
import org.example.gameconnectbackend.repositories.RoleRepository;
import org.example.gameconnectbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor

@Service
public class UserService implements IUserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final String roleUser = "USER";
    private final PasswordEncoder passwordEncoder;
    private final GameRepository gameRepository;

    @Override
    public void checkUniqueCredentials(String username, String email) {
        Map<String, String> errors = new HashMap<>();

        var usernameExist = userRepository.existsByUsername(username);
        if (usernameExist) errors.put("username", "Username already exist");

        var emailExist = userRepository.existsByEmail(email);
        if (emailExist) errors.put("email", "Email already exist");

        if (!errors.isEmpty()) throw new SameCredentialsException(errors);
    }

    @Override
    public UserDto changePassword(Long id, ChangePasswordRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) throw new UserNotFoundException();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AccesDeniedException();
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto registerUser(RegisterUserRequest request) {
        var user = userMapper.toEntity(request);
        var role = roleRepository.findByName(roleUser);

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Profile profile = new Profile();
        profile.setImg(request.getImg());
        profile.setBio("");
        profile.setUser(user);
        user.setProfile(profile);

        Set<Game> games = new HashSet<>();

        Game game = gameRepository.findById(request.getGameId())
                .orElseGet(() -> {
                    Game newGame = new Game();
                    newGame.setId(request.getGameId());
                    newGame.setName(request.getGameName());
                    newGame.setImg(request.getGameImg());
                    return gameRepository.save(newGame);
                });

        games.add(game);
        user.getProfile().setFavouriteGames(games);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public UserDto findEmail(String email) {
        var user = userRepository.findByEmail(email).orElse(null);
        if (user == null) throw new UserNotFoundException();

        return userMapper.toDto(user);
    }
}
