package com.hometask.servlets.repository.hibernate;

import com.hometask.servlets.model.Event;
import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.EventRepository;

import java.util.List;

public class HiberEventRepoImpl implements EventRepository {
    @Override
    public List<Event> getUserEvents(User user) {
        return null;
    }

    @Override
    public Event getById(Long aLong) {
        return null;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public List<Event> getAll() {
        return null;
    }

    @Override
    public Event save(Event obj) {
        return null;
    }

    @Override
    public Event update(Event obj) {
        return null;
    }
}
