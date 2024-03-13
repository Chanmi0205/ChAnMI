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
@Transactional
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

    @Override
    public Member login(String ID, String PW) {
        return em.createQuery("SELECT m FROM Member m WHERE m.ID =: ID AND m.PW =: PW", Member.class)
                .setParameter("ID", ID)
                .setParameter("PW", PW)
                .getSingleResult();
    }

    @Override
    public Member updateAllMember(String member_PK, String ID, String name, String PW) {
        Member allUpdateMember = em.createQuery("SELECT m FROM Member m WHERE m.Member_PK =: Member_PK", Member.class)
                        .setParameter("Member_PK", member_PK)
                        .getSingleResult();
        em.persist(allUpdateMember);
        allUpdateMember.allChangeMember(ID, name, PW);
        em.flush();
        return allUpdateMember;
    }

    @Override
    public Member updateMember(String member_PK, String ID, String name) {
        Member updateMember = em.createQuery("SELECT m FROM Member m WHERE m.Member_PK =: Member_PK", Member.class)
                .setParameter("Member_PK", member_PK)
                .getSingleResult();
        em.persist(updateMember);
        updateMember.changeMember(ID, name);
        em.flush();
        return updateMember;
    }

    @Override
    public List<Member> memberList() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }


}
