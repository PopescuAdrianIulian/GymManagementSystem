package org.example.repositories;

import org.example.entities.Member;
import org.example.entities.Subscription;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SubscriptionRepository {
    private SessionFactory sessionFactory = HibernateUti.getSessionFactory();

    public void createSubscription(Subscription subscription) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subscription);
        session.getTransaction().commit();
        session.close();
    }

    public Subscription getSubscriptionById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Subscription subscription = session.get(Subscription.class, id);
        session.getTransaction().commit();
        session.close();
        return subscription;
    }

    public void updateSubscription(Subscription subscription) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(subscription);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteSubscription(Subscription subscription) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(subscription);
        session.getTransaction().commit();
        session.close();
    }
}
