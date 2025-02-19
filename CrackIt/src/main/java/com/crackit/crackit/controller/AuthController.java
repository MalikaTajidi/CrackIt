package com.crackit.crackit.controller;

import com.crackit.crackit.dto.AuthResponse;
import com.crackit.crackit.dto.LoginDTO;
import com.crackit.crackit.dto.RegisterDTO;
import com.crackit.crackit.service.ServiceInterfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Validated @RequestBody RegisterDTO request) {
        String message = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(message));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody LoginDTO request) {
        String token = userService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse("Login successful", token));
    }
}
