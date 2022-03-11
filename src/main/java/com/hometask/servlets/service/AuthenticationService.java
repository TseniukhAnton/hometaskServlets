package com.hometask.servlets.service;

import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.UserRepository;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        User user = userRepository.getByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
