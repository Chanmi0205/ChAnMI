package movieprojectfile.chanmi.repository.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import movieprojectfile.chanmi.domain.Movie;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.Site;
import movieprojectfile.chanmi.domain.Theater;
import movieprojectfile.chanmi.repository.OpenMovieRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public class JPAOpenMovieRepository implements OpenMovieRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OpenMovie openMovie(String OpenMoviePK) {
        return em.createQuery("SELECT o FROM OpenMovie o WHERE o.Open_Movie_PK =: openMoviePK", OpenMovie.class)
                .setParameter("openMoviePK", OpenMoviePK)
                .getSingleResult();
    }

    @Override
    public List<OpenMovie> openDate(String movieName) {
        return em.createQuery("SELECT o FROM OpenMovie o WHERE o.movie.movieName =: movieName AND o.openMovieStartTime >= CURRENT_TIMESTAMP", OpenMovie.class)
                .setParameter("movieName", movieName)
                .getResultList();
    }

    @Override
    public List<Movie> openMovieList() {
        return em.createQuery("SELECT o FROM Movie o", Movie.class).getResultList();
    }

    @Override
    public List<OpenMovie> findOpenMovieList(String movieName) {
        return em.createQuery("SELECT DISTINCT o FROM OpenMovie o, Movie mo WHERE mo.movieName =: movieName", OpenMovie.class)
                .setParameter("movieName", movieName)
                .getResultList();
    }

    @Override
    public List<String> siteNameList(String theaterPK) {
        List<Site> siteList = em.createQuery("SELECT s FROM Site s WHERE s.theater.Theater_PK =: theaterPK", Site.class)
                .setParameter("theaterPK", theaterPK)
                .getResultList();
        List<String> sitePKList = new ArrayList<>();
        for (Site site : siteList) {
            sitePKList.add(site.getSite_PK());
        }
        return sitePKList;
    }

    @Override
    public Map<String, Site> siteList(String theaterPK) {
        List<Site> AllSite = em.createQuery("SELECT s FROM Site s WHERE s.theater.Theater_PK =: theaterPK", Site.class)
                .setParameter("theaterPK", theaterPK)
                .getResultList();

        Map<String, Site> siteList = new LinkedHashMap<>();

        for (Site site : AllSite) {
            siteList.put(site.getSite_PK(), site);
        }

        return siteList;
    }

    @Override
    public Map<String, Site> reserveSiteList(String OpenMoviePK) {

        List<Site> reserveSiteList = em.createQuery("SELECT s " +
                        "FROM OpenMovie o LEFT JOIN Theater t ON o.theater.Theater_PK = t.Theater_PK " +
                        "LEFT JOIN Site s ON t.Theater_PK = s.theater.Theater_PK " +
                        "LEFT JOIN ReserveMovie r ON s.site_PK = r.site.site_PK AND r.openMovie.Open_Movie_PK = o.Open_Movie_PK " +
                        "WHERE o.Open_Movie_PK =: OpenMoviePK AND r.member.Member_PK IS NULL", Site.class)
                .setParameter("OpenMoviePK", OpenMoviePK)
                .getResultList();

        Map<String, Site> siteList = new LinkedHashMap<>();

        for (Site site : reserveSiteList) {
            siteList.put(site.getSite_PK(), site);
        }

        return siteList;

    }

    @Override
    public List<Theater> AllTheater() {
        return em.createQuery("SELECT t FROM Cinema c, Theater t " +
                        "WHERE c.cinemaName = t.cinema.cinemaName", Theater.class)
                .getResultList();
    }

    @Override
    public List<Site> AllSite(String theaterPK) {

        return em.createQuery("SELECT s FROM Site s, Theater t " +
                        "WHERE s.theater.Theater_PK =: theaterPK", Site.class)
                .setParameter("theaterPK", theaterPK)
                .getResultList();

    }


}
