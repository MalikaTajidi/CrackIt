package com.crackit.crackit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.crackit.crackit.config.JwtProvider;
import com.crackit.crackit.dto.LoginDTO;
import com.crackit.crackit.dto.RegisterDTO;
import com.crackit.crackit.dto.UserResponseDTO;
import com.crackit.crackit.dto.UserUpdateDTO;
import com.crackit.crackit.model.User;
import com.crackit.crackit.repository.UserRepository;
import com.crackit.crackit.service.ServiceImp.UserServiceImp;

@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private UserServiceImp userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1);
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john.doe@example.com");
        testUser.setPassword("hashedPassword");
    }

    @Test
    void testRegister_Success() {
        // Arrange
        RegisterDTO registerDTO = new RegisterDTO("John", "Doe", "john.doe@example.com", "password123");

        when(userRepository.existsByEmail(registerDTO.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(registerDTO.getPassword())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        String result = userService.register(registerDTO);

        // Assert
        assertEquals("User registered successfully: john.doe@example.com", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        // Arrange
        RegisterDTO registerDTO = new RegisterDTO("John", "Doe", "john.doe@example.com", "password123");

        when(userRepository.existsByEmail(registerDTO.getEmail())).thenReturn(true);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.register(registerDTO));
        assertEquals("Email already in use.", exception.getMessage());
    }

    @Test
    void testLogin_Success() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("john.doe@example.com", "password123");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(loginDTO.getPassword(), testUser.getPassword())).thenReturn(true);
        when(jwtProvider.generateToken(testUser.getEmail())).thenReturn("mockedToken");

        // Act
        String token = userService.login(loginDTO);

        // Assert
        assertEquals("mockedToken", token);
    }
    @Test
    void testLogin_InvalidEmail() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("wrong.email@example.com", "password123");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.login(loginDTO));
        assertEquals("Invalid email or password.", exception.getMessage());
    }
    @Test
    void testLogin_InvalidPassword() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("john.doe@example.com", "wrongpassword");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(loginDTO.getPassword(), testUser.getPassword())).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.login(loginDTO));
        assertEquals("Invalid email or password.", exception.getMessage());
    }

    @Test
    void testSearchUsersByFirstNameAndLastName() {
        // Arrange
        when(userRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("John", "Doe"))
                .thenReturn(List.of(testUser));

        // Act
        List<UserResponseDTO> users = userService.searchUsersByFirstNameAndLastName("John", "Doe");

        // Assert
        assertEquals(1, users.size());
        assertEquals("John", users.get(0).getFirstName());
    }

    @Test
    void testUpdateUserProfile_Success() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Jane", "Doe", "jane.doe@example.com", "profile.jpg");

        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        User updatedUser = userService.updateUserProfile(1, userUpdateDTO);

        // Assert
        assertEquals("Jane", updatedUser.getFirstName());
        assertEquals("Doe", updatedUser.getLastName());
        assertEquals("jane.doe@example.com", updatedUser.getEmail());
        assertEquals("profile.jpg", updatedUser.getProfilePicture());
    }

    @Test
    void testUpdateUserProfile_UserNotFound() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Jane", "Doe", "jane.doe@example.com", "profile.jpg");

        when(userRepository.findById(2)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userService.updateUserProfile(2, userUpdateDTO));
        assertEquals("User not found", exception.getMessage());
    }
}
