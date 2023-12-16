package movieprojectfile.chanmi.service;

import jakarta.persistence.EntityManager;
import movieprojectfile.chanmi.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {


    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {

        String memberID = memberServiceImpl.memberJoin("test", "testName", "1234");

        System.out.println("=회원가입=======================================");
        em.flush();

    }

    @Test
    public void 회원가입시_아이디중복() throws Exception {

        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> memberServiceImpl.memberJoin("Chanmi", "박찬미", "12341234"));

    }

    @Test
    public void 회원정보수정() throws Exception {

        Member member = memberServiceImpl.findMember("Chanmi");
        member.changeMember("eunho", "박은호", member.getPW());

        System.out.println("=회원정보수정=======================================");
        em.flush();

    }

    @Test
    public void 회원정보수정시_아이디중복() throws Exception {

        String memberID = memberServiceImpl.memberJoin("test", "testName", "1234");

        Member member = memberServiceImpl.findMember(memberID);
        member.changeMember("Chanmi", "test", "1234");
    }

    @Test
    public void 회원탈퇴() throws Exception {

        Member member = memberServiceImpl.findMember("Chanmi");
        memberServiceImpl.MemberLeave(member);

        System.out.println("=회원탈퇴=======================================");
        em.flush();

    }

}