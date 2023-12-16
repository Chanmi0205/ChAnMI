package movieprojectfile.chanmi.repository.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import movieprojectfile.chanmi.domain.Movie;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.repository.OpenMovieRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPAOpenMovieRepository implements OpenMovieRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OpenMovie> openMovieList() {
        return em.createQuery("SELECT o FROM OpenMovie o", OpenMovie.class).getResultList();
    }

    // 회원이 검색한 상영 영화 반환
    @Override
    public List<OpenMovie> findOpenMovieList(String movieName) {
        return em.createQuery("SELECT DISTINCT o FROM OpenMovie o, Movie mo WHERE mo.movieName =: movieName", OpenMovie.class)
                .setParameter("movieName", movieName)
                .getResultList();
    }

}
