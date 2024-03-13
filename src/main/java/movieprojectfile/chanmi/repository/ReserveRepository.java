package movieprojectfile.chanmi.repository;

import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.ReserveMovie;
import movieprojectfile.chanmi.domain.Site;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReserveRepository {

    void reserveMovie(OpenMovie openMovie, Member member, Site site);

    void deleteReserveMovie(ReserveMovie reserveMovie);

    List<ReserveMovie> findReserveMovieList(Member member);

    Site findSite(String sitePK);

    ReserveMovie cancelMovie(String cancelMoviePK);

    String ifCancel(String cancelMoviePK);

}
