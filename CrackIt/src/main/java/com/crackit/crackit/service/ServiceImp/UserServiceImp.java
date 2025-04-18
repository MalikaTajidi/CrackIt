package com.crackit.crackit.service.ServiceImp;

import com.crackit.crackit.config.JwtProvider;
import com.crackit.crackit.dto.LoginDTO;
import com.crackit.crackit.dto.RegisterDTO;
import com.crackit.crackit.dto.UserResponseDTO;
import com.crackit.crackit.dto.UserUpdateDTO;
import com.crackit.crackit.model.User;
import com.crackit.crackit.repository.UserRepository;
import com.crackit.crackit.service.ServiceInterfaces.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public String register(RegisterDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return "User registered successfully: " + user.getEmail();
    }
    @Override
    public String login(LoginDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return jwtProvider.generateToken(user.getEmail());
    }
    @Override
    public List<UserResponseDTO> searchUsersByFirstNameAndLastName(String firstName, String lastName) {
        List<User> users = userRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getProfilePicture()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public User updateUserProfile(int userId, UserUpdateDTO userUpdateDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userUpdateDTO.getFirstName());
            user.setLastName(userUpdateDTO.getLastName());
            user.setEmail(userUpdateDTO.getEmail());
            user.setProfilePicture(userUpdateDTO.getProfilePicture());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    }



