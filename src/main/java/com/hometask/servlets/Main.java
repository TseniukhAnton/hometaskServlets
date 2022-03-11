package com.hometask.servlets;

import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.hibernate.HiberUserRepoImpl;

public class Main {
    public static void main(String[] args) {
        HiberUserRepoImpl hiberUserRepoImpl = new HiberUserRepoImpl();
        User user = new User();
        user.setUsername("Anton");
        user.setPassword("Test");
        hiberUserRepoImpl.save(user);
    }
}
