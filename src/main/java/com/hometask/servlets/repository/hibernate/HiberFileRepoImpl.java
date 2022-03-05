package com.hometask.servlets.repository.hibernate;

import com.hometask.servlets.model.File;
import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.FileRepository;
import com.hometask.servlets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HiberFileRepoImpl implements FileRepository {
    @Override
    public List<File> getUserFiles(User user) {
        List files = null;
        if (user == null) {
            return null;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM File WHERE user = :user");
            query.setParameter("user", user);
            files = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    public File getFileByName(String fileName, User user) {
        File file = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM File WHERE user = :user and name = :name");
            query.setParameter("user", user);
            query.setParameter("name", fileName);

            List files = query.getResultList();

            if (files.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public File getById(Long id) {
        File file = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM File WHERE id = :id");
            query.setParameter("id", id);
            List files = query.getResultList();
            if (files.isEmpty()) {
                return null;
            }
            file = (File) files.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
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
    public List<File> getAll() {
        List files = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            files = session.createQuery("FROM File").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    public File save(File file) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.save(file);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
        return file;
    }

    @Override
    public File update(File file) {
        Transaction transaction = null;
        if (getById(file.getId()) == null) {
            return null;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.update(file);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
        return file;
    }
}
