package movieprojectfile.chanmi.service;

import movieprojectfile.chanmi.domain.Member;

public interface MemberService {

    String memberJoin(String ID, String name, String PW);

    void MemberLeave(Member member);

    Member findMember(String ID);

}
