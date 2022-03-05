package com.hometask.servlets.repository;

import com.hometask.servlets.model.Event;
import com.hometask.servlets.model.User;

import java.util.List;

public interface EventRepository extends GenericRepository<Event, Long>{
    List<Event> getUserEvents(User user);
}
