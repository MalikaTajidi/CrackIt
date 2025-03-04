package com.crackit.crackit.controller;

import com.crackit.crackit.dto.UserResponseDTO;
import com.crackit.crackit.mapper.UserMapper;
import com.crackit.crackit.model.User;
import com.crackit.crackit.service.ServiceInterfaces.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
 }


    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(
            @RequestParam String firstName,
            @RequestParam String lastName) {

        List<UserResponseDTO> users = userService.searchUsersByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(users);
    }
}
