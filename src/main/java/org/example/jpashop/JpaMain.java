package org.example.jpashop;

import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Order order = em.find(Order.class, 1L);

            //Long memberId = order.getMemberId(); 이런한 방식이 아니라
            //Member member = em.find(Member.class, memberId);

            //객체탐색으로 찾아서 들어가야 함
            Member findMember = order.getMember();

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
