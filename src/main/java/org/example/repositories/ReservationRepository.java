package org.example.repositories;

import org.example.entities.Progress;
import org.example.entities.Reservation;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ReservationRepository {
    private SessionFactory sessionFactory = HibernateUti.getSessionFactory();

    public void createReservation(Reservation reservation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(reservation);
        session.getTransaction().commit();
        session.close();
    }


    public Reservation getReservationById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Reservation reservation = session.get(Reservation.class, id);
        session.getTransaction().commit();
        session.close();
        return reservation;
    }


    public void updateReservation(Reservation reservation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(reservation);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteReservation(Reservation reservation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(reservation);
        session.getTransaction().commit();
        session.close();
    }



}


