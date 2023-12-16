package movieprojectfile.chanmi.service;

import jakarta.persistence.EntityManager;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.ReserveMovie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ReserveServiceImplTest {


    @Autowired
    ReserveServiceImpl reserveServiceImpl;
    @Autowired
    OpenMovieServiceImpl openMovieServiceImpl;
    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired
    EntityManager em;

    // 영화 예매
    @Test
    public void 영화예매() throws Exception {

        Member reserveMember = memberServiceImpl.findMember("Chanmi");
        List<OpenMovie> openMovieList = openMovieServiceImpl.openMovieList();

        reserveServiceImpl.reserveMovie(openMovieList.get(0), reserveMember);
        System.out.println("=영화예매=======================================");
        em.flush();
    }

    // 개봉 영화 중복 예매
    @Test
    public void 영화중복예매() throws Exception {

        Member reserveMember = memberServiceImpl.findMember("Chanmi");
        List<OpenMovie> openMovieList = openMovieServiceImpl.openMovieList();
        reserveServiceImpl.reserveMovie(openMovieList.get(0), reserveMember);
        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> reserveServiceImpl.reserveMovie(openMovieList.get(0), reserveMember));

    }


    // 예매 내역 취소
    @Test
    public void 예매내역취소() throws Exception {

        Member reserveMember = memberServiceImpl.findMember("Chanmi");
        List<OpenMovie> openMovieList = openMovieServiceImpl.openMovieList();

        reserveServiceImpl.reserveMovie(openMovieList.get(0), reserveMember);
        List<ReserveMovie> reserveMovieList = reserveServiceImpl.reserveMovieList(reserveMember);

        reserveServiceImpl.cancelReserveMovie(reserveMovieList.get(0));
        System.out.println("=예매내역취소=======================================");
        em.flush();

    }

    // 예매 내역 조회
    @Test
    public void 예매내역조회() throws Exception {

        Member member = memberServiceImpl.findMember("Chanmi");
        System.out.println("=영화예매조회=======================================");
        List<ReserveMovie> reserveMovieList = reserveServiceImpl.reserveMovieList(member);

    }

}