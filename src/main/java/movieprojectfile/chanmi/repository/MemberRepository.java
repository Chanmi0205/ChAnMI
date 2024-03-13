package movieprojectfile.chanmi.repository;

import movieprojectfile.chanmi.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository {

    String insertMember(Member member);

    void deleteMember(Member member);

    Member findMember(String ID);

    Member login(String ID, String PW);

    Member updateAllMember(String member_PK, String ID, String name, String PW);

    Member updateMember(String member_PK, String ID, String name);

    List<Member> memberList();

}
