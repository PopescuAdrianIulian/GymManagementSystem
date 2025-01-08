package org.example.repositories;

import org.example.entities.Member;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MemberRepository {
    private SessionFactory sessionFactory = HibernateUti.getSessionFactory();

    public void createMember(Member member) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(member);
        session.getTransaction().commit();
        session.close();
    }

    public Member getMemberById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Member tempMember = session.get(Member.class, id);
        session.getTransaction().commit();
        session.close();
        return tempMember;
    }

    public void updateMember(Member member){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(member);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteMember(Member member){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(member);
        session.getTransaction().commit();
        session.close();
    }

}
