package movieprojectfile.chanmi.repository.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.ReserveMovie;
import movieprojectfile.chanmi.repository.ReserveRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPAReserveRepository implements ReserveRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String reserveMovie(OpenMovie openMovie, Member member) {

        ReserveMovie reserveMovie = new ReserveMovie();

        reserveMovie.setOpenMovie(openMovie);
        reserveMovie.setMember(member);
        validateDuplicateMember(reserveMovie);

        em.persist(reserveMovie);
        return reserveMovie.getOpenMovie().getMovie().getMovieName();
    }


    // 중복 예매 검증
    private void validateDuplicateMember(ReserveMovie reserveMovie) {
        List<Member> findMembers = em.createQuery("SELECT m FROM ReserveMovie r, OpenMovie o, Movie m " +
                                "WHERE r.openMovie.OpenMovie_PK = r.openMovie.OpenMovie_PK " +
                                "AND m.Movie_PK = o.movie.Movie_PK AND m.movieName =: movieName", Member.class)
                .setParameter("movieName", reserveMovie.getOpenMovie().getMovie().getMovieName())
                .getResultList();
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("<" + reserveMovie.getOpenMovie().getMovie().getMovieName() + ">" + "영화는 이미 예매했습니다.");
        }
    }

    @Override
    public void deleteReserveMovie(ReserveMovie reserveMovie) {
        em.remove(reserveMovie);
    }

    @Override
    public List<ReserveMovie> findReserveMovieList(Member member) {
       return em.createQuery("SELECT r FROM ReserveMovie r JOIN Member m ON m.Member_PK =: member_PK",
                       ReserveMovie.class)
                .setParameter("member_PK", member.getMember_PK()).getResultList();
    }
}
