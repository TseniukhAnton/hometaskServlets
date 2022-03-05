package com.hometask.servlets.repository.hibernate;

import com.hometask.servlets.model.Event;
import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.EventRepository;
import com.hometask.servlets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HiberEventRepoImpl implements EventRepository {
    @Override
    public List<Event> getUserEvents(User user) {
        List events = null;
        if (user == null){
            return null;
        }
        try(Session session = HibernateUtil.getSessionFactory().openSession();){
            Query query = session.createQuery("From Event WHERE user = :user");
            query.setParameter("user", user);
            events = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public Event getById(Long id) {
        Event event = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession();){

        }
        return event;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<Event> getAll() {
        return null;
    }

    @Override
    public Event save(Event event) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }
}
