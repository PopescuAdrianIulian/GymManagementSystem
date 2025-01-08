package org.example.repositories;

import org.example.entities.Trainer;
import org.example.entities.TrainingSession;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TrainingSessionRepository {
    private SessionFactory sessionFactory = HibernateUti.getSessionFactory();

    public void createTrainingSession(TrainingSession trainingSession) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(trainingSession);
        session.getTransaction().commit();
        session.close();
    }

    public TrainingSession getTrainingSessionById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TrainingSession tempTrainingSession = session.get(TrainingSession.class, id);
        session.getTransaction().commit();
        session.close();
        return tempTrainingSession;
    }

    public void updateTrainingSession(TrainingSession trainingSession) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(trainingSession);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteTrainingSession(TrainingSession trainingSession) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(trainingSession);
        session.getTransaction().commit();
        session.close();
    }

}
