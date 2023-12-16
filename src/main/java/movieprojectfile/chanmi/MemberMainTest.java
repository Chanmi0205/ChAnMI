package movieprojectfile.chanmi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MemberMainTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 회원 가입
            

            // 회원 가입 시 아이디 중복

            // 회원 정보 수정

            // 회원 정보 수정 시 아이디 중복

            // 회원 탈퇴

            tx.commit();

        } catch (Exception e) {

        } finally {
            em.close();
        }
        emf.close();
    }

}
