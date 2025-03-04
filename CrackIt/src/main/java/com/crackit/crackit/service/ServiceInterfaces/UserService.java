package com.crackit.crackit.service.ServiceInterfaces;

import java.util.List;

import com.crackit.crackit.dto.LoginDTO;
import com.crackit.crackit.dto.RegisterDTO;
import com.crackit.crackit.model.User;

public interface UserService {
    String register(RegisterDTO request);
    String login(LoginDTO request);
    List<User> searchUsersByFirstNameAndLastName(String firstName, String lastName);
}
