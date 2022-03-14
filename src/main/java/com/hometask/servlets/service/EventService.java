package com.hometask.servlets.service;

import com.hometask.servlets.model.Event;
import com.hometask.servlets.model.File;
import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.EventRepository;

import java.util.List;

public class EventService {
    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getUserEvents(User user) {
        return eventRepository.getUserEvents(user);
    }

    public Event getById(Long id) {
        return eventRepository.getById(id);
    }

    public boolean DeleteById(Long id) {
        return eventRepository.deleteById(id);
    }

    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    public Event save(String eventName, File file, User user){
        Event event = new Event();
        event.setEventName(eventName);
        event.setFile(file);
        event.setUser(user);
        eventRepository.save(event);
        return event;
    }

    public Event update(Event event){
        return eventRepository.update(event);
    }

}
