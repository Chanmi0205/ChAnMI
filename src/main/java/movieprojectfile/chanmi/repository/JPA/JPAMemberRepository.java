package movieprojectfile.chanmi.repository.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.repository.MemberRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JPAMemberRepository implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 회원 가입
    @Override
    @Transactional
    public String insertMember(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        em.persist(member);
        return member.getID();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = em.createQuery("SELECT m FROM Member m WHERE m.ID =: ID", Member.class)
                .setParameter("ID", member.getID())
                .getResultList();
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 ID입니다");
        }
    }

    @Override
    public void deleteMember(Member member) {
        em.remove(member);
    }

    @Override
    public Member findMember(String ID) {
        return em.createQuery("SELECT m FROM Member m WHERE m.ID =: ID", Member.class)
                .setParameter("ID", ID).getSingleResult();
    }

}
