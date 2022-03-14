package com.hometask.servlets.repository.hibernate;

import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.UserRepository;
import com.hometask.servlets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HiberUserRepoImpl implements UserRepository {
    @Override
    public User getById(Long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
//            Query query = session.createQuery("FROM User u LEFT JOIN FETCH u.files uf WHERE u.id = :id");
//            query.setParameter("id", id);
//            List users = query.getResultList();
//            if (users.isEmpty()) {
//                return null;
//            }
            user = session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM User u LEFT JOIN FETCH u.files uf WHERE u.username = :username");
            query.setParameter("username", username);
            List users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            user = (User) users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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
    public List<User> getAll() {
        List users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            users = session.createQuery("FROM User").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

}
