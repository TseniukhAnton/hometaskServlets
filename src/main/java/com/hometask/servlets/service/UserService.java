package com.hometask.servlets.service;

import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.UserRepository;

import java.util.List;

public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    public User update(User user, String newUsername, String newPassword) {
        if (newUsername != null) {
            user.setUsername(newUsername);
        }
        if (newPassword != null) {
            user.setPassword(newPassword);
        }
        userRepository.update(user);
        return user;
    }
}
