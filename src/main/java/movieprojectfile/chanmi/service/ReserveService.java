package movieprojectfile.chanmi.service;

import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.ReserveMovie;

import java.util.List;

public interface ReserveService {

    String reserveMovie(OpenMovie openMovie, Member member);

    void cancelReserveMovie(ReserveMovie reserveMovie);

    List<ReserveMovie> reserveMovieList(Member member);


}
