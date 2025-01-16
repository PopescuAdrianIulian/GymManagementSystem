package org.example.repositories;

import org.example.entities.Member;
import org.example.entities.Trainer;
import org.example.entities.TrainingSession;
import org.example.util.HibernateUti;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public TrainingSession addNewMemberToTrainingSessionById(int id, Member member) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TrainingSession tempTrainingSession = session.get(TrainingSession.class, id);
        tempTrainingSession.getMembers().add(member);
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

    public TrainingSession getMembersInTrainingSessionById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TrainingSession tempTrainingSession = session.get(TrainingSession.class, id);
        Hibernate.initialize(tempTrainingSession.getMembers());
        session.getTransaction().commit();
        session.close();
        return tempTrainingSession;
    }

}
