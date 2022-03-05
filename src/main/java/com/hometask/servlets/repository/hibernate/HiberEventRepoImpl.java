package com.hometask.servlets.repository.hibernate;

import com.hometask.servlets.model.Event;
import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.EventRepository;
import com.hometask.servlets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HiberEventRepoImpl implements EventRepository {
    @Override
    public List<Event> getUserEvents(User user) {
        List events = null;
        if (user == null) {
            return null;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("From Event WHERE user = :user");
            query.setParameter("user", user);
            events = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public Event getById(Long id) {
        Event event = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM Event WHERE id = :id");
            query.setParameter("id", id);
            List events = query.getResultList();
            if (events.isEmpty()) {
                return null;
            }
            event = (Event) events.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            if (getById(id) == null) {
                return false;
            }
            session.delete(getById(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
        return true;
    }

    @Override
    public List<Event> getAll() {
        List events = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            events = session.createQuery("FROM Event").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public Event save(Event event) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        Transaction transaction = null;
        if (getById(event.getId()) == null) {
            return null;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
        return event;
    }
}
