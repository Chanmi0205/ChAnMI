package movieprojectfile.chanmi.repository.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.ReserveMovie;
import movieprojectfile.chanmi.domain.Site;
import movieprojectfile.chanmi.repository.ReserveRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class JPAReserveRepository implements ReserveRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void reserveMovie(OpenMovie openMovie, Member member, Site site) {

        ReserveMovie reserveMovie = new ReserveMovie(openMovie, site, member);

        em.persist(reserveMovie);
        em.flush();
    }


    // 중복 예매 검증
    private void validateDuplicateMember(ReserveMovie reserveMovie) {
        List<Member> findMembers = em.createQuery("SELECT m FROM ReserveMovie r, OpenMovie o, Movie m, Site s " +
                                "WHERE r.openMovie.Open_Movie_PK = r.openMovie.Open_Movie_PK AND s.site_PK = r.site.site_PK " +
                                "AND m.movieName = o.movie.movieName AND m.movieName =: movieName", Member.class)
                .setParameter("movieName", reserveMovie.getOpenMovie().getMovie().getMovieName())
                .getResultList();
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("<" + reserveMovie.getOpenMovie().getMovie().getMovieRunTime() + ">" + "영화는 이미 예매했습니다.");
        }
    }

    @Override
    public void deleteReserveMovie(ReserveMovie reserveMovie) {
        em.remove(reserveMovie);
        em.flush();
    }

    @Override
    public List<ReserveMovie> findReserveMovieList(Member member) {
        System.out.println("member = " + member.getID());
       return em.createQuery("SELECT r FROM ReserveMovie r, Member m WHERE r.member.ID =: ID",
                       ReserveMovie.class)
                .setParameter("ID", member.getID())
               .getResultList();
    }

    @Override
    public Site findSite(String sitePK) {
        return em.createQuery("SELECT s FROM Site s WHERE s.site_PK =: site_PK", Site.class)
                .setParameter("site_PK", sitePK)
                .getSingleResult();
    }

    @Override
    public ReserveMovie cancelMovie(String cancelMoviePK) {
        return em.createQuery("SELECT r FROM ReserveMovie r WHERE r.Reserve_Movie_PK =: cancelMoviePK", ReserveMovie.class)
                .setParameter("cancelMoviePK", cancelMoviePK)
                .getSingleResult();
    }

    @Override
    public String ifCancel(String cancelMoviePK) {

        List<OpenMovie> NotCancel = em.createQuery("SELECT o FROM OpenMovie o WHERE o.Open_Movie_PK =: cancelMoviePK AND o.openMovieStartTime <= CURRENT_TIMESTAMP", OpenMovie.class)
                .setParameter("cancelMoviePK", cancelMoviePK)
                .getResultList();
        if (!NotCancel.isEmpty()) {
            return "notCancel";
        }
        return null;
    }
}
