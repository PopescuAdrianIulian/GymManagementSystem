package org.example.repositories;

import org.example.entities.Equipment;
import org.example.entities.Progress;
import org.example.entities.Reservation;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EquipmentRepository {
    private SessionFactory sessionFactory = HibernateUti.getSessionFactory();

    public void createEquipment(Equipment equipment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(equipment);
        session.getTransaction().commit();
        session.close();
    }


    public Equipment getEquipmentById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Equipment equipment = session.get(Equipment.class, id);
        session.getTransaction().commit();
        session.close();
        return equipment;
    }


    public void updateEquipment(Equipment equipment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(equipment);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteEquipment(Equipment equipment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(equipment);
        session.getTransaction().commit();
        session.close();
    }

    public List<Equipment> getAllEquipment() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Equipment> query = session.createQuery("FROM Equipment", Equipment.class);
        List<Equipment> equipmentList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return equipmentList;
    }

    public List<Reservation> getEquipmentReservationById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Equipment equipment = session.get(Equipment.class, id);
        List<Reservation> reservationList = equipment.getReservationList().stream().toList();
        session.getTransaction().commit();
        session.close();
        return reservationList;
    }
}
