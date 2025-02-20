package com.crackit.crackit.service.ServiceInterfaces;

import com.crackit.crackit.dto.LoginDTO;
import com.crackit.crackit.dto.RegisterDTO;

public interface UserService {
    String register(RegisterDTO request);
    String login(LoginDTO request);
}
