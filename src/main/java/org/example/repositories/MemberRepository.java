package org.example.repositories;

import org.example.entities.Member;
import org.example.entities.Progress;
import org.example.util.HibernateUti;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public Member addProgressToMemberById(int id, Progress progress) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Member tempMember = session.get(Member.class, id);
        List<Progress> progresses = tempMember.getProgressList();
        progresses.add(progress);

        session.getTransaction().commit();
        session.close();
        return tempMember;
    }

    public List<Progress> getMemberProgressById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Member tempMember = session.get(Member.class, id);
        List<Progress> progressList = tempMember.getProgressList().stream().toList();

        session.getTransaction().commit();
        session.close();
        return progressList;
    }


    public Member getMemberSubscriptionById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Member tempMember = session.get(Member.class, id);
        tempMember.getSubscription();
        session.getTransaction().commit();
        session.close();
        return tempMember;
    }

    public Member getMemberSessionById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Member tempMember = session.get(Member.class, id);
        tempMember.getTrainingSessions().stream().forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
        return tempMember;
    }

    public void updateMember(Member member) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(member);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteMember(Member member) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(member);
        session.getTransaction().commit();
        session.close();
    }

}
