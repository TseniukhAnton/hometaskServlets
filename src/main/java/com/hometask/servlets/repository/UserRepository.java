package com.hometask.servlets.repository;

import com.hometask.servlets.model.User;

public interface UserRepository extends GenericRepository<User, Long> {
    User getByUsername(String username);
}
