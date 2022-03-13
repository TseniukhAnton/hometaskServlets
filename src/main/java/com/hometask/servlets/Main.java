package com.hometask.servlets;

import com.hometask.servlets.model.Event;
import com.hometask.servlets.model.File;
import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.hibernate.HiberEventRepoImpl;
import com.hometask.servlets.repository.hibernate.HiberFileRepoImpl;
import com.hometask.servlets.repository.hibernate.HiberUserRepoImpl;

public class Main {
    public static void main(String[] args) {
        HiberUserRepoImpl hiberUserRepoImpl = new HiberUserRepoImpl();
        HiberFileRepoImpl hiberFileRepoImpl = new HiberFileRepoImpl();
        HiberEventRepoImpl hiberEventRepoImpl = new HiberEventRepoImpl();
        User user = new User();
        user.setUsername("Oleg");
        user.setPassword("Test");
        hiberUserRepoImpl.save(user);
        File file = new File();
        file.setName("Anton's");
        file.setUser(user);
        hiberFileRepoImpl.save(file);
        Event event = new Event();
        event.setEventName("Save");
        event.setFile(file);
        event.setUser(user);
        hiberEventRepoImpl.save(event);
    }
}
