package movieprojectfile.chanmi.repository;

import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.ReserveMovie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReserveRepository {

    String reserveMovie(OpenMovie openMovie, Member member);

    void deleteReserveMovie(ReserveMovie reserveMovie);

    List<ReserveMovie> findReserveMovieList(Member member);

}
