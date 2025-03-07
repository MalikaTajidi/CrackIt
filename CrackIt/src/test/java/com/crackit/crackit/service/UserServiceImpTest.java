package com.crackit.crackit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.crackit.crackit.config.JwtProvider;
import com.crackit.crackit.dto.RegisterDTO;
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
}
