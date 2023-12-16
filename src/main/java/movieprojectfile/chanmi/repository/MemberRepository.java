package movieprojectfile.chanmi.repository;

import movieprojectfile.chanmi.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository {

    String insertMember(Member member);

    void deleteMember(Member member);

    Member findMember(String ID);

}
