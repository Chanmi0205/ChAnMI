package movieprojectfile.chanmi.service;

import lombok.RequiredArgsConstructor;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.repository.MemberRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Override
    public String memberJoin(String ID, String name, String PW) {
        Member member = new Member(ID, name, PW);

        memberRepository.insertMember(member);
        return member.getID();
    }

    // 회원탈퇴
    @Override
    public void MemberLeave(Member member) {
        memberRepository.deleteMember(member);
    }

    // 회원 조회
    @Override
    public Member findMember(String ID) {
        return memberRepository.findMember(ID);
    }


}
