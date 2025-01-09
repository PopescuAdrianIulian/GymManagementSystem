package org.example.repositories;

import org.example.entities.Progress;
import org.example.entities.Trainer;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProgressRepository {
    private SessionFactory sessionFactory = HibernateUti.getSessionFactory();

    public void createProgress(Progress progress) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(progress);
        session.getTransaction().commit();
        session.close();
    }


    public Progress getProgressById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Progress progress = session.get(Progress.class, id);
        session.getTransaction().commit();
        session.close();
        return progress;
    }


    public void updateProgress(Progress progress) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(progress);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteProgress(Progress progress) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(progress);
        session.getTransaction().commit();
        session.close();
    }


}
