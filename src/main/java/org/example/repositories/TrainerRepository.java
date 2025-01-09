package org.example.repositories;

import org.example.entities.Member;
import org.example.entities.Trainer;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TrainerRepository {
    private SessionFactory sessionFactory= HibernateUti.getSessionFactory();

    public void createTrainer(Trainer trainer){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(trainer);
        session.getTransaction().commit();
        session.close();
    }

    public Trainer getTrainerById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Trainer tempTrainer = session.get(Trainer.class, id);
        session.getTransaction().commit();
        session.close();
        return tempTrainer;
    }

    public Trainer getTrainerSessionById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Trainer tempTrainer = session.get(Trainer.class, id);
        tempTrainer.getTrainingSessions().stream().forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
        return tempTrainer;
    }

    public void updateTrainer(Trainer trainer){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(trainer);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteTrainer(Trainer trainer){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(trainer);
        session.getTransaction().commit();
        session.close();
    }


}
